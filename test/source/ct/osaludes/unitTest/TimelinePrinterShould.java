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
        String firstMessage = "blablabl";
        String firstDate = "2016/10/30 17:00";
        String secondMessage = "no no";
        String secondDate = "2016/10/30 17:05";
        TimelineMessage firstTimelineMessage = getTimelineMessage(alias, firstMessage, firstDate);
        TimelineMessage secondTimelineMessage = getTimelineMessage(alias, secondMessage, secondDate);
        List<TimelineMessage> timelineMessages = new LinkedList<>();
        timelineMessages.add(firstTimelineMessage);
        timelineMessages.add(secondTimelineMessage);

        given(formatDateTime.format(firstDate)).willReturn("(5 minutes ago)");
        given(formatDateTime.format(secondDate)).willReturn("(2 minutes ago)");

        timelinePrinter.print(timelineMessages);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).print("no no (2 minutes ago)");
        inOrder.verify(console).print("blablabl (5 minutes ago)");
    }

    private TimelineMessage getTimelineMessage(String alias, String message, String date) {
        return new TimelineMessage(alias, message, date);
    }
}
