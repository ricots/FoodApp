package by.vshkl.android.foodapp.database.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import by.vshkl.android.foodapp.database.entity.ParamEntity;
import by.vshkl.android.foodapp.network.model.Param;

public class ParamMapper {

    public static ParamEntity transform(Param param, int offerId) {
        ParamEntity paramEntity = null;

        if (param != null) {
            paramEntity = new ParamEntity();
            paramEntity.setOfferId(offerId);
            paramEntity.setName(param.getName());
            paramEntity.setText(param.getText());
        }

        return paramEntity;
    }

    public static Collection<ParamEntity> transform(Collection<Param> params, int offerId) {
        if (params == null || params.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<ParamEntity> paramEntities = new ArrayList<>(params.size());
        for (Param param : params) {
            if (param != null) {
                paramEntities.add(transform(param, offerId));
            }
        }

        return paramEntities;
    }
}
