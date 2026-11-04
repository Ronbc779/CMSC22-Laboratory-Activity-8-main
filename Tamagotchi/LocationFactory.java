// creates a Location from its name, so Game does not need to know what each place allows
class LocationFactory {
    // returns null for a place that does not exist.
    static Location create(String locationName) {
        switch (locationName.toLowerCase()) {
            case "lobby":
                return new Location("Lobby", "feed", "play", "train");
            case "playground":
                return new Location("Playground", "play");
            case "training room":
                return new Location("Training Room", "train");
            case "kitchen":
                return new Location("Kitchen", "feed");
            default:
                return null;
        }
    }
}
