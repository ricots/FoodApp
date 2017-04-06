package by.vshkl.android.foodapp.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.ui.fragment.CategoriesFragment;
import by.vshkl.android.foodapp.ui.fragment.OffersFragment;

public class Navigator {

    public static void navigateToCategories(FragmentActivity activity) {
        replaceFragment(activity, CategoriesFragment.newInstance(), false);
    }

    public static void navigateToOffers(FragmentActivity activity, int categoryId) {
        replaceFragment(activity, OffersFragment.newInstance(categoryId), true);
    }

    private static void replaceFragment(FragmentActivity activity, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_fragment_container, fragment, fragment.getTag());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }

}
