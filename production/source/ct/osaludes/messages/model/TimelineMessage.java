package ct.osaludes.messages.model;

public class TimelineMessage {
    private String message;
    private String date;

    public TimelineMessage(String message, String date) {
        this.message = message;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimelineMessage that = (TimelineMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
