package org.taonaw.managementsite.presentation.reservation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.taonaw.managementsite.application.error.EquipmentOutOfStocksError;
import org.taonaw.managementsite.application.error.ErrorCode;
import org.taonaw.managementsite.application.error.ErrorResponse;
import org.taonaw.managementsite.application.facilitymanagement.FacilityManagementService;
import org.taonaw.managementsite.application.facilitymanagement.query.EquipmentDto;
import org.taonaw.managementsite.application.facilitymanagement.query.StudioDto;
import org.taonaw.managementsite.application.reservation.ReservationService;
import org.taonaw.managementsite.application.reservation.command.reserve_studio.ReserveStudioRequest;
import org.taonaw.managementsite.presentation.reservation.form.Equipment;
import org.taonaw.managementsite.presentation.reservation.form.ReserveStudioForm;

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
        form.getStudios().addAll(getStudios());
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
            return newReservationErrorProcess(form, model, "不正な入力があります。");
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

        try {
            reservationService.reserveStudio(request);
        } catch (HttpClientErrorException e) {
            var errorResponse = ErrorResponse.of(e);

            if (errorResponse.exists(ErrorCode.ReservationDuplicated)) {
                return newReservationErrorProcess(form, model, "予約が重複しています。");
            }

            if (errorResponse.exists(ErrorCode.EquipmentOutOfStocks)) {
                var equipmentOutOfStocksError = errorResponse
                        .getError(ErrorCode.EquipmentOutOfStocks, EquipmentOutOfStocksError.class)
                        .orElseThrow();
                model.addAttribute("equipmentError", "機材の在庫が余っていません。");
                form.refreshEquipmentsOutOfStocks(equipmentOutOfStocksError.getEquipmentIds());
                return newReservationErrorProcess(form, model, "不正な入力があります。");
            }

            // TODO:予約確認エラーのチェック

            throw e;
        }

        return "redirect:/reservations";
    }

    private String newReservationErrorProcess(ReserveStudioForm form, Model model, String errorMessage) {
        form.getStudios().addAll(getStudios());
        model.addAttribute("validationError", errorMessage);
        return "reservation/new";
    }

    private List<Equipment> getEquipments() {
        var response = facilityManagementService.getEquipments();
        var equipmentDtoList = response.getBody();
        Objects.requireNonNull(equipmentDtoList);
        equipmentDtoList.sort(Comparator.comparing(EquipmentDto::getCategoryName).thenComparing(EquipmentDto::getName));
        return equipmentDtoList.stream().map(Equipment::from).collect(Collectors.toList());
    }

    private List<StudioDto> getStudios() {
        var response = facilityManagementService.getStudios();
        var studioDtoList = response.getBody();
        Objects.requireNonNull(studioDtoList);
        studioDtoList.sort(Comparator.comparing(StudioDto::getName));
        return studioDtoList;
    }
}
