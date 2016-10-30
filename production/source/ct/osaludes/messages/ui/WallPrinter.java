package ct.osaludes.messages.ui;

import ct.osaludes.messages.model.TimelineMessage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
