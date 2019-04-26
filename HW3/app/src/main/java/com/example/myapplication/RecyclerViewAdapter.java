package com.example.myapplication;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    static final String MOVIE = "movie";
    static final String YEAR = "year";
    static final String DIRECTOR = "director";
    static final String URL = "url";
    static final String DESCRIPTION = "description";
    static final String TAG = "RecyclerViewAdapter";
    private Context context;
    String[][] movies;







    public RecyclerViewAdapter(Context context, String[][] movies) {
        this.context = context;
        this.movies = movies;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int n) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final int n =i;

        viewHolder.movie.setText(movies[n][0]);
        viewHolder.year.setText(movies[n][1]);
        viewHolder.parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,displayMoviesActivity.class);
                intent.putExtra(MOVIE,movies[n][0]);
                intent.putExtra(YEAR,movies[n][1]);
                intent.putExtra(DIRECTOR,movies[n][2]);
                intent.putExtra(URL,movies[n][3]);
                intent.putExtra(DESCRIPTION,movies[n][4]);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.movies.length;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout parent;
        TextView movie;
        TextView year;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.linearLayout);
            movie = itemView.findViewById(R.id.movie);
            year = itemView.findViewById(R.id.year);

        }
    }
}