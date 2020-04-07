package org.taonaw.facilitymanagement.application.change_studio;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.facilitymanagement.domain.model.studio.*;

@Service
@AllArgsConstructor
public class ChangeStudioAppService {
    @Autowired
    private final IStudioRepository studioRepository;

    public ChangeStudioResponse handle(ChangeStudioRequest request) {
        var studio = studioRepository.findBy(new StudioId(request.getStudioId())).orElseThrow();
        studio.changeName(new StudioName(request.getName()));
        studio.changeRoomSize(new StudioRoomSize(request.getRoomSize()));
        studio.changeStartTimeType(StartTimeType.from(request.getStartTimeType()));
        studioRepository.update(studio);

        return ChangeStudioResponse.builder()
                .studioId(studio.getStudioId().getValue())
                .name(studio.getName().getValue())
                .roomSize(studio.getRoomSize().getValue())
                .startTimeType(studio.getStartTimeType().getValue())
                .build();
    }
}
