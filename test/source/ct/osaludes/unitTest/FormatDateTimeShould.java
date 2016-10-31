package ct.osaludes.unitTest;

import ct.osaludes.messages.infrastructure.Clock;
import ct.osaludes.messages.ui.FormatDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class FormatDateTimeShould {
    @Mock Clock clock;
    private FormatDateTime formatDateTime;

    @Before
    public void setUp() throws Exception {
        formatDateTime = new FormatDateTime(clock);
    }

    @Test
    public void returnOneMinuteAgo() throws Exception {
        String datetime = "2016/10/30 16:59:00";
        given(clock.getDateTime()).willReturn("2016/10/30 17:00:00");

        String dateFormatted = formatDateTime.format(datetime);

        Assert.assertEquals("(1 minute ago)", dateFormatted);
    }

    @Test
    public void returnLessThanOneMinuteAgo() throws Exception {
        String datetime = "2016/10/30 17:00:00";
        given(clock.getDateTime()).willReturn("2016/10/30 17:01:30");

        String dateFormatted = formatDateTime.format(datetime);

        Assert.assertEquals("(1 minute ago)", dateFormatted);
    }

    @Test
    public void returnMoreThanOneMinuteAgo() throws Exception {
        String datetime = "2016/10/30 16:50:30";
        given(clock.getDateTime()).willReturn("2016/10/30 17:00:00");

        String dateFormatted = formatDateTime.format(datetime);

        Assert.assertEquals("(9 minutes ago)", dateFormatted);
    }
}
