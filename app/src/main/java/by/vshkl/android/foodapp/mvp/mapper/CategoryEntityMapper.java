package by.vshkl.android.foodapp.mvp.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import by.vshkl.android.foodapp.database.entity.CategoryEntity;
import by.vshkl.android.foodapp.mvp.model.Category;

public class CategoryEntityMapper {

    public static Category transform(CategoryEntity categoryEntity) {
        Category category = null;

        if (categoryEntity != null) {
            category = new Category();
            category.setId(categoryEntity.getId());
            category.setName(categoryEntity.getName());
        }

        return category;
    }

    public static Collection<Category> transform(Collection<CategoryEntity> categoryEntities) {
        if (categoryEntities == null || categoryEntities.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<Category> categories = new ArrayList<>(categoryEntities.size());
        for (CategoryEntity categoryEntity : categoryEntities) {
            if (categoryEntity != null) {
                categories.add(transform(categoryEntity));
            }
        }

        return categories;
    }
}
