package com.example.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class LOCAsyncTaskLoader extends AsyncTaskLoader<String> {
    private String queryString;
    public LOCAsyncTaskLoader(Context context, String queryString){
        super(context);
        this.queryString =queryString;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        String baseURL ="https://web6.seattle.gov/Travelers/api/Map/Data";
        return NetworkConnection.getData(baseURL,"zoomId","13", "type", "2");
    }
    @Override
    protected void onStartLoading(){
        forceLoad();
    }

}
