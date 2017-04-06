package by.vshkl.android.foodapp.ui.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.ui.view.RobotoRegularTextView;

class CategoriesViewHolder extends ViewHolder {

    FrameLayout flCategoryRoot;
    ImageView ivCategoryIcon;
    RobotoRegularTextView tvCategoryName;

    CategoriesViewHolder(View itemView) {
        super(itemView);

        flCategoryRoot = (FrameLayout) itemView.findViewById(R.id.fl_category_root);
        ivCategoryIcon = (ImageView) itemView.findViewById(R.id.iv_category_icon);
        tvCategoryName = (RobotoRegularTextView) itemView.findViewById(R.id.tv_category_name);
    }
}
