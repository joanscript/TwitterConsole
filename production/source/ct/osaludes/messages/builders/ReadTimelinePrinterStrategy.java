package ct.osaludes.messages.builders;

import ct.osaludes.messages.actions.ReadTimeline;
import ct.osaludes.messages.builders.ProcessStrategy;
import ct.osaludes.messages.infrastructure.Clock;
import ct.osaludes.messages.infrastructure.Console;
import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.model.TimelineMessage;
import ct.osaludes.messages.ui.FormatDateTime;
import ct.osaludes.messages.ui.TimelinePrinter;

import java.util.List;

public class ReadTimelinePrinterStrategy implements ProcessStrategy {
    private final ReadTimeline readTimeline;
    private final TimelinePrinter timelinePrinter;

    public ReadTimelinePrinterStrategy() {
        readTimeline = new ReadTimeline(MessageRepository.getInstance(new Clock()));
        timelinePrinter = new TimelinePrinter(new Console(), new FormatDateTime(new Clock()));
    }

    public void execute(String alias, String message) {
        List<TimelineMessage> timelineMessages = readTimeline.execute(alias);
        timelinePrinter.print(timelineMessages);
    }
}
