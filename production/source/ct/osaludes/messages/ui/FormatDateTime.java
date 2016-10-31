package ct.osaludes.messages.ui;

import com.sun.javafx.css.converters.EffectConverter;
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
        long diffInMillis = millis - now;

        return getFormattedText(diffInMillis);
    }

    private String getFormattedText(long diffInMillis) {
        long minutesDifference = TimeUnit.MINUTES.convert(diffInMillis, TimeUnit.MILLISECONDS);
        if (minutesDifference == 1) {
            return "(1 minute ago)";
        } else if (minutesDifference == 0) {
            return "(less than 1 minute ago)";
        } else {
            return "(" + minutesDifference + " minutes ago)";
        }
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
}
