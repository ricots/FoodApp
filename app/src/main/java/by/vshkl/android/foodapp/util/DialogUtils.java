package by.vshkl.android.foodapp.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import by.vshkl.android.foodapp.R;
import permissions.dispatcher.PermissionRequest;

public class DialogUtils {

    public static void showLocationRationaleDialog(Context context, final PermissionRequest request) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.permission_rationale_title)
                .setMessage(R.string.permission_rationale_message)
                .setPositiveButton(R.string.permission_rationale_allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.permission_rationale_deny, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                }).create().show();
    }

    public static void showLocationTurnOnDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.permission_rationale_title)
                .setMessage(R.string.location_message)
                .setPositiveButton(R.string.location_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Navigator.navigateToLocationSettings(context);
                    }
                }).create().show();
    }

    public static void showNetworkTurnOnDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.network_title)
                .setMessage(R.string.network_message)
                .setPositiveButton(R.string.network_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }
}
