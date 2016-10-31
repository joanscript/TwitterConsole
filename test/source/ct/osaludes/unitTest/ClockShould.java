package ct.osaludes.unitTest;

import ct.osaludes.messages.infrastructure.Clock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockShould {
    private Clock clock;

    @Before
    public void setUp() throws Exception {
        clock = new TestClock();
    }

    @Test
    public void returnDateAsddmmyyyyhhmmss() throws Exception {
        String actual = clock.getDateTime();

        Assert.assertEquals("2016/07/07 12:12:12", actual);
    }

    private class TestClock extends Clock {
        protected Date getDate() {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
                return simpleDateFormat.parse("2016/07/07 12:12:12");
            } catch (ParseException e) {
                return new Date();
            }
        }
    }
}


