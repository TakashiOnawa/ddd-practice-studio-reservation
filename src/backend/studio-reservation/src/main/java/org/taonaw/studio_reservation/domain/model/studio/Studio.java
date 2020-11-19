package org.taonaw.studio_reservation.domain.model.studio;

import lombok.NonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Studio {
    private StudioId id;
    private StudioName name;
    private StartTime startTime;
    private Set<EquipmentMaxUsableCount> equipmentMaxUsableCounts;

    private Studio(@NonNull StudioId id) {
        this.id = id;
    }

    public static Studio create(
            @NonNull StudioName name,
            @NonNull StartTime startTime,
            @NonNull Set<EquipmentMaxUsableCount> equipmentMaxUsableCounts) {

        var instance = new Studio(StudioId.newId());
        instance.name = name;
        instance.startTime = startTime;
        instance.equipmentMaxUsableCounts = new HashSet<>(equipmentMaxUsableCounts);
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
