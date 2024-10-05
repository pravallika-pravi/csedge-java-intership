import java.util.Scanner;
import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;

public class Chatbot {
    private Scanner scanner;

    public Chatbot() {
        scanner = new Scanner(System.in);
    }

    public void respond(String input) {
        String[] commands = input.split(" ");
        switch (commands[0]) {
            case "open":
                openApp(commands[1]);
                break;
            case "search":
                searchWeb(commands[1]);
                break;
            default:
                System.out.println("Invalid command. Try 'open <app>' or 'search <query>'");
        }
    }

    private void openApp(String appName) {
        try {
            Runtime.getRuntime().exec("open " + appName);
        } catch (IOException e) {
            System.out.println("Error opening " + appName);
        }
    }

    private void searchWeb(String query) {
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI("https://www.google.com/search?q=" + query);
            desktop.browse(uri);
        } catch (Exception e) {
            System.out.println("Error searching for " + query);
        }
    }

    public static void main(String[] args) {
        Chatbot chatbot = new Chatbot();
        while (true) {
            System.out.print("Chatbot: ");
            String input = chatbot.scanner.nextLine();
            chatbot.respond(input);
        }
    }
}