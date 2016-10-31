package ct.osaludes.unitTest;

import ct.osaludes.messages.model.TimelineMessage;
import ct.osaludes.messages.infrastructure.Console;
import ct.osaludes.messages.ui.FormatDateTime;
import ct.osaludes.messages.ui.TimelinePrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class TimelinePrinterShould {
    private TimelinePrinter timelinePrinter;
    @Mock Console console;
    @Mock FormatDateTime formatDateTime;

    @Before
    public void setUp() throws Exception {
        timelinePrinter = new TimelinePrinter(console, formatDateTime);
    }

    @Test
    public void shouldPrintMessagesInReversedOrder() throws Exception {
        String alias = "john";
        String message = "no no";
        String date = "2016/10/30 17:05";
        String message2 = "blablabl";
        String date2 = "2016/10/30 17:00";
        TimelineMessage timelineMessage = getTimelineMessage(alias, message, date);
        TimelineMessage timelineMessage2 = getTimelineMessage(alias, message2, date2);
        List<TimelineMessage> timelineMessages = new LinkedList<>();
        timelineMessages.add(timelineMessage);
        timelineMessages.add(timelineMessage2);

        given(formatDateTime.format(date)).willReturn("(5 minutes ago)");
        given(formatDateTime.format(date2)).willReturn("(2 minutes ago)");

        timelinePrinter.print(timelineMessages);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).print("blablabl (2 minutes ago)");
        inOrder.verify(console).print("no no (5 minutes ago)");
    }

    private TimelineMessage getTimelineMessage(String alias, String message, String date) {
        return new TimelineMessage(alias, message, date);
    }
}
