package org.taonaw.reservation.domain.model.reservations.specification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.taonaw.reservation.domain.model.reservations.TimePeriodOfUsage;
import org.taonaw.reservation.domain.model.reservations.specificatiions.TimePeriodUnitOfUsageSpecification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePeriodUnitOfUsageSpecificationTest {
    @Test
    public void isSatisfied_60分単位の場合はtrueを返却する() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        Assertions.assertTrue(isSatisfiedTest(df.parse("201901010000"), df.parse("201901010100")));
        Assertions.assertTrue(isSatisfiedTest(df.parse("201901010000"), df.parse("201901010200")));
    }

    @Test
    public void isSatisfied_60分単位ではない場合はfalseを返却する() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        Assertions.assertFalse(isSatisfiedTest(df.parse("201901010001"), df.parse("201901010100")));
        Assertions.assertFalse(isSatisfiedTest(df.parse("201901010001"), df.parse("201901010101")));
        Assertions.assertFalse(isSatisfiedTest(df.parse("201901010000"), df.parse("201901010059")));
        Assertions.assertFalse(isSatisfiedTest(df.parse("201901010000"), df.parse("201901010101")));
    }

    private boolean isSatisfiedTest(Date start, Date end) {
        TimePeriodUnitOfUsageSpecification target = new TimePeriodUnitOfUsageSpecification();
        TimePeriodOfUsage timePeriodOfUsage = new TimePeriodOfUsage(start, end);
        return target.isSatisfied(timePeriodOfUsage);
    }
}
