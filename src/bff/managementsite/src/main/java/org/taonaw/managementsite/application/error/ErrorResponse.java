package org.taonaw.managementsite.application.error;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.web.client.RestClientResponseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ErrorResponse {
    private final List<ErrorInformationWithJsonString> errors = new ArrayList<>();

    private ErrorResponse() {
    }

    private ErrorResponse(List<ErrorInformationWithJsonString> errors) {
        this.errors.addAll(errors);
    }

    public static ErrorResponse of(@NonNull RestClientResponseException e) {
        var responseBodyAsString = e.getResponseBodyAsString();
        if (responseBodyAsString.isEmpty()) {
            return new ErrorResponse();
        }

        try {
            var gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            var errors = gson.fromJson(responseBodyAsString, JsonObject.class).get("errors");
            if (errors == null) {
                return new ErrorResponse();
            }

            var errorInformationList = new ArrayList<ErrorInformationWithJsonString>();
            for (var jsonElement : errors.getAsJsonArray()) {
                var jsonString = jsonElement.toString();
                var errorInformation = gson.fromJson(jsonString, ErrorInformation.class);
                errorInformationList.add(new ErrorInformationWithJsonString(jsonString, errorInformation));
            }
            return new ErrorResponse(errorInformationList);
        } catch (JsonSyntaxException ex) {
            return new ErrorResponse();
        }
    }

    public boolean exists(@NonNull ErrorCode errorCode) {
        return errors.stream().anyMatch(item -> item.errorInfo.is(errorCode));
    }

    public <T extends ErrorInformation> Optional<T> getError(@NonNull ErrorCode errorCode, @NonNull Class<T> type) {
        var errorInfoWithJsonString = errors.stream().filter(item -> item.errorInfo.is(errorCode)).findFirst();
        if (errorInfoWithJsonString.isEmpty()) {
            return Optional.empty();
        }

        try {
            var gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            var error = gson.fromJson(errorInfoWithJsonString.get().getJsonString(), type);
            return Optional.of(error);
        } catch (JsonSyntaxException ex) {
            return Optional.empty();
        }
    }

    @Getter
    @AllArgsConstructor
    private static class ErrorInformationWithJsonString {
        @NonNull private final String jsonString;
        @NonNull private final ErrorInformation errorInfo;
    }
}
