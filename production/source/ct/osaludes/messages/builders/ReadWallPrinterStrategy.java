package ct.osaludes.messages.builders;

import ct.osaludes.messages.actions.ReadWall;
import ct.osaludes.messages.builders.ProcessStrategy;
import ct.osaludes.messages.infrastructure.Clock;
import ct.osaludes.messages.infrastructure.Console;
import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.model.TimelineMessage;
import ct.osaludes.messages.ui.FormatDateTime;
import ct.osaludes.messages.ui.WallPrinter;
import ct.osaludes.users.model.FollowingUsersRepository;

import java.util.List;

public class ReadWallPrinterStrategy implements ProcessStrategy {
    private final ReadWall readWall;
    private final WallPrinter printer;

    public ReadWallPrinterStrategy() {
        readWall = new ReadWall(MessageRepository.getInstance(new Clock()), FollowingUsersRepository.getInstance());
        printer = new WallPrinter(new Console(), new FormatDateTime(new Clock()));
    }

    public void execute(String alias, String message) {
        List<TimelineMessage> timelineMessages = readWall.execute(alias);
        printer.print(timelineMessages);
    }
}
