package by.vshkl.android.foodapp.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.Collection;

import by.vshkl.android.foodapp.mvp.model.Offer;

public interface OfferDetailsView extends MvpView {

    void showLoading();

    void hideLoading();

    void showOffers(Offer offer);
}
