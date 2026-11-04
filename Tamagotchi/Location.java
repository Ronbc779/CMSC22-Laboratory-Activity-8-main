import java.util.ArrayList;
import java.util.List;

// a place the pet can be in, with the list of actions allowed there
class Location {
    String name;
    List<String> actions = new ArrayList<>();

    Location(String name, String... actionNames) {
        this.name = name;
        for (String actionName : actionNames) {
            actions.add(actionName);
        }
        actions.add("status");
    }
}
