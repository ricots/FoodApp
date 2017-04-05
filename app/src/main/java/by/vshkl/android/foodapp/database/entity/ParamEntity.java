package by.vshkl.android.foodapp.database.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import by.vshkl.android.foodapp.database.AppDatabase;

@Table(database = AppDatabase.class, name = "Params")
public class ParamEntity extends BaseModel {

    @PrimaryKey(autoincrement = true) private int id;
    @ForeignKey(tableClass = OfferEntity.class) private int offerId;
    @Column private String name;
    @Column private String text;

    public ParamEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
