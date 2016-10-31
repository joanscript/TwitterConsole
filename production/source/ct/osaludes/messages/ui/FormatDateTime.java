package ct.osaludes.messages.ui;

import ct.osaludes.messages.infrastructure.Clock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FormatDateTime {
    private Clock clock;

    public FormatDateTime(Clock clock) {
        this.clock = clock;
    }

    public String format(String stringDate) {
        long now = getMillis(clock.getDateTime());
        long millis = getMillis(stringDate);
        long minutesDifference = getMinutesDifference(now, millis);
        return getFormattedText(minutesDifference);
    }

    private long getMillis(String stringDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Date date = simpleDateFormat.parse(stringDate);
            return date.getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    private long getMinutesDifference(long now, long millis) {
        long diffInMillis = millis - now;
        return TimeUnit.MINUTES.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    private String getFormattedText(long minutesDifference) {
        if (minutesDifference == 1) {
            return "(1 minute ago)";
        } else if (minutesDifference == 0) {
            return "(less than 1 minute ago)";
        } else {
            return "(" + minutesDifference + " minutes ago)";
        }
    }
}
