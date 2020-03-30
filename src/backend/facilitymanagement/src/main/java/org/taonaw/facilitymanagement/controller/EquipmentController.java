package org.taonaw.facilitymanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.facilitymanagement.application.register_equipment.RegisterEquipmentAppService;
import org.taonaw.facilitymanagement.application.register_equipment.RegisterEquipmentRequest;
import org.taonaw.facilitymanagement.application.register_equipment.RegisterEquipmentResponse;
import org.taonaw.facilitymanagement.query.equipment.EquipmentDto;
import org.taonaw.facilitymanagement.query.equipment.IEquipmentQuery;

import java.util.List;

@RestController
@AllArgsConstructor
public class EquipmentController {
    @Autowired
    private final RegisterEquipmentAppService registerEquipmentAppService;
    @Autowired
    private final IEquipmentQuery equipmentQuery;

    @PostMapping("/equipments")
    public ResponseEntity<RegisterEquipmentResponse> registerEquipment
            (@RequestBody RegisterEquipmentRequest request, UriComponentsBuilder uriComponentsBuilder) {
        var response = registerEquipmentAppService.handle(request);
        var uri = uriComponentsBuilder.path("/equipments/{equipmentId}")
                .buildAndExpand(response.getEquipmentId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/equipments")
    public ResponseEntity<List<EquipmentDto>> getEquipments() {
        var equipments = equipmentQuery.getAll();
        return ResponseEntity.ok(equipments);
    }
}
