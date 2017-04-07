package by.vshkl.android.foodapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.mvp.model.Offer;
import by.vshkl.android.foodapp.mvp.model.Param;
import by.vshkl.android.foodapp.mvp.presenter.OfferDetailsPresenter;
import by.vshkl.android.foodapp.mvp.view.OfferDetailsView;
import by.vshkl.android.foodapp.ui.activity.MainActivity;
import by.vshkl.android.foodapp.ui.view.AspectRatioImageView;
import by.vshkl.android.foodapp.ui.view.RobotoRegularTextView;

public class OfferDetailsFragment extends MvpAppCompatFragment implements OfferDetailsView {

    private static final String KEY_OFFER_ID = "OffersFragment.KEY_OFFER_ID";

    @InjectPresenter OfferDetailsPresenter presenter;

    private ProgressBar pbProgress;
    private AspectRatioImageView ivOfferImage;
    private RobotoRegularTextView tvOfferDescription;
    private RobotoRegularTextView tvOfferWeight;
    private RobotoRegularTextView tvOfferPrice;

    private MainActivity parentActivity;
    private int offerId = -1;

    public static Fragment newInstance(int offerId) {
        Fragment fragment = new OfferDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_OFFER_ID, offerId);
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
        ivOfferImage = (AspectRatioImageView) view.findViewById(R.id.iv_offer_image);
        tvOfferDescription = (RobotoRegularTextView) view.findViewById(R.id.tv_offer_description);
        tvOfferWeight = (RobotoRegularTextView) view.findViewById(R.id.tv_offer_weight);
        tvOfferPrice = (RobotoRegularTextView) view.findViewById(R.id.tv_offer_price);
        if (offerId >= 0) {
            presenter.loadOffer(offerId);
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
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbProgress.setVisibility(View.GONE);
    }

    @Override
    public void showOffers(Offer offer) {
        initializeOffer(offer);
    }

    private void initializeOffer(Offer offer) {
        parentActivity.setTitle(offer.getName());

        String offerPictureUrl = offer.getPictureUrl();
        if (offerPictureUrl != null) {
            ivOfferImage.setVisibility(View.VISIBLE);
            Picasso.with(getContext())
                    .load(offer.getPictureUrl())
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_image)
                    .into(ivOfferImage);
        }

        String offerDescription = offer.getDescription();
        if (offerDescription != null && !offerDescription.isEmpty()) {
            tvOfferDescription.setVisibility(View.VISIBLE);
            tvOfferDescription.setText(getString(R.string.template_description, offer.getDescription()));
        }

        if (offer.getParams() != null) {
            for (Param param : offer.getParams()) {
                if (param.getName().equals("Вес")) {
                    tvOfferWeight.setVisibility(View.VISIBLE);
                    tvOfferWeight.setText(getString(R.string.template_weight, param.getText()));
                }
            }
        }

        String offerPrice = offer.getPrice();
        if (offerPrice != null) {
            tvOfferPrice.setVisibility(View.VISIBLE);
            tvOfferPrice.setText(offer.getPrice());
            tvOfferPrice.setText(getString(R.string.template_price, offer.getPrice()));
        }
    }
}
