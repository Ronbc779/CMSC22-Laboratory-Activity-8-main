// sends a text message to the staff (it only prints the message to the console, not a literal text message)
class SmsSender {
    void send(String number, String message) {
        System.out.println("  [SMS to " + number + "] " + message);
    }
}
