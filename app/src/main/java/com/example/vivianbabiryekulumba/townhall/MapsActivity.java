package com.example.vivianbabiryekulumba.townhall;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    private MapView mapView;
    private Double commBoardLag;
    private Double commBoardLong;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapView = findViewById(R.id.map);
        getCoordinates();
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (commBoardLag != null && commBoardLong != null) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(commBoardLag, commBoardLong))
                    .title(address)
                    .snippet("Community Board Address"));

            CameraPosition restaurant = CameraPosition.builder()
                    .target(new LatLng(commBoardLag, commBoardLong))
                    .zoom(16)
                    .bearing(0)
                    .tilt(45)
                    .build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(restaurant));
        } else {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                    (getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            googleMap.setMyLocationEnabled(true);
            CameraPosition myLocation = CameraPosition.builder()
                    .target(new LatLng(MainActivity.getCurrentLatitude(), MainActivity.getCurrentLongitude()))
                    .zoom(16)
                    .bearing(0)
                    .tilt(45)
                    .build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(myLocation));
        }

    }

    public void getCoordinates() {
        Bundle bundle = getIntent().getExtras().getBundle("address");
        if (bundle != null) {
            commBoardLong = bundle.getDouble("long");
            commBoardLag = bundle.getDouble("lag");
            address = bundle.getString("address");
            Log.d("geoLocate ", address + " " + commBoardLag + ", " + commBoardLong);
        }
    }

}
