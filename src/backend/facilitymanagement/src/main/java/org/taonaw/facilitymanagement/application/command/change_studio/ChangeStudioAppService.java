package org.taonaw.facilitymanagement.application.command.change_studio;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.facilitymanagement.domain.model.studio.*;

@Service
@AllArgsConstructor
public class ChangeStudioAppService {
    @Autowired
    private final IStudioRepository studioRepository;

    public ChangeStudioResult handle(ChangeStudioCommand command) {
        var studio = studioRepository.findBy(new StudioId(command.getStudioId())).orElseThrow();
        studio.changeName(new StudioName(command.getName()));
        studio.changeRoomSize(new StudioRoomSize(command.getRoomSize()));
        studio.changeStartTimeType(StartTimeType.from(command.getStartTimeType()));
        studioRepository.update(studio);

        return ChangeStudioResult.of(studio);
    }
}
