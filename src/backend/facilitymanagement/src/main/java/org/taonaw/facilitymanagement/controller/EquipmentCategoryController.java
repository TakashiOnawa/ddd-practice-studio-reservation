package org.taonaw.facilitymanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.facilitymanagement.application.register_equipmentcategory.RegisterEquipmentCategoryAppService;
import org.taonaw.facilitymanagement.application.register_equipmentcategory.RegisterEquipmentCategoryRequest;
import org.taonaw.facilitymanagement.application.register_equipmentcategory.RegisterEquipmentCategoryResponse;

@RestController
@AllArgsConstructor
public class EquipmentCategoryController {
    @Autowired
    private final RegisterEquipmentCategoryAppService registerEquipmentCategoryAppService;

    @PostMapping("/equipment_categories")
    public ResponseEntity<RegisterEquipmentCategoryResponse> registerEquipmentCategory
            (@RequestBody RegisterEquipmentCategoryRequest request, UriComponentsBuilder uriComponentsBuilder) {
        var response = registerEquipmentCategoryAppService.handle(request);
        var uri = uriComponentsBuilder.path("/equipment_categories/{equipmentCategoryId}")
                .buildAndExpand(response.getEquipmentCategoryId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
