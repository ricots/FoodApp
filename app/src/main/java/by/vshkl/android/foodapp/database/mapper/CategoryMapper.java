package by.vshkl.android.foodapp.database.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import by.vshkl.android.foodapp.database.entity.CategoryEntity;
import by.vshkl.android.foodapp.network.model.Category;

public class CategoryMapper {

    public static CategoryEntity transform(Category category) {
        CategoryEntity categoryEntity = null;

        if (category != null) {
            categoryEntity = new CategoryEntity();
            categoryEntity.setId(Integer.parseInt(category.getId()));
            categoryEntity.setName(category.getText());
        }

        return categoryEntity;
    }

    public static Collection<CategoryEntity> transform(Collection<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<CategoryEntity> categoryEntities = new ArrayList<>(categories.size());
        for (Category category : categories) {
            if (category != null) {
                categoryEntities.add(transform(category));
            }
        }

        return categoryEntities;
    }
}
