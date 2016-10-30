package ct.osaludes.messages.infrastructure;

import ct.osaludes.messages.model.TimelineMessage;

import java.util.*;

public class MessageRepository {
    private Map<String, LinkedList<TimelineMessage>> allMessages = new LinkedHashMap<>();
    private final Clock clock;

    public MessageRepository(Clock clock) {
        this.clock = clock;
    }

    public void add(String alias, String message) {
        TimelineMessage timelineMessage = new TimelineMessage(alias, message, clock.getDateTime());
        LinkedList<TimelineMessage> userMessages = getTimelineMessagesByAlias(alias);
        userMessages.add(timelineMessage);
        allMessages.put(alias, userMessages);
    }

    public List<TimelineMessage> findAllByAlias(String alias) {
        return allMessages.get(alias);
    }

    public List<TimelineMessage> findAllByAlias(HashSet<String> followingUsers) {
        throw new RuntimeException("not implemented");
    }

    private LinkedList<TimelineMessage> getTimelineMessagesByAlias(String alias) {
        LinkedList<TimelineMessage> userMessages = allMessages.get(alias);
        if (userMessages == null) {
            userMessages = new LinkedList<>();
        }
        return userMessages;
    }
}
