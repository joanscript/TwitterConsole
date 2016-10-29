package ct.osaludes.messages.actions;

import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.model.TimelineMessage;

import java.util.List;

public class ReadTimeline {
    private MessageRepository messageRepository;

    public ReadTimeline(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<TimelineMessage> execute(String alias) {
        return messageRepository.findAllByAlias(alias);
    }
}
