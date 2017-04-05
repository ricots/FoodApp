package by.vshkl.android.foodapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import by.vshkl.android.foodapp.mvp.view.MainView;
import by.vshkl.android.foodapp.network.ApiClient;
import by.vshkl.android.foodapp.network.model.Catalog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    public void downloadMenu() {
        ApiClient.getFoodApi().getFood()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Catalog>() {
                    @Override
                    public void accept(@NonNull Catalog catalog) throws Exception {
                        System.out.println(catalog.getShop().getCategories().toString());
                    }
                });
    }
}
