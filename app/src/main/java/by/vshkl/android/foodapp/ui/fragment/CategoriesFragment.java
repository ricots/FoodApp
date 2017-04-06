package by.vshkl.android.foodapp.ui.fragment;

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
import by.vshkl.android.foodapp.ui.adapter.CategoriesAdapter;

public class CategoriesFragment extends MvpAppCompatFragment implements CategoriesView {

    @InjectPresenter CategoriesPresenter presenter;

    private RecyclerView rvCategories;
    private ProgressBar pbProgress;

    private CategoriesAdapter categoriesAdapter;

    public static Fragment newInstance() {
        return new CategoriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        rvCategories = (RecyclerView) view.findViewById(R.id.rv_categories);
        pbProgress = (ProgressBar) view.findViewById(R.id.pb_progress);
        initializeRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadCategories();
    }

    @Override
    public void onDetach() {
        presenter.onDestroy();
        super.onDetach();
    }

    @Override
    public void showLoading() {
        rvCategories.setVisibility(View.GONE);
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbProgress.setVisibility(View.GONE);
        rvCategories.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCategories(Collection<Category> categories) {
        categoriesAdapter.setCategories(categories);
        categoriesAdapter.notifyDataSetChanged();
    }

    private void initializeRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        categoriesAdapter = new CategoriesAdapter(ContextCompat.getColor(getContext(), R.color.colorIconCategories));
        rvCategories.setLayoutManager(linearLayoutManager);
        rvCategories.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvCategories.setAdapter(categoriesAdapter);
    }
}
