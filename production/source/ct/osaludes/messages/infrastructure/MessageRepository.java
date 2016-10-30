package ct.osaludes.messages.infrastructure;

import ct.osaludes.messages.model.TimelineMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageRepository {
    private Map<String, List<TimelineMessage>> usersMessages = new HashMap<>();

    public void add(String alias, String message) {
        String date = "2001/1/1";
        TimelineMessage timelineMessage = new TimelineMessage(message, date);
        List<TimelineMessage> userMessages = new ArrayList<>();
        userMessages.add(timelineMessage);
        usersMessages.put(alias, userMessages);
    }

    public List<TimelineMessage> findAllByAlias(String alias) {
        return usersMessages.get(alias);
    }

    public List<TimelineMessage> findAllByAlias(List<String> followingUsers) {
        throw new RuntimeException("not implemented");
    }
}
