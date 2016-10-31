package ct.osaludes.messages.ui;

import ct.osaludes.messages.infrastructure.Console;
import ct.osaludes.messages.model.TimelineMessage;

public class WallPrinter extends Printer {

    public WallPrinter(Console console, FormatDateTime formatDateTime) {
        super(console, formatDateTime);
    }

    @Override
    protected String getDateTimeFormat(TimelineMessage timelineMessage, FormatDateTime formatDateTime) {
        return timelineMessage.getAlias()
                + " - "
                + timelineMessage.getMessage()
                + " "
                + formatDateTime.format(timelineMessage.getDate());
    }
}
