package org.taonaw.facilitymanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.facilitymanagement.application.change_equipment.ChangeEquipmentAppService;
import org.taonaw.facilitymanagement.application.change_equipment.ChangeEquipmentRequest;
import org.taonaw.facilitymanagement.application.change_equipment.ChangeEquipmentResponse;
import org.taonaw.facilitymanagement.application.register_equipment.RegisterEquipmentAppService;
import org.taonaw.facilitymanagement.application.register_equipment.RegisterEquipmentRequest;
import org.taonaw.facilitymanagement.query.equipment.EquipmentDto;
import org.taonaw.facilitymanagement.query.equipment.IEquipmentQuery;

import java.util.List;

@RestController
@AllArgsConstructor
public class EquipmentController {
    @Autowired
    private final RegisterEquipmentAppService registerEquipmentAppService;
    @Autowired
    private final ChangeEquipmentAppService changeEquipmentAppService;
    @Autowired
    private final IEquipmentQuery equipmentQuery;

    @PostMapping("/equipments")
    public ResponseEntity<Void> registerEquipment(
            @RequestBody RegisterEquipmentRequest request,
            UriComponentsBuilder uriComponentsBuilder) {
        var response = registerEquipmentAppService.handle(request);
        var uri = uriComponentsBuilder.path("/equipments/{equipmentId}")
                .buildAndExpand(response.getEquipmentId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/equipments/{equipmentId}")
    public ResponseEntity<ChangeEquipmentResponse> changeEquipment(
            @PathVariable String equipmentId,
            @RequestBody ChangeEquipmentRequest request) {
        var response = changeEquipmentAppService.handle(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/equipments")
    public ResponseEntity<List<EquipmentDto>> getEquipments() {
        var equipments = equipmentQuery.getAll();
        return ResponseEntity.ok(equipments);
    }

    @GetMapping("/equipments/{equipmentId}")
    public ResponseEntity<EquipmentDto> getEquipment(@PathVariable String equipmentId) {
        var equipment = equipmentQuery.getByEquipmentId(equipmentId);
        return equipment.map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
    }
}
