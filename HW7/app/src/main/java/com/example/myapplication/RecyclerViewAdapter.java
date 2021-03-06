package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMapOptions;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Listener listener;



    public void setListener(Listener listener) {

        this.listener = listener;

    }



    interface Listener {

        void onClick(int position);

    }

    private TrafficCamera[] cameraData;
    private String TAG="RecyclerViewAdapter: ";




    public RecyclerViewAdapter(ArrayList<TrafficCamera> cameraData) {
        this.cameraData= cameraData.toArray(new TrafficCamera[0]);
    }
    @Override
    public int getItemCount() {
        return cameraData.length;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_traffic,viewGroup,false);
        ViewHolder holder = new ViewHolder( (view) )  ;

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        View view = viewHolder.itemView;
        TrafficCamera newCamera = cameraData[i];
        viewHolder.imageName.setText(newCamera.getDescription()  );
        String imgURl = newCamera.getImageUrl();
        Log.d(TAG,"image url: " + imgURl);
        Picasso.get().load(newCamera.getImageUrl())
                //.resize(200,200)
                .into(viewHolder.image);

        view.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                if (listener != null) {

                    listener.onClick(i);

                }

            }

        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView imageName;
        LinearLayout parentLayout;

        public ViewHolder(View itemView){
            super(itemView);
            image=itemView.findViewById(R.id.image );
            imageName=itemView.findViewById( R.id.image_name );
            parentLayout=itemView.findViewById( R.id.parent_layout );
        }


    }

}