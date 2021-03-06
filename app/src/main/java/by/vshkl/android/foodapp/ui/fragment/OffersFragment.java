package by.vshkl.android.foodapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import by.vshkl.android.foodapp.mvp.model.Offer;
import by.vshkl.android.foodapp.mvp.presenter.OffersPresenter;
import by.vshkl.android.foodapp.mvp.view.OffersView;
import by.vshkl.android.foodapp.ui.activity.MainActivity;
import by.vshkl.android.foodapp.ui.adapter.OffersAdapter;
import by.vshkl.android.foodapp.ui.listener.OfferEventListener;

public class OffersFragment extends MvpAppCompatFragment implements OffersView, OfferEventListener {

    private static final String KEY_CATEGORY_ID = "OffersFragment.KEY_CATEGORY_ID";
    private static final String KEY_CATEGORY_NAME = "OffersFragment.KEY_CATEGORY_NAME";

    @InjectPresenter OffersPresenter presenter;

    private RecyclerView rvList;
    private ProgressBar pbProgress;

    private MainActivity parentActivity;
    private OffersAdapter offersAdapter;
    private int categoryId;
    private String categoryName ;

    public static Fragment newInstance(int categoryId, String categoryName) {
        Fragment fragment = new OffersFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_CATEGORY_ID, categoryId);
        args.putString(KEY_CATEGORY_NAME, categoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.parentActivity = (MainActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryId = getArguments().getInt(KEY_CATEGORY_ID, -1);
        categoryName = getArguments().getString(KEY_CATEGORY_NAME, "");
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
        parentActivity.setTitle(categoryName);
        if (categoryId >= 0) {
            presenter.loadOffers(categoryId);
        }
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
    public void showOffers(Collection<Offer> offers) {
        offersAdapter.setOffers(offers);
        offersAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOfferItemClicked(int offerId) {
        parentActivity.getPresenter().showOffer(offerId);
    }

    private void initializeRecyclerView() {
        offersAdapter = new OffersAdapter();
        offersAdapter.setListener(this);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvList.setAdapter(offersAdapter);
    }
}
