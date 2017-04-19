package vehicletracker;

import anno.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by be on 2017-4-18.
 */
@ThreadSafe
public class PublishingVehicleTracker {
    private final Map<String,SafePoint> locations;
    private final Map<String,SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> locations) {
        this.locations = new HashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }

    public SafePoint get(String id){
        return locations.get(id);
    }


}
