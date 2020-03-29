package org.taonaw.facilitymanagement.application.register_studio;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.facilitymanagement.domain.model.studio.*;

@Service
@AllArgsConstructor
public class RegisterStudioAppService {
    @Autowired
    private final IStudioRepository studioRepository;

    public RegisterStudioResponse handle(RegisterStudioRequest request) {
        var studio = Studio.newStudio(
                new StudioName(request.getName()),
                new StudioRoomSize(request.getRoomSize()),
                StartTimeType.from(request.getStartTimeType()));

        studioRepository.add(studio);

        return RegisterStudioResponse.builder()
                .studioId(studio.getStudioId().getValue())
                .build();
    }
}
