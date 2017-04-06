package by.vshkl.android.foodapp.ui.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.mvp.model.Category;

public class CategoriesAdapter extends Adapter<CategoriesViewHolder> {

    private List<Category> categories;
    private int colorCategoryIcon;

    public CategoriesAdapter(int colorCategoryIcon) {
        categories = new ArrayList<>();
        this.colorCategoryIcon = colorCategoryIcon;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoriesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        final Category category = categories.get(position);

        holder.ivCategoryIcon.setImageDrawable(TextDrawable.builder()
                .buildRound(category.getName().substring(0, 2), colorCategoryIcon));
        holder.tvCategoryName.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = new ArrayList<>(categories);
    }
}
