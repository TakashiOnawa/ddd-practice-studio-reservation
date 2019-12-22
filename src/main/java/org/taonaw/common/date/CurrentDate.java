package org.taonaw.common.date;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CurrentDate {
    public Date Now() {
        return new Date();
    }
}
