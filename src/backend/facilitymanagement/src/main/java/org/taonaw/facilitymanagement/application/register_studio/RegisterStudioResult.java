package org.taonaw.facilitymanagement.application.register_studio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.model.studio.Studio;

@Getter
@Builder
@AllArgsConstructor
public class RegisterStudioResult {
    @NonNull private String studioId;

    static RegisterStudioResult of(Studio studio) {
        return builder()
                .studioId(studio.getStudioId().getValue())
                .build();
    }
}
