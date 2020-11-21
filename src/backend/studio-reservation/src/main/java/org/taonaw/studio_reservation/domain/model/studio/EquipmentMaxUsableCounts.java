package org.taonaw.studio_reservation.domain.model.studio;

import lombok.EqualsAndHashCode;
import org.taonaw.studio_reservation.domain.model.studio.error.EquipmentMaxUsableCountDuplicatedError;
import org.taonaw.studio_reservation.domain.shared.Assertion;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class EquipmentMaxUsableCounts {
    private final List<EquipmentMaxUsableCount> items;

    public EquipmentMaxUsableCounts(List<EquipmentMaxUsableCount> items) {
        Assertion.required(items);
        this.items = new ArrayList<>(items);
    }

    public static EquipmentMaxUsableCounts empty() {
        return new EquipmentMaxUsableCounts(new ArrayList<>());
    }

    public List<EquipmentMaxUsableCount> items() {
        return new ArrayList<>(items);
    }

    public Optional<Error> validateDuplicated() {
        var errorItems = items.stream()
                .filter(item -> items.stream().anyMatch(item::isDuplicated))
                .collect(Collectors.toList());
        if (errorItems.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new EquipmentMaxUsableCountDuplicatedError(errorItems));
    }
}
