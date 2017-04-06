package by.vshkl.android.foodapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.mvp.model.Offer;
import by.vshkl.android.foodapp.mvp.presenter.OfferDetailsPresenter;
import by.vshkl.android.foodapp.mvp.view.OfferDetailsView;

public class OfferDetailsFragment extends MvpAppCompatFragment implements OfferDetailsView {

    private static final String KEY_OFFER_ID = "OffersFragment.KEY_OFFER_ID";

    @InjectPresenter OfferDetailsPresenter presenter;

    private ProgressBar pbProgress;

    private int offerId = -1;

    public static Fragment newInstance(int offerId) {
        Fragment fragment = new OfferDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_OFFER_ID, offerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offerId = getArguments().getInt(KEY_OFFER_ID, -1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_offer_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pbProgress = (ProgressBar) view.findViewById(R.id.pb_progress);
        if (offerId >= 0) {
            presenter.loadOffer(offerId);
        }
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbProgress.setVisibility(View.GONE);
    }

    @Override
    public void showOffers(Offer offer) {
        Toast.makeText(getActivity(), offer.toString(), Toast.LENGTH_SHORT).show();
    }
}
