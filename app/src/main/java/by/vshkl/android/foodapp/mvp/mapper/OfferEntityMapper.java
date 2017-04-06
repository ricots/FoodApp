package by.vshkl.android.foodapp.mvp.mapper;

import java.util.ArrayList;
import java.util.List;

import by.vshkl.android.foodapp.database.entity.OfferEntity;
import by.vshkl.android.foodapp.database.entity.ParamEntity;
import by.vshkl.android.foodapp.mvp.model.Offer;

public class OfferEntityMapper {

    public static Offer transform(OfferEntity offerEntity, List<ParamEntity> paramEntities) {
        Offer offer = null;

        if (offerEntity != null && paramEntities != null) {
            offer = new Offer();
            offer.setId(offerEntity.getId());
            offer.setCategoryId(offerEntity.getCategoryId());
            offer.setName(offerEntity.getName());
            offer.setDescription(offerEntity.getDescription());
            offer.setPrice(offerEntity.getPrice());
            offer.setUrl(offerEntity.getUrl());
            offer.setPictureUrl(offerEntity.getPictureUrl());
            offer.setParams(new ArrayList<>(ParamEntityMapper.transform(paramEntities)));
        }

        return offer;
    }
}
