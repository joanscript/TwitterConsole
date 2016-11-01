package ct.osaludes.messages.builders;

public interface ProcessStrategy {
    void execute(String alias, String message);
}
