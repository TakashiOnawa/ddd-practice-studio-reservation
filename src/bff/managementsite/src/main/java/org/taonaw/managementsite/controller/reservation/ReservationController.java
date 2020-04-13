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
import org.taonaw.managementsite.application.facilitymanagement.FacilityManagementService;
import org.taonaw.managementsite.application.facilitymanagement.query.EquipmentDto;
import org.taonaw.managementsite.application.facilitymanagement.query.StudioDto;
import org.taonaw.managementsite.application.reservation.ReservationService;
import org.taonaw.managementsite.application.reservation.command.reserve_studio.ReserveStudioRequest;
import org.taonaw.managementsite.controller.reservation.form.Equipment;
import org.taonaw.managementsite.controller.reservation.form.ReserveStudioForm;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ReservationController {
    @Autowired
    private final ReservationService reservationService;
    @Autowired
    private final FacilityManagementService facilityManagementService;

    @GetMapping("/reservations")
    public String list(Model model) {
        return "reservation/list";
    }

    @GetMapping("/reservations/new")
    public String newReservation(Model model) {
        var form = new ReserveStudioForm();
        form.getStudios().addAll(getAccounts());
        form.getEquipments().addAll(getEquipments());
        model.addAttribute("reserveStudioForm", form);
        return "reservation/new";
    }

    @PostMapping("/reservations")
    public String newReservation(
            @ModelAttribute @Validated ReserveStudioForm form,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            form.getStudios().addAll(getAccounts());
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
        var response = facilityManagementService.getEquipments();
        var equipmentDtoList = response.getBody();
        Objects.requireNonNull(equipmentDtoList);
        equipmentDtoList.sort(Comparator.comparing(EquipmentDto::getCategoryName).thenComparing(EquipmentDto::getName));
        return equipmentDtoList.stream().map(Equipment::from).collect(Collectors.toList());
    }

    private List<StudioDto> getAccounts() {
        var response = facilityManagementService.getStudios();
        var studioDtoList = response.getBody();
        Objects.requireNonNull(studioDtoList);
        studioDtoList.sort(Comparator.comparing(StudioDto::getName));
        return studioDtoList;
    }
}
