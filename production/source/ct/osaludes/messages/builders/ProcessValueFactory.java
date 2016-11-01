package ct.osaludes.messages.builders;

import ct.osaludes.users.builders.PostMessageStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProcessValueFactory {
    private static final Map<String, ProcessStrategy> PROCESS = createProcessMap();
    private static Map<String, ProcessStrategy> createProcessMap() {
        Map<String, ProcessStrategy> result = new HashMap<>();
        result.put("->", new PostMessageStrategy());
        result.put("", new ReadTimelinePrinterStrategy());
        result.put("follows", new FollowUserStrategy());
        result.put("wall", new ReadWallPrinterStrategy());
        return Collections.unmodifiableMap(result);
    }

    public static ProcessStrategy constructFromArgument(String argument) throws RuntimeException{
        if (PROCESS.containsKey(argument)) {
            return PROCESS.get(argument);
        }
        throw new RuntimeException("command not exists");
    }
}
