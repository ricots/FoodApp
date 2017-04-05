package by.vshkl.android.foodapp.network;

import by.vshkl.android.foodapp.network.model.Catalog;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/getyml/?key=ukAXxeJYZN")
    Observable<Catalog> getFood();
}
