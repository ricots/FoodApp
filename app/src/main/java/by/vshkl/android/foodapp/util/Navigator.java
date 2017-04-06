package by.vshkl.android.foodapp.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.ui.fragment.CategoriesFragment;

public class Navigator {

    public static void navigateToCategories(FragmentActivity activity) {
        replaceFragment(activity, CategoriesFragment.newInstance());
    }

    private static void replaceFragment(FragmentActivity activity, Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_fragment_container, fragment, fragment.getTag());
        fragmentTransaction.commit();
    }

}
