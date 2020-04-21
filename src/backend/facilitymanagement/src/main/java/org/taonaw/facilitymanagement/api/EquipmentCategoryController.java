package org.taonaw.facilitymanagement.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.facilitymanagement.application.command.change_equipmentcategory.ChangeEquipmentCategoryAppService;
import org.taonaw.facilitymanagement.application.command.change_equipmentcategory.ChangeEquipmentCategoryCommand;
import org.taonaw.facilitymanagement.application.command.change_equipmentcategory.ChangeEquipmentCategoryResult;
import org.taonaw.facilitymanagement.application.command.register_equipmentcategory.RegisterEquipmentCategoryAppService;
import org.taonaw.facilitymanagement.application.command.register_equipmentcategory.RegisterEquipmentCategoryCommand;

@RestController
@RequestMapping("/equipment_categories")
@AllArgsConstructor
public class EquipmentCategoryController {
    @Autowired
    private final RegisterEquipmentCategoryAppService registerEquipmentCategoryAppService;
    @Autowired
    private final ChangeEquipmentCategoryAppService changeEquipmentCategoryAppService;

    @PostMapping
    public ResponseEntity<Void> registerEquipmentCategory(
            @RequestBody RegisterEquipmentCategoryCommand request,
            UriComponentsBuilder uriComponentsBuilder) {
        var response = registerEquipmentCategoryAppService.handle(request);
        var uri = uriComponentsBuilder.path("/equipment_categories/{equipmentCategoryId}")
                .buildAndExpand(response.getEquipmentCategoryId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{equipmentCategoryId}")
    public ResponseEntity<ChangeEquipmentCategoryResult> changeEquipmentCategory(
            @PathVariable String equipmentCategoryId,
            @RequestBody ChangeEquipmentCategoryCommand request) {
        var response = changeEquipmentCategoryAppService.handle(request);
        return ResponseEntity.ok(response);
    }
}
