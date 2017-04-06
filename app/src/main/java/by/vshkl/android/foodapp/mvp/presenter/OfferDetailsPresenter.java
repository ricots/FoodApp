package by.vshkl.android.foodapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import by.vshkl.android.foodapp.database.DatabaseRepository;
import by.vshkl.android.foodapp.mvp.model.Offer;
import by.vshkl.android.foodapp.mvp.view.OfferDetailsView;
import by.vshkl.android.foodapp.util.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@InjectViewState
public class OfferDetailsPresenter extends BasePresenter<OfferDetailsView> {

    public void loadOffer(int offerId) {
        setDisposable(DatabaseRepository.loadOffer(offerId)
                .compose(RxUtils.<Offer>applySchedulers())
                .subscribe(new Consumer<Offer>() {
                    @Override
                    public void accept(@NonNull Offer offer) throws Exception {
                        getViewState().showOffers(offer);
                        getViewState().hideLoading();
                    }
                }));
    }
}
