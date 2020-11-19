package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DayType {
    /** 平日 */
    WEEK_DAYS(1),
    /** 土日 */
    WEEKEND(2),
    /** 祝日 */
    PUBLIC_HOLIDAY(3),
    /** 土日祝日 */
    WEEKEND_AND_PUBLIC_HOLIDAY(4),

    SUNDAY(5),
    MONDAY(6),
    TUESDAY(7),
    WEDNESDAY(8),
    THURSDAY(9),
    FRIDAY(10),
    SATURDAY(11);

    private final int value;

    DayType(int value) {
        this.value = value;
    }

    public static final List<DayTypePattern> ALL_DAY_TYPE_PATTERNS;

    static {
        var patterns = new ArrayList<DayTypePattern>();
        patterns.add(new DayTypePattern(WEEK_DAYS, WEEKEND));
        patterns.add(new DayTypePattern(WEEK_DAYS, WEEKEND_AND_PUBLIC_HOLIDAY));
        patterns.add(new DayTypePattern(WEEK_DAYS, SATURDAY, SUNDAY));
        patterns.add(new DayTypePattern(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, WEEKEND));
        patterns.add(new DayTypePattern(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, WEEKEND_AND_PUBLIC_HOLIDAY));
        patterns.add(new DayTypePattern(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY));
        ALL_DAY_TYPE_PATTERNS = patterns.stream().collect(Collectors.toUnmodifiableList());
    }

    public static class DayTypePattern {
        private final List<DayType> dayTypes;

        private DayTypePattern(DayType... dayTypes) {
            this.dayTypes = new ArrayList<>(Arrays.asList(dayTypes));
        }

        public List<DayType> getDayTypes() {
            return new ArrayList<>(dayTypes);
        }
    }
}
