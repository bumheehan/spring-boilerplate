package xyz.bumbing.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import xyz.bumbing.mvc.error.ErrorCode;
import xyz.bumbing.mvc.security.SecurityConfig;
import xyz.bumbing.mvc.validation.ValidationController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ValidationController.class)
@Import(SecurityConfig.class)
class ValidationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("pass - Spring annotation validation")
    void valid1() throws Exception {
        MultiValueMap<String, String> input = new LinkedMultiValueMap<>();

        input.put("name", List.of("1"));

        mvc.perform(get("/api/validation/1")
                        .contentType("application/json")
                        .queryParams(input))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("pass - password validation")
    void valid2_1() throws Exception {
        Map<String, String> input = new HashMap<>();

        input.put("password", "qwer!234");

        String content = objectMapper.writeValueAsString(input);

        mvc.perform(get("/api/validation/2")
                        .contentType("application/json")
                        .content(content))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("non pass - password validation")
    void valid2_2() throws Exception {
        Map<String, String> input = new HashMap<>();

        input.put("password", "qwer1234");

        String content = objectMapper.writeValueAsString(input);

        mvc.perform(get("/api/validation/2")
                        .contentType("application/json")
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ErrorCode.INVALID_PARAMETER.name()));
    }
}