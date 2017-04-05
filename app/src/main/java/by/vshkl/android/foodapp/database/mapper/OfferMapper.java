package by.vshkl.android.foodapp.database.mapper;

import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import by.vshkl.android.foodapp.database.entity.OfferEntity;
import by.vshkl.android.foodapp.database.entity.ParamEntity;
import by.vshkl.android.foodapp.network.model.Offer;

public class OfferMapper {

    public static Pair<OfferEntity, Collection<ParamEntity>> transform(Offer offer) {
        Pair<OfferEntity, Collection<ParamEntity>> pair = null;

        if (offer != null) {
            OfferEntity offerEntity = new OfferEntity();
            offerEntity.setId(Integer.parseInt(offer.getId()));
            offerEntity.setName(offer.getName());
            offerEntity.setCategoryId(Integer.parseInt(offer.getCategoryId()));
            offerEntity.setPictureUrl(offer.getPictureUrl());
            offerEntity.setDescription(offer.getDescription());
            offerEntity.setUrl(offer.getUrl());
            offerEntity.setPrice(offer.getPrice());
            pair = new Pair<>(offerEntity, ParamMapper.transform(offer.getParams(), offerEntity.getId()));
        }

        return pair;
    }

    public static Collection<Pair<OfferEntity, Collection<ParamEntity>>> transform(Collection<Offer> offers) {
        if (offers == null || offers.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<Pair<OfferEntity, Collection<ParamEntity>>> pairs = new ArrayList<>(offers.size());
        for (Offer offer : offers) {
            if (offer != null) {
                pairs.add(transform(offer));
            }
        }

        return pairs;
    }
}
