package by.vshkl.android.foodapp.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import by.vshkl.android.foodapp.R;

public class AspectRatioImageView extends AppCompatImageView {

    private int aspectRatio;

    public AspectRatioImageView(Context context) {
        super(context);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView, 0, 0);
        try {
            aspectRatio = array.getInteger(R.styleable.AspectRatioImageView_aspectRatio, -1);
        } finally {
            array.recycle();
        }
    }

    public AspectRatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView, defStyleAttr, 0);
        try {
            aspectRatio = array.getInteger(R.styleable.AspectRatioImageView_aspectRatio, -1);
        } finally {
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (aspectRatio == 0) {
            setMeasuredDimension(width, width * 9 / 16);
        } else if (aspectRatio == 1) {
            setMeasuredDimension(width, width);
        } else {
            setMeasuredDimension(width, height);
        }
    }
}
