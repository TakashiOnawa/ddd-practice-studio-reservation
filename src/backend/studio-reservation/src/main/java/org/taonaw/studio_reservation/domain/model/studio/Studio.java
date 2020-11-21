package org.taonaw.studio_reservation.domain.model.studio;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;

import java.util.Objects;

public class Studio {
    private StudioId id;
    private StudioName name;
    private StartTime startTime;
    private EquipmentMaxUsableCounts equipmentMaxUsableCounts = EquipmentMaxUsableCounts.empty();

    private Studio(@NonNull StudioId id) {
        this.id = id;
    }

    public static Studio create(
            @NonNull StudioName name,
            @NonNull StartTime startTime,
            @NonNull EquipmentMaxUsableCounts equipmentMaxUsableCounts) {

        var errorNotification = new ErrorNotification();
        errorNotification.addError(equipmentMaxUsableCounts.validateDuplicated());
        errorNotification.throwIfHasErrors("スタジオ内容に不備があります。");

        var instance = new Studio(StudioId.newId());
        instance.name = name;
        instance.startTime = startTime;
        instance.equipmentMaxUsableCounts = equipmentMaxUsableCounts;
        return instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studio studio = (Studio) o;
        return id.equals(studio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
