package org.taonaw.managementsite.controller.reservation.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "電話番号を入力してください")
    private String userPhoneNumber;

    @Min(value = 1, message = "利用人数は 1 名以上を入力してください")
    private int numberOfUsers;

    private int practiceType = 1;

    private final List<Equipment> equipments = new ArrayList<>();

    private final Map<String, String> studios = new HashMap<>();

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
