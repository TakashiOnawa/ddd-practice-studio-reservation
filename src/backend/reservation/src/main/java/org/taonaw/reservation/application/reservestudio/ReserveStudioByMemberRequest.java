package org.taonaw.reservation.application.reservestudio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Builder
@AllArgsConstructor
public class ReserveStudioByMemberRequest {
    @NonNull
    private String memberId;
    private int practiceType;
    @NonNull
    private String studioId;
    @NonNull
    private Date startDateTime;
    @NonNull
    private Date endDateTime;
    private int numberOfUsers;
    @NonNull
    private Collection<String> equipments;

    public List<EquipmentId> getEquipmentIds() {
        return this.equipments.stream().map(EquipmentId::new).collect(Collectors.toList());
    }
}
