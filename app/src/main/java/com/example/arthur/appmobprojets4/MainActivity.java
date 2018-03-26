package com.example.arthur.appmobprojets4;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    private static double lat = 0.000000;
    private static double lng = 0.000000;
    private static String adresse = "";
    private static Location blockedLocation ;
    private FusedLocationProviderClient mFusedLocationClient;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button button = (Button) findViewById(R.id.buttonMap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAllerEcranMap(view);
            }
        });
        button = (Button) findViewById(R.id.buttonAppBlock);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAllerEcranChoixApp(view);
            }
        });
        Intent retourMap = getIntent();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        AfficherAdresse(retourMap);

    }

    public void AfficherAdresse(Intent retourMap){
        if (retourMap != null){
            adresse = retourMap.getStringExtra("adresse");
            lat = retourMap.getDoubleExtra("lat",0);
            lng = retourMap.getDoubleExtra("lng",0);
            TextView textview = (TextView)findViewById(R.id.textViewAdresse);
            textview.setText(adresse);
            textview.setVisibility(View.VISIBLE);
            textview = (TextView)findViewById(R.id.textViewLat);
            textview.setText(String.format(Locale.getDefault(),"%.6f",lat));
            textview.setVisibility(View.VISIBLE);
            textview = (TextView)findViewById(R.id.textViewLng);
            textview.setText(String.format(Locale.getDefault(),"%.6f",lng));
            textview.setVisibility(View.VISIBLE);
            //blockedLocation.setLatitude(lat);
            //blockedLocation.setLatitude(lng);
        }
    }

    public float getDistance(Location loc1, Location loc2){
        float distanceInMeters = loc1.distanceTo(loc2);
        return distanceInMeters;
    }

    public void onClickAllerEcranMap(View view) {
        Intent goMap = new Intent(this, MapsActivity.class);
        startActivity(goMap);
    }

    public void onClickAllerEcranChoixApp(View view) {
        Intent goAppBlock = new Intent(this, Choix_app_Activity.class);
        startActivity(goAppBlock);
    }
}
