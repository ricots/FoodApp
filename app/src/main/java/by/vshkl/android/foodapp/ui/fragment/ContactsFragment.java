package by.vshkl.android.foodapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.mvp.view.ContactsView;
import by.vshkl.android.foodapp.ui.MainActivity;
import by.vshkl.android.foodapp.ui.view.RobotoRegularTextView;
import by.vshkl.android.foodapp.ui.view.ScrollViewMapFragment;

public class ContactsFragment extends MvpAppCompatFragment implements ContactsView, ConnectionCallbacks,
        OnMapReadyCallback {

    private ScrollView svContactsRoot;
    private RobotoRegularTextView tvContactsInfo;

    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    private MainActivity parentActivity;

    public static Fragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.parentActivity = (MainActivity) context;
        }
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
    public void onDetach() {
        parentActivity = null;
        super.onDetach();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

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
}
