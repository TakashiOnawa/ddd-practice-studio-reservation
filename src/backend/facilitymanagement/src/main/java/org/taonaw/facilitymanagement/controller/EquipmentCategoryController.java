package org.taonaw.facilitymanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.facilitymanagement.application.change_equipmentcategory.ChangeEquipmentCategoryAppService;
import org.taonaw.facilitymanagement.application.change_equipmentcategory.ChangeEquipmentCategoryRequest;
import org.taonaw.facilitymanagement.application.change_equipmentcategory.ChangeEquipmentCategoryResponse;
import org.taonaw.facilitymanagement.application.register_equipmentcategory.RegisterEquipmentCategoryAppService;
import org.taonaw.facilitymanagement.application.register_equipmentcategory.RegisterEquipmentCategoryRequest;

@RestController
@AllArgsConstructor
public class EquipmentCategoryController {
    @Autowired
    private final RegisterEquipmentCategoryAppService registerEquipmentCategoryAppService;
    @Autowired
    private final ChangeEquipmentCategoryAppService changeEquipmentCategoryAppService;

    @PostMapping("/equipment_categories")
    public ResponseEntity<Void> registerEquipmentCategory(
            @RequestBody RegisterEquipmentCategoryRequest request,
            UriComponentsBuilder uriComponentsBuilder) {
        var response = registerEquipmentCategoryAppService.handle(request);
        var uri = uriComponentsBuilder.path("/equipment_categories/{equipmentCategoryId}")
                .buildAndExpand(response.getEquipmentCategoryId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/equipment_categories/{equipmentCategoryId}")
    public ResponseEntity<ChangeEquipmentCategoryResponse> changeEquipmentCategory(
            @PathVariable String equipmentCategoryId,
            @RequestBody ChangeEquipmentCategoryRequest request) {
        var response = changeEquipmentCategoryAppService.handle(request);
        return ResponseEntity.ok(response);
    }
}
