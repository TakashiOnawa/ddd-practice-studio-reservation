package org.taonaw.managementsite.presentation.reservation.form;

import lombok.Data;
import org.taonaw.managementsite.application.facilitymanagement.query.StudioDto;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReserveStudioForm {

    private static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm";

    public ReserveStudioForm(){}

    @NotEmpty(message = "スタジオを選択してください")
    private String studioId;

    @NotEmpty(message = "利用開始日を入力してください")
    private String startDate;

    @NotEmpty(message = "利用開始時間を入力してください")
    private String startTime;

    @Min(value = 1, message = "利用時間数は 1 時間以上を入力してください")
    private int hourQuantity;

    @NotEmpty(message = "利用者名を入力してください")
    private String userName;

    @NotNull
    @Size(min = 5, max = 20, message = "電話番号は 5 文字以上 20 文字以内")
    @Pattern(regexp = "[0-9]*", message = "電話番号は数字のみで入力してください")
    private String userPhoneNumber;

    @Min(value = 1, message = "利用人数は 1 名以上を入力してください")
    private int numberOfUsers;

    private int practiceType = 1;

    private final List<Equipment> equipments = new ArrayList<>();

    private final List<StudioDto> studios = new ArrayList<>();

    public LocalDateTime getStartDateTime() {
        return toLocalDateTime(startDate, startTime);
    }

    public LocalDateTime getEndDateTime() {
        return getStartDateTime().plusHours(hourQuantity);
    }

    public List<String> getEquipmentIds() {
        return equipments.stream()
                .flatMap(item -> item.getIds().stream())
                .collect(Collectors.toList());
    }

    private LocalDateTime toLocalDateTime(String date, String time) {
        var format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return LocalDateTime.parse(date + " " + time, format);
    }

    public void refreshEquipmentsOutOfStocks(List<String> outOfStocksEquipmentIds) {
        for (var equipment : equipments) {
            if (outOfStocksEquipmentIds.contains(equipment.getId())) {
                equipment.setOutOfStocks(true);
            } else {
                equipment.setOutOfStocks(false);
            }
        }
    }
}
