
package com.example.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Recommended extends RecyclerView.Adapter<Adapter_Recommended.ViewHolder>
{
    Context context;
    ArrayList<show> shows;


    public Adapter_Recommended(Context context, ArrayList<show> shows ) {
        this.context = context;
        this.shows = shows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.recommended,parent,false);
return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            final show moviez=shows.get(position);
            holder.tx1.setText(moviez.getName());
            holder.tx2.setText(moviez.getRating());
            holder.tx3.setText(moviez.getGenre());
        Picasso.with(context).load(moviez.img).fit().into(holder.img);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,moviedetails.class);
                intent.putExtra("movie",  moviez);
                intent.putExtra("moviename", moviez.getName());
                intent.putExtra("cast",moviez.getCast());
                intent.putExtra("movierating", moviez.getRating());
                intent.putExtra("moviegenre", moviez.getGenre());
                intent.putExtra("moviedescription", moviez.getDescription());
                intent.putExtra("movieimg", moviez.getImg())                   ;
                intent.putExtra("moviedate",moviez.getDate());
                intent.putExtra("movietime",moviez.getTime());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return shows.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tx1,tx2,tx3,tx4,tx5,tx6;
        ImageView img;
        ConstraintLayout constraintLayout;
        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tx1=(TextView)itemView.findViewById(R.id.name);
            tx2=(TextView)itemView.findViewById(R.id.rating);
            tx3=(TextView)itemView.findViewById(R.id.genre);
            img=itemView.findViewById(R.id.movPIC);
            constraintLayout=itemView.findViewById(R.id.recommended1);
        }
    }
}
