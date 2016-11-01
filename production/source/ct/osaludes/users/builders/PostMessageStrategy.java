package ct.osaludes.users.builders;

import ct.osaludes.messages.actions.PostMessage;
import ct.osaludes.messages.infrastructure.Clock;
import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.builders.ProcessStrategy;

public class PostMessageStrategy implements ProcessStrategy {
    private final PostMessage postMessage;

    public PostMessageStrategy() {
        postMessage = new PostMessage(MessageRepository.getInstance(new Clock()));
    }

    public void execute(String alias, String message) {
        postMessage.execute(alias, message);
    }
}
