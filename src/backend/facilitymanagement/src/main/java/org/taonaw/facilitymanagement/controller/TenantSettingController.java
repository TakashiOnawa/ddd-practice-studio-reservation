package org.taonaw.facilitymanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.taonaw.facilitymanagement.application.change_tenantsetting.ChangeTenantSettingAppService;
import org.taonaw.facilitymanagement.application.change_tenantsetting.ChangeTenantSettingRequest;
import org.taonaw.facilitymanagement.application.change_tenantsetting.ChangeTenantSettingResponse;

@RestController
@AllArgsConstructor
public class TenantSettingController {
    @Autowired
    private final ChangeTenantSettingAppService changeTenantSettingAppService;

    @PutMapping("/tenant_setting")
    public ResponseEntity<ChangeTenantSettingResponse> changeTenantSetting
            (@RequestBody ChangeTenantSettingRequest request) {
        var response = changeTenantSettingAppService.handle(request);
        return ResponseEntity.ok(response);
    }
}
