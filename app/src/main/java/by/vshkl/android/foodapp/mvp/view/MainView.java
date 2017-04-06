package by.vshkl.android.foodapp.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void showEmpty();

    void hideEmpty();

    void showProgress();

    void hideProgress();

    void showMessage(int messageId);

    void showCatalog();

    void showOffers(int categoryId, String categoryName);

    void showOffer(int offerId);

    void showContacts();

    void updateCatalog();
}
