package org.taonaw.managementsite.application.facilitymanagement;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.taonaw.managementsite.application.facilitymanagement.command.change_tenantsetting.ChangeTenantSettingRequest;
import org.taonaw.managementsite.application.facilitymanagement.command.change_tenantsetting.ChangeTenantSettingResponse;
import org.taonaw.managementsite.application.facilitymanagement.command.register_equipment.RegisterEquipmentRequest;
import org.taonaw.managementsite.application.facilitymanagement.command.register_equipmentcategory.RegisterEquipmentCategoryRequest;
import org.taonaw.managementsite.application.facilitymanagement.command.register_studio.RegisterStudioRequest;
import org.taonaw.managementsite.application.facilitymanagement.query.EquipmentDto;
import org.taonaw.managementsite.application.facilitymanagement.query.StudioDto;
import org.taonaw.managementsite.application.facilitymanagement.query.TenantSettingDto;

import java.util.List;

@Service
@AllArgsConstructor
public class FacilityManagementService {
    @Autowired
    @Qualifier("facilityManagementRestOptions")
    private final RestOperations facilityManagementRestOptions;

    public ResponseEntity<ChangeTenantSettingResponse> changeTenantSetting(ChangeTenantSettingRequest request) {
        var uri = "/tenant_setting";
        return facilityManagementRestOptions.postForEntity(uri, request, ChangeTenantSettingResponse.class);
    }

    public ResponseEntity<Void> registerEquipment(RegisterEquipmentRequest request) {
        var uri = "/equipments";
        return facilityManagementRestOptions.postForEntity(uri, request, Void.class);
    }

    public ResponseEntity<Void> registerEquipmentCategory(RegisterEquipmentCategoryRequest request) {
        var uri = "/equipment_categories";
        return facilityManagementRestOptions.postForEntity(uri, request, Void.class);
    }

    public ResponseEntity<Void> registerStudio(RegisterStudioRequest request) {
        var uri = "/studios";
        return facilityManagementRestOptions.postForEntity(uri, request, Void.class);
    }

    public ResponseEntity<List<StudioDto>> getStudios() {
        var uri = "/studios";
        return facilityManagementRestOptions.exchange(uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<List<EquipmentDto>> getEquipments() {
        var uri = "/equipments";
        return facilityManagementRestOptions.exchange(uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<TenantSettingDto> getTenantSetting() {
        var uri = "/tenant_setting";
        return facilityManagementRestOptions.getForEntity(uri, TenantSettingDto.class);
    }
}
