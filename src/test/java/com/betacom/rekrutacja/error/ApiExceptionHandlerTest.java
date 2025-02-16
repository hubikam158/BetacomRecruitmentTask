package com.betacom.rekrutacja.error;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiExceptionHandlerTest {

    private final ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();

    @Test
    void shouldHandleUserAlreadyExistsException() {

        // given
        final String message = "message";
        final UserAlreadyExistsException exception = new UserAlreadyExistsException(message);
        final HttpStatusCode expectedCode = HttpStatus.CONFLICT;

        // when
        final ResponseEntity<ErrorResponse> response = apiExceptionHandler.handleException(exception);

        // then
        assertThat(response.getStatusCode()).isEqualTo(expectedCode);
        assertThat(Objects.requireNonNull(response.getBody()).getStatus()).isEqualTo(expectedCode.value());
        assertThat(Objects.requireNonNull(response.getBody()).getDescription()).isEqualTo(message);
    }
}
