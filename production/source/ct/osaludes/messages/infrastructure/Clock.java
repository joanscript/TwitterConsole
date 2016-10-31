package ct.osaludes.messages.infrastructure;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
    public String getDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = getDate();
        return simpleDateFormat.format(date);
    }

    protected Date getDate() {
        return new Date();
    }
}
