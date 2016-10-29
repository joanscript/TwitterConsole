package ct.osaludes.messages.actions;

import ct.osaludes.messages.infrastructure.MessageRepository;

public class PostMessage {

    private MessageRepository messageRepository;

    public PostMessage(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void post(String alias, String message) {
        messageRepository.add(alias, message);
    }
}
