package ct.osaludes.messages.ui;

import ct.osaludes.messages.model.TimelineMessage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TimelinePrinter {
    private Console console;
    private FormatDateTime formatDateTime;

    public TimelinePrinter(Console console, FormatDateTime formatDateTime) {
        this.console = console;
        this.formatDateTime = formatDateTime;
    }

    public void print(List<TimelineMessage> timelineMessages) {
        Iterator<String> iterator = timelineMessages.stream()
                .map(timelineMessage -> getDateTimeFormat(timelineMessage, formatDateTime))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator();
        while(iterator.hasNext()) {
            console.print(iterator.next());
        }
    }

    private String getDateTimeFormat(TimelineMessage timelineMessage, FormatDateTime formatDateTime) {
        return timelineMessage.getMessage()
                + " "
                + formatDateTime.format(timelineMessage.getDate());
    }
}