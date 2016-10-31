package ct.osaludes.messages.infrastructure;

import ct.osaludes.messages.model.TimelineMessage;

import java.util.*;

public class MessageRepository {
    private static MessageRepository instance = null;
    private Map<String, LinkedList<TimelineMessage>> allMessages = new LinkedHashMap<>();
    private final Clock clock;

    public MessageRepository(Clock clock) {
        this.clock = clock;
    }

    public static MessageRepository getInstance(Clock clock) {
        if (instance == null) {
            instance = new MessageRepository(clock);
        }
        return instance;
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
        List<TimelineMessage> total = new LinkedList<>();
        for (String followingUser: followingUsers) {
            total.addAll(findAllByAlias(followingUser));
        }
        return total;
    }

    private LinkedList<TimelineMessage> getTimelineMessagesByAlias(String alias) {
        LinkedList<TimelineMessage> userMessages = allMessages.get(alias);
        if (userMessages == null) {
            userMessages = new LinkedList<>();
        }
        return userMessages;
    }
}
