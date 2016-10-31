package ct.osaludes.messages.ui;

import ct.osaludes.messages.infrastructure.Console;
import ct.osaludes.messages.model.TimelineMessage;

public class TimelinePrinter extends Printer {

    public TimelinePrinter(Console console, FormatDateTime formatDateTime) {
        super(console, formatDateTime);
    }

    protected String getDateTimeFormat(TimelineMessage timelineMessage, FormatDateTime formatDateTime) {
        return timelineMessage.getMessage()
                + " "
                + formatDateTime.format(timelineMessage.getDate());
    }
}
