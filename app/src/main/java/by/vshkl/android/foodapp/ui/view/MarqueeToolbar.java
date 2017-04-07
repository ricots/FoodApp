package by.vshkl.android.foodapp.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MarqueeToolbar extends Toolbar {

    private TextView title;
    private boolean reflected = false;

    public MarqueeToolbar(Context context) {
        super(context);
    }

    public MarqueeToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTitle(CharSequence title) {
        if (!reflected) {
            reflected = reflectTitle();
        }
        super.setTitle(title);
        selectTitle();
    }

    @Override
    public void setTitle(int resId) {
        if (!reflected) {
            reflected = reflectTitle();
        }
        super.setTitle(resId);
        selectTitle();
    }

    private boolean reflectTitle() {
        try {
            Field field = Toolbar.class.getDeclaredField("mTitleTextView");
            field.setAccessible(true);
            title = (TextView) field.get(this);
            title.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            title.setMarqueeRepeatLimit(-1);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        } catch (IllegalAccessException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void selectTitle() {
        if (title != null)
            title.setSelected(true);
    }
}
