package by.vshkl.android.foodapp.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public enum ApiClient {
    INSTANCE;

    public static ApiInterface getFoodApi() {
        return new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://ufa.farfor.ru")
                .build()
                .create(ApiInterface.class);
    }
}
