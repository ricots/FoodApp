package by.vshkl.android.foodapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.Collection;

import by.vshkl.android.foodapp.database.DatabaseRepository;
import by.vshkl.android.foodapp.mvp.model.Offer;
import by.vshkl.android.foodapp.mvp.view.OffersView;
import by.vshkl.android.foodapp.util.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@InjectViewState
public class OffersPresenter extends BasePresenter<OffersView> {

    public void loadOffers(int categoryId) {
        setDisposable(DatabaseRepository.loadOffers(categoryId)
                .compose(RxUtils.<Collection<Offer>>applySchedulers())
                .subscribe(new Consumer<Collection<Offer>>() {
                    @Override
                    public void accept(@NonNull Collection<Offer> offers) throws Exception {
                        getViewState().showOffers(offers);
                        getViewState().hideLoading();
                    }
                }));
    }
}
