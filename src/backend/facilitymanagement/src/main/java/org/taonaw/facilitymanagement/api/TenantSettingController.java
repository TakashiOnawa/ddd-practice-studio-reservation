package org.taonaw.facilitymanagement.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taonaw.facilitymanagement.application.change_tenantsetting.ChangeTenantSettingAppService;
import org.taonaw.facilitymanagement.application.change_tenantsetting.ChangeTenantSettingCommand;
import org.taonaw.facilitymanagement.application.change_tenantsetting.ChangeTenantSettingResult;
import org.taonaw.facilitymanagement.query.tenantsetting.CancellationFeeRateDto;
import org.taonaw.facilitymanagement.query.tenantsetting.ITenantSettingQuery;
import org.taonaw.facilitymanagement.query.tenantsetting.TenantSettingDto;

import java.util.List;

@RestController
@RequestMapping("/tenant_setting")
@AllArgsConstructor
public class TenantSettingController {
    @Autowired
    private final ChangeTenantSettingAppService changeTenantSettingAppService;
    @Autowired
    private final ITenantSettingQuery tenantSettingQuery;

    @PutMapping
    public ResponseEntity<ChangeTenantSettingResult> changeTenantSetting(
            @RequestBody ChangeTenantSettingCommand request) {
        var response = changeTenantSettingAppService.handle(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<TenantSettingDto> getTenantSetting() {
        var dto = tenantSettingQuery.get();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/cancellation_fee_rates")
    public ResponseEntity<List<CancellationFeeRateDto>> getCancellationFeeRates() {
        var dto = tenantSettingQuery.get();
        return ResponseEntity.ok(dto.getCancellationFeeRates());
    }
}
