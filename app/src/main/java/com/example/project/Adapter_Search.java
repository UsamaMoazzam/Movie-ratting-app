package com.example.project;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class Adapter_Search extends RecyclerView.Adapter<Adapter_Search.ViewHolder> implements Filterable
{   Context context;
    ArrayList<show> shows;
    ArrayList<show> shows1;
    public Adapter_Search(Context context, ArrayList<show> shows ) {
        this.context = context;
        this.shows = shows;
        shows1=new ArrayList<>(shows);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.search,parent,false);
return new ViewHolder(view); }

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
                intent.putExtra("moviename", moviez.getName());
                intent.putExtra("movierating", moviez.getRating());
                intent.putExtra("cast",moviez.getCast());
                intent.putExtra("moviegenre", moviez.getGenre());
                intent.putExtra("moviedescription", moviez.getDescription());
                intent.putExtra("movieimg",  moviez.getImg());
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

    @Override
    public Filter getFilter() {
        return example;
    }
   private Filter example=new Filter() {
       @Override
       protected FilterResults performFiltering(CharSequence constraint) {
           ArrayList<show> shows2=new ArrayList<>();
           if(constraint==null|| constraint.length()==0)
           {
               shows2.addAll(shows);
           }
           else
           {
               String filterpattern= constraint.toString().toLowerCase().trim();
               for(show sh:shows)
               {
                   if(sh.getName().toLowerCase().contains(filterpattern))
                   {
                       shows2.add(sh);
                   }
               }
           }
           FilterResults results=new FilterResults();
           results.values=shows2;
           return  results;
       }

       @Override
       protected void publishResults(CharSequence constraint, FilterResults results) {
           shows.clear();
           shows.addAll((List) results.values);
           notifyDataSetChanged();
       }
   };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tx1,tx2,tx3,tx4,tx5,tx6;
        ImageView img;
        ConstraintLayout constraintLayout;
        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tx1=(TextView)itemView.findViewById(R.id.name);
            tx2=(TextView)itemView.findViewById(R.id.genre);
            tx3=(TextView)itemView.findViewById(R.id.rating);
            img=itemView.findViewById(R.id.movPIC);
            constraintLayout=itemView.findViewById(R.id.sea1);
        }
    }


}
