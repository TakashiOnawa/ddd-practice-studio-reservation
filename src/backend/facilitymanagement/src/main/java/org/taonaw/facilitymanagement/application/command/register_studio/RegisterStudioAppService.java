package org.taonaw.facilitymanagement.application.command.register_studio;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.facilitymanagement.domain.model.studio.*;

@Service
@AllArgsConstructor
public class RegisterStudioAppService {
    @Autowired
    private final IStudioRepository studioRepository;

    public RegisterStudioResult handle(RegisterStudioCommand command) {
        var studio = Studio.newStudio(
                new StudioName(command.getName()),
                new StudioRoomSize(command.getRoomSize()),
                StartTimeType.from(command.getStartTimeType()));

        studioRepository.add(studio);

        return RegisterStudioResult.of(studio);
    }
}
