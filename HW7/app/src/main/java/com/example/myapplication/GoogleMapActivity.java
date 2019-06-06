package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import android.location.Location;
import android.util.Log;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "Google_map_activity:";
    private GoogleMap googleMap;
    private String description;
    private Double lat;
    private Double lng;
    private FusedLocationProviderClient mLocationClient;
    private boolean mLocationPermissionGranted= false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map );
        description = getIntent().getStringExtra(MainActivity.location);
        lat= (Double) getIntent().getDoubleExtra(MainActivity.latitude,0.0);
        lng= (Double) getIntent().getDoubleExtra(MainActivity.longtiude,0.0);



        mLocationClient = LocationServices
                .getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    @SuppressLint("missingPermission")
    private void getLocation() {
        if (mLocationPermissionGranted) {
            Task location = mLocationClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener<Location>() {

            @Override
            public void onComplete(Task<Location> task) {
                Location actualLocation = task.getResult();
                if(actualLocation != null) {
                    String latLong = String.format("Lat: %f, Long: %f",
                            actualLocation.getLatitude(),
                            actualLocation.getLongitude());

                    googleMap.setMyLocationEnabled(true);
                    googleMap.getUiSettings().setMyLocationButtonEnabled(true);

                    LatLng here = new LatLng(actualLocation.getLatitude(),
                            actualLocation.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(here).title("Current Location"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 10));
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));

                } else {
                    Log.e(TAG, "Location is nul...");
                }

            }

        });

        location.addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, e.getLocalizedMessage());
            }
        });
    }
}
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        mLocationPermissionGranted = false;
        switch(requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                    getLocation();
                }
            }
        }
    }
    private void getLocationPermission() {
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat,lng))
                .title( "LAT: " + lat.toString()+" LNG: "+ lng.toString())
                .visible( true ));
        getLocationPermission();
        getLocation();
    }
}




