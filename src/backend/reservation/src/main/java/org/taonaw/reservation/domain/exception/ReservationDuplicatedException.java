package org.taonaw.reservation.domain.exception;

import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.UseTime;
import org.taonaw.reservation.domain.model.studio.StudioId;

import java.time.LocalDateTime;

@Getter
public class ReservationDuplicatedException extends DomainException {
    private final String studioId;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public ReservationDuplicatedException(@NonNull StudioId studioId, @NonNull UseTime useTime) {
        super("予約が重複しています。");
        this.studioId = studioId.getValue();
        this.startDateTime = useTime.getStart();
        this.endDateTime = useTime.getEnd();
    }
}
