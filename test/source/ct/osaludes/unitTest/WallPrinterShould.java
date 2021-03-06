package ct.osaludes.unitTest;

import ct.osaludes.messages.model.TimelineMessage;
import ct.osaludes.messages.infrastructure.Console;
import ct.osaludes.messages.ui.FormatDateTime;
import ct.osaludes.messages.ui.WallPrinter;
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
public class WallPrinterShould {
    private WallPrinter wallPrinter;
    @Mock Console console;
    @Mock FormatDateTime formatDateTime;

    @Before
    public void setUp() throws Exception {
        wallPrinter = new WallPrinter(console, formatDateTime);
    }

    @Test
    public void shouldPrintMessagesInReversedOrder() throws Exception {
        String alias = "maria";
        String message2 = "no no";
        String date2 = "2016/10/30 17:05";

        String alias2 = "john";
        String message1 = "blablabl";
        String date1 = "2016/10/30 17:00";

        String message3 = "my last message";
        String date3 = "2016/10/30 17:08";

        TimelineMessage timelineMessage1 = getTimelineMessage(alias2, message1, date1);
        TimelineMessage timelineMessage2 = getTimelineMessage(alias, message2, date2);
        TimelineMessage timelineMessage3 = getTimelineMessage(alias2, message3, date3);
        List<TimelineMessage> timelineMessages = new LinkedList<>();
        timelineMessages.add(timelineMessage1);
        timelineMessages.add(timelineMessage2);
        timelineMessages.add(timelineMessage3);

        given(formatDateTime.format(date1)).willReturn("(10 minutes ago)");
        given(formatDateTime.format(date2)).willReturn("(5 minutes ago)");
        given(formatDateTime.format(date3)).willReturn("(2 minutes ago)");

        wallPrinter.print(timelineMessages);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).print("john - my last message (2 minutes ago)");
        inOrder.verify(console).print("maria - no no (5 minutes ago)");
        inOrder.verify(console).print("john - blablabl (10 minutes ago)");
    }

    private TimelineMessage getTimelineMessage(String alias, String message, String date) {
        return new TimelineMessage(alias, message, date);
    }
}
