package org.taonaw.managementsite.controller.reservation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.taonaw.managementsite.application.reservation.ReservationService;
import org.taonaw.managementsite.application.reservation.reservestudio.ReserveStudioRequest;
import org.taonaw.managementsite.controller.reservation.form.Equipment;
import org.taonaw.managementsite.controller.reservation.form.ReserveStudioForm;

import java.util.*;

@Controller
@AllArgsConstructor
public class ReservationController {

    @Autowired
    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public String list(Model model) {
        return "reservation/list";
    }

    @GetMapping("/reservations/new")
    public String newReservation(Model model) {
        var form = new ReserveStudioForm();
        form.getStudios().putAll(getAccounts());
        form.getEquipments().addAll(getEquipments());
        model.addAttribute("reserveStudioForm", form);
        return "reservation/new";
    }

    @PostMapping("/reservations")
    public String newReservation(@ModelAttribute @Validated ReserveStudioForm form,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            form.getStudios().putAll(getAccounts());
            model.addAttribute("validationError", "不正な入力があります。");
            return "reservation/new";
        }

        var request = ReserveStudioRequest.builder()
                .studioId(form.getStudioId())
                .startDateTime(form.getStartDateTime())
                .endDateTime(form.getEndDateTime())
                .userName(form.getUserName())
                .userPhoneNumber(form.getUserPhoneNumber())
                .numberOfUsers(form.getNumberOfUsers())
                .practiceType(form.getPracticeType())
                .equipmentIds(form.getEquipmentIds())
                .build();

        var response = reservationService.reserveStudio(request);

        // TODO：予約の重複エラーチェック

        // TODO：機材の在庫切れエラーチェック

        return "redirect:/reservations";
    }

    private List<Equipment> getEquipments() {
        var equipments = new ArrayList<Equipment>();
        equipments.add(new Equipment("1", "アンプ", "マーシャル", 0));
        equipments.add(new Equipment("1", "アンプ", "ローランド", 0));
        return equipments;
    }

    private Map<String, String> getAccounts() {
        var accounts = new HashMap<String, String>();
        accounts.put("1", "A（10 畳）");
        accounts.put("2", "A（13 畳）");
        accounts.put("3", "A（15 畳）");
        return accounts;
    }
}
