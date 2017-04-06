package by.vshkl.android.foodapp.ui.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import by.vshkl.android.foodapp.R;

public class CategoriesViewHolder extends ViewHolder {

    ImageView ivCategoryIcon;
    TextView tvCategoryName;

    public CategoriesViewHolder(View itemView) {
        super(itemView);

        ivCategoryIcon = (ImageView) itemView.findViewById(R.id.iv_category_icon);
        tvCategoryName = (TextView) itemView.findViewById(R.id.tv_category_name);
    }
}
