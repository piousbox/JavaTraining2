package com.piousbox.training2;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.piousbox.training2.rest.SiteNews;

import java.util.ArrayList;

public class NewsitemsListActivity extends AppCompatActivity
    implements GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient mGoogleApiClient = null;
    private Location mCurrentLocation = null;

    /**
     * Display gps results without losing them
     */
    ArrayList<String> gpsItems = new ArrayList<String>();
    ArrayAdapter<String> gpsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();
        Log.v("abba", "connecting...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        new SiteNews(this).execute();
    }
    
    @Override
    public void onConnectionFailed(ConnectionResult r) {
        Log.v("abba", "connection failed?"+ r);
    }

    @Override
    public void onLocationChanged(Location loc) {
        Log.v("abba", "location changed"+ loc);
        Toast toast = Toast.makeText(this, "location:"+loc.getLatitude()+"::"+loc.getLongitude(), Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onConnected(Bundle b) {
        Log.v("abba", "connected");

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1 * 1000);
        mLocationRequest.setFastestInterval(1 * 1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);

        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            Log.v("abba", "curr loc:" + mCurrentLocation);
        } catch (SecurityException e) {
            Log.v("abba", "exception"+ e);
        }

        Toast toast = Toast.makeText(this, "location:"+mCurrentLocation, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onConnectionSuspended(int i) {}



}
