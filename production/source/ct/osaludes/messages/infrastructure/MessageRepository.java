package ct.osaludes.messages.infrastructure;

import ct.osaludes.messages.model.TimelineMessage;

import java.util.List;

public class MessageRepository {
    public void add(String alias, String message) {

    }

    public List<TimelineMessage> findAllByAlias(String alias) {
        throw new RuntimeException("not implemented");
    }
}
