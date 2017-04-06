package by.vshkl.android.foodapp.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.mvp.view.ContactsView;
import by.vshkl.android.foodapp.ui.view.RobotoRegularTextView;
import by.vshkl.android.foodapp.ui.view.ScrollViewMapFragment;
import by.vshkl.android.foodapp.util.DialogUtils;
import by.vshkl.android.foodapp.util.Navigator;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ContactsFragment extends MvpAppCompatFragment implements ContactsView, ConnectionCallbacks,
        OnMapReadyCallback, OnMarkerClickListener {

    private ScrollView svContactsRoot;
    private RobotoRegularTextView tvContactsInfo;

    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;

    public static Fragment newInstance() {
        return new ContactsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        svContactsRoot = (ScrollView) view.findViewById(R.id.sv_contacts_root);
        tvContactsInfo = (RobotoRegularTextView) view.findViewById(R.id.tv_contacts_info);
        initializeGoogleMap();
        initializeGoogleApiClient();
    }

    @Override
    public void onResume() {
        googleApiClient.connect();
        super.onResume();
    }

    @Override
    public void onPause() {
        googleApiClient.disconnect();
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ContactsFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        showUserLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        setupMap();
        ContactsFragmentPermissionsDispatcher.updateCoordinatesWithCheck(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    void updateCoordinates() {
        LocationManager lm = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        boolean hasProvider = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                || lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (hasProvider) {
            showUserLocation();
        } else {
            DialogUtils.showLocationTurnOnDialog(getContext());
        }
    }

    @OnShowRationale({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    void showRationaleForLocation(final PermissionRequest request) {
        DialogUtils.showLocationRationaleDialog(getContext(), request);
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    void onDeniedForLocation() {
        Snackbar.make(svContactsRoot, R.string.permission_denied_message, Snackbar.LENGTH_LONG)
                .setAction(R.string.permission_settings, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigator.navigateToAppSettings(getContext());
                    }
                }).show();
    }

    private void initializeGoogleMap() {
        ScrollViewMapFragment mapFragment =
                (ScrollViewMapFragment) getChildFragmentManager().findFragmentById(R.id.fr_contacts_map);
        mapFragment.getMapAsync(this);
        mapFragment.setListener(new ScrollViewMapFragment.OnMapTouchListener() {
            @Override
            public void onTouch() {
                svContactsRoot.requestDisallowInterceptTouchEvent(true);
            }
        });
    }

    private void initializeGoogleApiClient() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addApi(LocationServices.API).build();
        }
    }

    private void setupMap() {
        UiSettings settings = googleMap.getUiSettings();
        settings.setCompassEnabled(false);
        settings.setMyLocationButtonEnabled(false);
        settings.setMapToolbarEnabled(false);
        googleMap.setBuildingsEnabled(true);
        googleMap.setOnMarkerClickListener(this);
    }

    private void showUserLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            googleMap.setMyLocationEnabled(true);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11F));
        }
    }
}
