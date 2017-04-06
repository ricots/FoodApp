package by.vshkl.android.foodapp.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.Collection;

import by.vshkl.android.foodapp.mvp.model.Category;

public interface CategoriesView extends MvpView {

    void showLoading();

    void hideLoading();

    void showCategories(Collection<Category> categories);
}
