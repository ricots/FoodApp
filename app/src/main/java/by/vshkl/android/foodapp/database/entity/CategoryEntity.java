package by.vshkl.android.foodapp.database.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import by.vshkl.android.foodapp.database.AppDatabase;

@Table(database = AppDatabase.class, name = "Categories")
public class CategoryEntity extends BaseModel {

    @PrimaryKey private int id;
    @Column private String name;

    public CategoryEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
