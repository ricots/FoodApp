package by.vshkl.android.foodapp.mvp.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import by.vshkl.android.foodapp.database.entity.ParamEntity;
import by.vshkl.android.foodapp.mvp.model.Param;

public class ParamEntityMapper {

    public static Param transform(ParamEntity paramEntity) {
        Param param = null;

        if (paramEntity != null) {
            param = new Param();
            param.setName(paramEntity.getName());
            param.setText(paramEntity.getText());
        }

        return param;
    }

    public static Collection<Param> transform(Collection<ParamEntity> paramEntities) {
        if (paramEntities == null || paramEntities.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<Param> params = new ArrayList<>(paramEntities.size());
        for (ParamEntity paramEntity : paramEntities) {
            if (paramEntity != null) {
                params.add(transform(paramEntity));
            }
        }

        return params;
    }
}
