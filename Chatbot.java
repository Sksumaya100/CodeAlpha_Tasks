import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Chatbot {
    private List<String> intents;
    private List<String> responses;

    public Chatbot() {
        intents = new ArrayList<>();
        responses = new ArrayList<>();
    }

    public void train(String intent, String response) {
        intents.add(intent);
        responses.add(response);
    }

    public String respond(String input) {
        String intent = getIntent(input.toLowerCase()); // Convert input to lowercase for case-insensitive matching
        if (intent != null) {
            return getResponse(intent);
        } else {
            return "I didn't understand that.";
        }
    }

    private String getIntent(String input) {
        // Basic intent recognition using keyword matching
        for (String intent : intents) {
            if (input.contains(intent.toLowerCase())) { // Convert intent to lowercase for case-insensitive matching
                return intent;
            }
        }
        return null;
    }

    private String getResponse(String intent) {
        // Retrieve response based on intent
        int index = intents.indexOf(intent);
        return responses.get(index);
    }

    public static void main(String[] args) {
        Chatbot chatbot = new Chatbot();

        // Training data
        chatbot.train("hello", "Hello! How are you?");
        chatbot.train("goodbye", "Goodbye! See you later.");

        // Test the chatbot
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine();
            System.out.println("Chatbot: " + chatbot.respond(input));
        }
    }
}
