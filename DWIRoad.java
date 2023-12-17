public class DWIRoad implements IRoad {

    private final String origin; // the origin of the road
    private final String destination; // the destination of the road
    private final int distance; // the distance of the road

    public DWIRoad(String origin, String destination, String distance) {
        this.origin = origin; // set the origin
        this.destination = destination; // set the destination
        this.distance = Integer.parseInt(distance); // set the distance
    }

    /**
     * Gets the distance of the Road
     *
     * @return distance Integer
     */
    @Override public int getDistance() {
        return this.distance;
    }

    /**
     * Gets the origin City of the Road
     *
     * @return origin City
     */
    @Override public String getOrigin() {
        return this.origin;
    }

    /**
     * Gets the destination City of the Road
     *
     * @return destination City
     */
    @Override public String getDestination() {
        return this.destination;
    }
}
