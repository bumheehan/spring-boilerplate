package xyz.bumbing.mvc.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private Instant timestamp;
    private Integer status;
    private String message;
    private String code;
    private List<ParameterError> errors;
    private String path;

    protected ErrorResponse(HttpStatus status) {
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            this.path = request.getRequestURI();
            this.timestamp = Instant.now();
        }
        this.message = status.getReasonPhrase();
        this.status = status.value();
        this.code = status.name();
        this.errors = new ArrayList<>();

    }


    public static ErrorResponse of(final HttpStatus httpStatus, String code, final BindingResult bindingResult) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus);
        errorResponse.setCode(code);
        errorResponse.errors = ParameterError.of(bindingResult);
        return errorResponse;
    }

    public static ErrorResponse of(final HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus);
    }

    public static ErrorResponse of(final HttpStatus httpStatus, String code) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus);
        errorResponse.setCode(code);
        return errorResponse;
    }

    public static ErrorResponse of(final HttpStatus httpStatus, String code, String message) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus);
        errorResponse.setMessage(message);
        errorResponse.setCode(code);
        return errorResponse;
    }

    public static ErrorResponse of(HttpStatus httpStatus, String code, MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<ParameterError> errors = ParameterError.of(e.getName(), value, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(httpStatus);
        errorResponse.setCode(code);
        errorResponse.errors = errors;
        return errorResponse;
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ParameterError {
        private String field;
        private String value;
        private String reason;

        private ParameterError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<ParameterError> of(final String field, final String value, final String reason) {
            List<ParameterError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new ParameterError(field, value, reason));
            return fieldErrors;
        }

        private static List<ParameterError> of(final BindingResult bindingResult) {
            final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new ParameterError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}