package org.taonaw.facilitymanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.facilitymanagement.application.register_equipment.RegisterEquipmentAppService;
import org.taonaw.facilitymanagement.application.register_equipment.RegisterEquipmentRequest;
import org.taonaw.facilitymanagement.application.register_equipment.RegisterEquipmentResponse;
import org.taonaw.facilitymanagement.application.register_equipmentcategory.RegisterEquipmentCategoryRequest;
import org.taonaw.facilitymanagement.application.register_equipmentcategory.RegisterEquipmentCategoryResponse;

@RestController
@AllArgsConstructor
public class EquipmentController {
    @Autowired
    private final RegisterEquipmentAppService registerEquipmentAppService;

    @PostMapping("/equipments")
    public ResponseEntity<RegisterEquipmentResponse> registerEquipment
            (@RequestBody RegisterEquipmentRequest request, UriComponentsBuilder uriComponentsBuilder) {
        var response = registerEquipmentAppService.handle(request);
        var uri = uriComponentsBuilder.path("/equipments/{equipmentId}")
                .buildAndExpand(response.getEquipmentId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
