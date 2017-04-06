package by.vshkl.android.foodapp.util;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class DummyCoordinateUtils {

    public static List<LatLng> getDummyCoordinates() {
        List<LatLng> coordinates = new ArrayList<>();

        coordinates.add(new LatLng(53.9265433, 27.4403715));
        coordinates.add(new LatLng(53.9057034, 27.4493408));
        coordinates.add(new LatLng(53.90832, 27.469275));
        coordinates.add(new LatLng(53.868092, 27.4844027));
        coordinates.add(new LatLng(53.8752527, 27.4975562));
        coordinates.add(new LatLng(53.9126049, 27.5323391));
        coordinates.add(new LatLng(53.9260632, 27.592485));
        coordinates.add(new LatLng(53.8998502, 27.5973773));

        return coordinates;
    }
}
