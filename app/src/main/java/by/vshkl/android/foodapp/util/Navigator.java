package by.vshkl.android.foodapp.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.ui.activity.MainActivity;
import by.vshkl.android.foodapp.ui.fragment.CategoriesFragment;
import by.vshkl.android.foodapp.ui.fragment.ContactsFragment;
import by.vshkl.android.foodapp.ui.fragment.OfferDetailsFragment;
import by.vshkl.android.foodapp.ui.fragment.OffersFragment;

public class Navigator {

    public static void navigatrToMainActivity(Context context) {
        context.startActivity(MainActivity.newIntent(context));
    }

    public static void navigateToAppSettings(Context context) {
        final Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent);
    }

    public static void navigateToLocationSettings(Context context) {
        context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    public static void navigateToCategories(FragmentActivity activity) {
        replaceFragment(activity, CategoriesFragment.newInstance(), false, false);
    }

    public static void navigateToOffers(FragmentActivity activity, int categoryId, String categoryName) {
        replaceFragment(activity, OffersFragment.newInstance(categoryId, categoryName), true, true);
    }

    public static void navigateToOffer(FragmentActivity activity, int offerId) {
        replaceFragment(activity, OfferDetailsFragment.newInstance(offerId), true, true);
    }

    public static void navigateToContacts(FragmentActivity activity) {
        replaceFragment(activity, ContactsFragment.newInstance(), false, false);
    }

    private static void replaceFragment(FragmentActivity activity, Fragment fragment, boolean addToBackStack,
                                        boolean animate) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (animate) {
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, 0, R.anim.enter_from_left, 0);
        }
        fragmentTransaction.replace(R.id.fl_fragment_container, fragment, fragment.getTag());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        } else {
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                fragmentManager.popBackStack();
            }
        }
        fragmentTransaction.commit();
    }

}
