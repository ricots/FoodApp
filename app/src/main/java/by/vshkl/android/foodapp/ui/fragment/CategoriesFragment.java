package by.vshkl.android.foodapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Collection;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.mvp.model.Category;
import by.vshkl.android.foodapp.mvp.presenter.CategoriesPresenter;
import by.vshkl.android.foodapp.mvp.view.CategoriesView;
import by.vshkl.android.foodapp.ui.activity.MainActivity;
import by.vshkl.android.foodapp.ui.adapter.CategoriesAdapter;
import by.vshkl.android.foodapp.ui.listener.CategoryItemEventListener;

public class CategoriesFragment extends MvpAppCompatFragment implements CategoriesView, CategoryItemEventListener {

    @InjectPresenter CategoriesPresenter presenter;

    private RecyclerView rvList;
    private ProgressBar pbProgress;

    private MainActivity parentActivity;
    private CategoriesAdapter categoriesAdapter;

    public static Fragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.parentActivity = (MainActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvList = (RecyclerView) view.findViewById(R.id.rv_list);
        pbProgress = (ProgressBar) view.findViewById(R.id.pb_progress);
        initializeRecyclerView();
        parentActivity.setTitle(getString(R.string.nav_catalog));
        presenter.loadCategories();
    }

    @Override
    public void onDetach() {
        presenter.onDestroy();
        this.parentActivity = null;
        super.onDetach();
    }

    @Override
    public void showLoading() {
        rvList.setVisibility(View.GONE);
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbProgress.setVisibility(View.GONE);
        rvList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCategories(Collection<Category> categories) {
        categoriesAdapter.setCategories(categories);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCategoryItemClicked(int categoryId, String categoryName) {
        parentActivity.getPresenter().showOffers(categoryId, categoryName);
    }

    private void initializeRecyclerView() {
        categoriesAdapter = new CategoriesAdapter(ContextCompat.getColor(getContext(), R.color.colorIconCategories));
        categoriesAdapter.setListener(this);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvList.setAdapter(categoriesAdapter);
    }
}
