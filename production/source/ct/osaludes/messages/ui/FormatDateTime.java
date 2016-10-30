package ct.osaludes.messages.ui;

import ct.osaludes.messages.infrastructure.Clock;

public class FormatDateTime {
    private Clock clock;

    public FormatDateTime(Clock clock) {

        this.clock = clock;
    }

    public String format(String date) {
        throw new RuntimeException("not implemented");
    }
}
