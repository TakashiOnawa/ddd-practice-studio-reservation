package org.taonaw.managementsite.controller.reservation.form;

import lombok.Data;
import org.taonaw.managementsite.application.facilitymanagement.query.StudioDto;

import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class ReserveStudioForm {

    private static final String DATE_TIME_FORMAT = "yyyy/MM/dd hh:mm";

    public ReserveStudioForm(){}

    @NotEmpty(message = "スタジオを選択してください")
    private String studioId;

    @NotEmpty(message = "利用開始日を入力してください")
    private String startDate;

    @NotEmpty(message = "利用開始時間を入力してください")
    private String startTime;

    @NotEmpty(message = "利用終了日を入力してください")
    private String endDate;

    @NotEmpty(message = "利用終了時間を入力してください")
    private String endTime;

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

    public Date getStartDateTime() {
        return toDate(startDate, startTime);
    }

    public Date getEndDateTime() {
        return toDate(endDate, endTime);
    }

    public List<String> getEquipmentIds() {
        return equipments.stream()
                .flatMap(item -> item.getIds().stream())
                .collect(Collectors.toList());
    }

    private Date toDate(String date, String time) {
        try {
            var format = new SimpleDateFormat(DATE_TIME_FORMAT);
            return format.parse(date + " " + time);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
