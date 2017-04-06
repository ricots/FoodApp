package by.vshkl.android.foodapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.Collection;

import by.vshkl.android.foodapp.database.DatabaseRepository;
import by.vshkl.android.foodapp.mvp.model.Category;
import by.vshkl.android.foodapp.mvp.view.CategoriesView;
import by.vshkl.android.foodapp.util.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@InjectViewState
public class CategoriesPresenter extends BasePresenter<CategoriesView> {

    public void loadCategories() {
        setDisposable(DatabaseRepository.loadCategories()
                .compose(RxUtils.<Collection<Category>>applySchedulers())
                .subscribe(new Consumer<Collection<Category>>() {
                    @Override
                    public void accept(@NonNull Collection<Category> categories) throws Exception {
                        getViewState().hideLoading();
                        getViewState().showCategories(categories);
                    }
                }));
    }
}
