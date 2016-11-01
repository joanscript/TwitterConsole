package ct.osaludes.unitTest;

import ct.osaludes.messages.builders.*;
import ct.osaludes.users.builders.PostMessageStrategy;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class ProcessValueFactoryShould {
    @Test
    public void postCommandCreatesPostMessage() throws Exception {
        String argument = "->";
        ProcessStrategy processStrategy = ProcessValueFactory.constructFromArgument(argument);
        assertThat(processStrategy, instanceOf(PostMessageStrategy.class));
    }

    @Test
    public void followsCommandCreatesFollowUser() throws Exception {
        String argument = "follows";
        ProcessStrategy processStrategy = ProcessValueFactory.constructFromArgument(argument);
        assertThat(processStrategy, instanceOf(FollowUserStrategy.class));
    }

    @Test
    public void wallCommandGetsWallUser() throws Exception {
        String argument = "wall";
        ProcessStrategy processStrategy = ProcessValueFactory.constructFromArgument(argument);
        assertThat(processStrategy, instanceOf(ReadWallPrinterStrategy.class));
    }

    @Test
    public void readTimelineCommandReadsTimeline() throws Exception {
        String argument = "";
        ProcessStrategy processStrategy = ProcessValueFactory.constructFromArgument(argument);
        assertThat(processStrategy, instanceOf(ReadTimelinePrinterStrategy.class));
    }
}
