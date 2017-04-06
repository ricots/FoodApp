package by.vshkl.android.foodapp.ui.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.mvp.model.Offer;
import by.vshkl.android.foodapp.mvp.model.Param;
import by.vshkl.android.foodapp.ui.listener.OfferEventListener;

public class OffersAdapter extends Adapter<OffersViewHolder> {

    private List<Offer> offers;
    private OfferEventListener listener;

    public OffersAdapter() {
        this.offers = new ArrayList<>();
    }

    @Override
    public OffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OffersViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_offer, parent, false));
    }

    @Override
    public void onBindViewHolder(OffersViewHolder holder, int position) {
        final Offer offer = offers.get(position);

        if (offer.getParams() != null) {
            for (Param param : offer.getParams()) {
                if (param.getName().equals("Вес")) {
                    holder.tvOfferWeight.setText(param.getText());
                }
            }
        }

        Picasso.with(holder.ivOfferIcon.getContext())
                .load(offer.getPictureUrl())
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image)
                .into(holder.ivOfferIcon);
        holder.tvOfferTitle.setText(offer.getName());
        holder.tvOfferPrice.setText(offer.getPrice());
        holder.rlOfferRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onOfferItemClicked(offer.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return offers != null ? offers.size() : 0;
    }

    public void setOffers(Collection<Offer> offers) {
        this.offers = new ArrayList<>(offers);
    }

    public void setListener(OfferEventListener listener) {
        this.listener = listener;
    }
}
