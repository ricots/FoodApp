package by.vshkl.android.foodapp.ui.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.ui.view.RobotoMediumTextView;
import by.vshkl.android.foodapp.ui.view.RobotoRegularTextView;

class OffersViewHolder extends ViewHolder {

    RelativeLayout rlOfferRoot;
    ImageView ivOfferIcon;
    RobotoRegularTextView tvOfferTitle;
    RobotoMediumTextView tvOfferWeight;
    RobotoMediumTextView tvOfferPrice;

    OffersViewHolder(View itemView) {
        super(itemView);

        rlOfferRoot = (RelativeLayout) itemView.findViewById(R.id.rl_offer_root);
        ivOfferIcon = (ImageView) itemView.findViewById(R.id.iv_offer_icon);
        tvOfferTitle = (RobotoRegularTextView) itemView.findViewById(R.id.tv_offer_title);
        tvOfferWeight = (RobotoMediumTextView) itemView.findViewById(R.id.tv_offer_weight);
        tvOfferPrice = (RobotoMediumTextView) itemView.findViewById(R.id.tv_offer_price);
    }
}
