package ct.osaludes.messages.infrastructure;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {

    protected static final String YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd hh:mm:ss";

    public String getDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date date = getDate();
        return simpleDateFormat.format(date);
    }

    protected Date getDate() {
        return new Date();
    }
}
