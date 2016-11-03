package ct.osaludes;

import ct.osaludes.messages.builders.ProcessStrategy;
import ct.osaludes.messages.builders.ProcessValueFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class launchApp {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to TwitterConsole. Enter your command:");
        System.out.println("Post message: alias -> message");
        System.out.println("Read timeline: alias");
        System.out.println("Read wall: alias wall");
        System.out.println("Follow user: alias follows alias2");
        System.out.println("Finish: exit");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        while (!command.equals("exit")) {
            String[] commandSplit = command.split(" ", 3);
            String argument1 = commandSplit[0];
            String action = "";
            if (commandSplit.length > 1 && commandSplit[1] != null) {
                action = commandSplit[1];
            }
            String argument2 = "";
            if (commandSplit.length > 2 && commandSplit[2] != null) {
                argument2 = commandSplit[2];
            }
            try {
                ProcessStrategy processStrategy = ProcessValueFactory.constructFromArgument(action);
                processStrategy.execute(argument1, argument2);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            command = br.readLine();
        }
        System.out.println("Goodbye!");
    }
}
