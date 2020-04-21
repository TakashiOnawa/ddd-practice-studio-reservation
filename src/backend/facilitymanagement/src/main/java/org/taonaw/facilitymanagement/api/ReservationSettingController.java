package org.taonaw.facilitymanagement.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.taonaw.facilitymanagement.query.reservationsetting.IReservationSettingQuery;
import org.taonaw.facilitymanagement.query.reservationsetting.ReservationSettingDto;

@RestController
@RequestMapping("/reservation_setting")
@AllArgsConstructor
public class ReservationSettingController {
    @Autowired
    private final IReservationSettingQuery reservationSettingQuery;

    @GetMapping(params = { "studio_id", "practice_type" })
    public ResponseEntity<ReservationSettingDto> getReservationSetting(
            @RequestParam("studio_id") String studioId,
            @RequestParam("practice_type") int practiceType) {
        var dto = reservationSettingQuery.getByStudioIdAndPracticeType(studioId, practiceType);
        return ResponseEntity.ok(dto);
    }
}
