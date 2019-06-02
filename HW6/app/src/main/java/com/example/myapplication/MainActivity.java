package com.example.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    private static final String TAG = "MainActivity: ";
    private TextView networkStatus;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManagermanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutManagermanager = new LinearLayoutManager(this);
        networkStatus  = findViewById(R.id.textView);
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        networkStatus.setText(getString( R.string.Connect));
        if (networkInfo != null && networkInfo.isConnected()) {
            Bundle bundle = new Bundle();
            bundle.putString("queryString", "");
            getSupportLoaderManager().restartLoader(0, bundle, this);

        } else {
            networkStatus.setText(getString( R.string.NotConnected));
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";
        if (bundle !=  null) {
            queryString = bundle.getString("queryString");
        }
        return new LOCAsyncTaskLoader( this, queryString );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        networkStatus.setText(getString( R.string.Loading));
        String ID="";
        String description = "";
        String imageUri = "";
        String type = "";
        ArrayList<TrafficCamera> allCameras = new ArrayList<>();

        try {

            JSONObject rootObject = new JSONObject(s);
            JSONArray features =  rootObject.getJSONArray("Features");

            for(int i = 0; i < features.length(); i++) {
                JSONObject newCameraObject = features.getJSONObject(i);
                JSONArray camera = newCameraObject.getJSONArray("Cameras");
                for(int j = 0; j < camera.length(); j++) {
                    JSONObject currentCameraObject = camera.getJSONObject(j);
                    ID = currentCameraObject.getString( "Id" );
                    description = currentCameraObject.getString("Description");
                    imageUri = currentCameraObject.getString("ImageUrl");
                    type = currentCameraObject.getString("Type");

                    Log.d( TAG ,"data: " +ID+", " +description+", "+imageUri+", " + type);
                    allCameras.add(new TrafficCamera.CameraBuilder().description(description).cameraImage(imageUri,type).buildCamera());
                }
            }
                adapter = new RecyclerViewAdapter( allCameras);
                RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
                //recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManagermanager);
                recyclerView.setAdapter(adapter);

        } catch(Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }
}
