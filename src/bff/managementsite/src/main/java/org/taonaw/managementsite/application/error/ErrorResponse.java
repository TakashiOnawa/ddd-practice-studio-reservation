package org.taonaw.managementsite.application.error;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private List<ErrorInfo> errors;

    public static ErrorResponse ofOrElseThrow(@NonNull RestClientResponseException e) {
        var responseBodyAsString = e.getResponseBodyAsString();
        if (responseBodyAsString.isEmpty()) {
            throw e;
        }

        try {
            var gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            return gson.fromJson(responseBodyAsString, ErrorResponse.class);
        } catch (JsonSyntaxException ex) {
            throw e;
        }
    }

    public boolean exists(@NonNull ErrorCode errorCode) {
        return errors != null && errors.stream().anyMatch(item -> item.is(errorCode));
    }
}
