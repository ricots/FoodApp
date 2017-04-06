package by.vshkl.android.foodapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import by.vshkl.android.foodapp.database.DatabaseRepository;
import by.vshkl.android.foodapp.mvp.view.MainView;
import by.vshkl.android.foodapp.network.ApiClient;
import by.vshkl.android.foodapp.network.model.Catalog;
import by.vshkl.android.foodapp.util.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    public void showCatalog() {
        getViewState().showCatalog();
    }

    public void showOffers(int categoryId, String categoryName) {
        getViewState().showOffers(categoryId, categoryName);
    }

    public void showOffer(int offerId) {
        getViewState().showOffer(offerId);
    }

    public void showContacts() {
        getViewState().showContacts();
    }

    public void checkIfCatalogDownloaded() {
        setDisposable(DatabaseRepository.checkIfCatalogDownloaded()
                .compose(RxUtils.<Boolean>applySchedulers())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (!aBoolean) {
                            getViewState().showEmpty();
                        } else {
                            getViewState().hideEmpty();
                            getViewState().showCatalog();
                        }
                    }
                }));
    }

    public void downloadMenu() {
        setDisposable(ApiClient.getFoodApi().getFood()
                .compose(RxUtils.<Catalog>applySchedulers())
                .subscribe(new Consumer<Catalog>() {
                    @Override
                    public void accept(@NonNull Catalog catalog) throws Exception {
                        DatabaseRepository.saveCatalog(catalog)
                                .compose(RxUtils.<Boolean>applySchedulers())
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                                        if (aBoolean) {
                                            getViewState().hideEmpty();
                                        }
                                    }
                                });
                    }
                }));
    }
}
