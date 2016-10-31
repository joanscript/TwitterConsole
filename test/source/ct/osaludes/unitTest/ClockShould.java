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
        clock = new TestClock("2016/07/07 12:12:12");
    }

    @Test
    public void returnDateAsddmmyyyyhhmmss() throws Exception {
        String actual = clock.getDateTime();

        Assert.assertEquals("2016/07/07 12:12:12", actual);
    }

    private class TestClock extends Clock {
        private String stringDate;

        TestClock(String stringDate) {
            this.stringDate = stringDate;
        }

        protected Date getDate() {
            Date date = null;
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                date = simpleDateFormat.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
    }
}


