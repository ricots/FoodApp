package by.vshkl.android.foodapp.ui.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import by.vshkl.android.foodapp.R;

class OffersViewHolder extends ViewHolder {

    RelativeLayout rlOfferRoot;
    ImageView ivOfferIcon;
    TextView tvOfferTitle;
    TextView tvOfferWeight;
    TextView tvOfferPrice;

    OffersViewHolder(View itemView) {
        super(itemView);

        rlOfferRoot = (RelativeLayout) itemView.findViewById(R.id.rl_offer_root);
        ivOfferIcon = (ImageView) itemView.findViewById(R.id.iv_offer_icon);
        tvOfferTitle = (TextView) itemView.findViewById(R.id.tv_offer_title);
        tvOfferWeight = (TextView) itemView.findViewById(R.id.tv_offer_weight);
        tvOfferPrice = (TextView) itemView.findViewById(R.id.tv_offer_price);
    }
}
