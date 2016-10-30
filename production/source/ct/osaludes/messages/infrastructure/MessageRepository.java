package ct.osaludes.messages.infrastructure;

import ct.osaludes.messages.model.TimelineMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageRepository {
    private Map<String, List<TimelineMessage>> allMessages = new HashMap<>();
    private final Clock clock;

    public MessageRepository(Clock clock) {
        this.clock = clock;
    }

    public void add(String alias, String message) {
        TimelineMessage timelineMessage = new TimelineMessage(message, clock.getDateTime());
        List<TimelineMessage> userMessages = getTimelineMessagesByAlias(alias);
        userMessages.add(timelineMessage);
        allMessages.put(alias, userMessages);
    }

    public List<TimelineMessage> findAllByAlias(String alias) {
        return allMessages.get(alias);
    }

    public List<TimelineMessage> findAllByAlias(List<String> followingUsers) {
        throw new RuntimeException("not implemented");
    }

    private List<TimelineMessage> getTimelineMessagesByAlias(String alias) {
        List<TimelineMessage> userMessages = allMessages.get(alias);
        if (userMessages == null) {
            userMessages = new ArrayList<>();
        }
        return userMessages;
    }
}
