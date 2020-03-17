package org.taonaw.reservation.infrastructure.repository;

import com.google.gson.Gson;
import org.springframework.stereotype.Repository;
import org.taonaw.reservation.domain.model.equipments.Equipment;
import org.taonaw.reservation.domain.model.equipments.IEquipmentRepository;
import org.taonaw.reservation.domain.model.reservations.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EquipmentRepository implements IEquipmentRepository {

    private static final List<Equipment> equipments = new ArrayList<>();

    public List<Equipment> findAll() {
        return equipments.stream()
                .map(this::deepCopy)
                .collect(Collectors.toList());
    }

    private Equipment deepCopy(Equipment equipment) {
        var gson = new Gson();
        return gson.fromJson(gson.toJson(equipment), Equipment.class);
    }
}
