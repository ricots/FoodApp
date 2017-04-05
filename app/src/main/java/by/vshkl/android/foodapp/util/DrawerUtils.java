package by.vshkl.android.foodapp.util;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import by.vshkl.android.foodapp.R;

public class DrawerUtils {

    public static Drawer initializeDrawer(AppCompatActivity activity, Toolbar toolbar,
                                          OnDrawerItemClickListener listener) {
        return new DrawerBuilder()
                .withActivity(activity)
                .withHeader(R.layout.drawer_header)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.nav_catalog)
                                .withIcon(R.drawable.ic_restaurant_menu),
                        new PrimaryDrawerItem()
                                .withName(R.string.nav_contacts)
                                .withIcon(R.drawable.ic_contacts))
                .withOnDrawerItemClickListener(listener)
                .build();
    }
}
