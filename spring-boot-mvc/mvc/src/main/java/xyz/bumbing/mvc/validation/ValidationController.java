package xyz.bumbing.mvc.validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/validation")
@Slf4j
public class ValidationController {

    @GetMapping("1")
    public String valid1(@Valid ValidationRequestDto requestDto) {
        log.info("dto : " + requestDto);
        return requestDto.toString();
    }

    @Data
    public static class ValidationRequestDto {
        @NotBlank
        private String name;
    }

    @GetMapping("2")
    public String valid2(@Valid @RequestBody CustomValidationRequestDto requestDto) {
        log.info("dto : " + requestDto);
        return requestDto.toString();
    }

    @Data
    public static class CustomValidationRequestDto {
        @Password
        private String password;
    }

}
