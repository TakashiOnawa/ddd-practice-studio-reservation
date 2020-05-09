package org.taonaw.facilitymanagement.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taonaw.facilitymanagement.api.error.ErrorCode;
import org.taonaw.facilitymanagement.api.error.ErrorResponse;
import org.taonaw.facilitymanagement.application.command.change_tenantsetting.ChangeTenantSettingAppService;
import org.taonaw.facilitymanagement.application.command.change_tenantsetting.ChangeTenantSettingCommand;
import org.taonaw.facilitymanagement.application.command.change_tenantsetting.ChangeTenantSettingResult;
import org.taonaw.facilitymanagement.application.query.tenantsetting.CancellationFeeRateDto;
import org.taonaw.facilitymanagement.application.query.tenantsetting.ITenantSettingQuery;
import org.taonaw.facilitymanagement.application.query.tenantsetting.TenantSettingDto;
import org.taonaw.facilitymanagement.domain.exception.ChangeCancellationFeeRatesException;

import java.util.List;

@RestController
@RequestMapping("/tenant_setting")
@AllArgsConstructor
@ControllerAdvice
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


    @ExceptionHandler(ChangeCancellationFeeRatesException.class)
    public ResponseEntity<ErrorResponse> hnandleException(ChangeCancellationFeeRatesException e) {
        var response = new ErrorResponse(ErrorCode.CanNotChangeCancellationFeeRates);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
