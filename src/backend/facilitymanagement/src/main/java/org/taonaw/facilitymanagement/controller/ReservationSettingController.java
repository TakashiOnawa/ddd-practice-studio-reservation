package org.taonaw.facilitymanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.taonaw.facilitymanagement.query.reservationsetting.IReservationSettingQuery;
import org.taonaw.facilitymanagement.query.reservationsetting.ReservationSettingDto;

@RestController
@AllArgsConstructor
public class ReservationSettingController {
    @Autowired
    private final IReservationSettingQuery reservationSettingQuery;

    @GetMapping(value = "/reservation_setting", params = { "studio_id", "practice_type" })
    public ResponseEntity<ReservationSettingDto> getReservationSetting(@RequestParam("studio_id") String studioId,
                                                                       @RequestParam("practice_type") int practiceType) {
        var dto = reservationSettingQuery.getByStudioAndPracticeType(studioId, practiceType);
        return ResponseEntity.ok(dto);
    }
}
