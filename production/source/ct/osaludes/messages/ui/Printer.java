package ct.osaludes.messages.ui;

import ct.osaludes.messages.infrastructure.Console;
import ct.osaludes.messages.model.TimelineMessage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Printer {
    protected Console console;
    protected FormatDateTime formatDateTime;

    public Printer(Console console, FormatDateTime formatDateTime) {
        this.console = console;
        this.formatDateTime = formatDateTime;
    }

    public void print(List<TimelineMessage> timelineMessages) {

        timelineMessages.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        Iterator<String> iterator = timelineMessages.stream()
                .map(timelineMessage -> getDateTimeFormat(timelineMessage, formatDateTime))
                .collect(Collectors.toCollection(LinkedList::new)).iterator();
        while(iterator.hasNext()) {
            console.print(iterator.next());
        }
    }

    protected abstract String getDateTimeFormat(TimelineMessage timelineMessage, FormatDateTime formatDateTime);
}
