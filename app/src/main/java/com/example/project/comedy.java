package com.example.project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class comedy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thriller);
        final ArrayList<show> shows=new ArrayList<>();
        DatabaseReference myRef ;
        Adapter_Movie adapter_movie;
        final ArrayList<show> movies = new ArrayList<>();
        RecyclerView recyclerView=findViewById(R.id.recthrill);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<String> l1=new ArrayList<>();
        final Adapter_Recommended adapter_recommended=new Adapter_Recommended(this,shows);
        recyclerView.setAdapter(adapter_recommended);
        for(int i=0;i<499;i++) {
            String k=Integer.toString(i);
            myRef = FirebaseDatabase.getInstance().getReference().child("Data").child(k);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    l1.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        l1.add(dataSnapshot1.getValue().toString());
                    }
                    String ele=l1.get(4);
                    String ke=ele.substring(1,ele.length()-1);
                    String elements[]=ke.split(",");
                    boolean found=false;
                    List<String> fixedLenghtList = Arrays.asList(elements);
                    ArrayList<String> listOfString = new ArrayList<String>(fixedLenghtList);
                    for(int i=0;i<listOfString.size();i++)
                    {
                        if(listOfString.get(i).toLowerCase().compareTo("comedy")==0)
                        {
                            found=true;
                        }
                    }
                    if (found) {
                        if (l1.get(6).length() == 0) {
                            l1.set(6, l1.get(12));
                        }
                        String k1=l1.get(4).substring(0,l1.get(4).length()-1);
                        l1.set(4,k1);
                        String k2=l1.get(4).substring(1,l1.get(4).length());
                        l1.set(4,k2);
                        String k3=l1.get(3).substring(2);
                        l1.set(3,k3);
                        show move = new show(l1.get(6), l1.get(4), l1.get(11), l1.get(5), l1.get(3), l1.get(10), l1.get(8),l1.get(0));
                        shows.add(move);
                        adapter_recommended.notifyDataSetChanged();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        for(int i=500;i<597;i++) {
            String k=Integer.toString(i);
            myRef = FirebaseDatabase.getInstance().getReference().child("Data").child(k);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    l1.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        l1.add(dataSnapshot1.getValue().toString());
                    }
                    show move = new show(l1.get(14), l1.get(4), l1.get(7), l1.get(19), l1.get(13), l1.get(11),l1.get(8),l1.get(0));
                    if(l1.get(19).compareTo("N/A")!=0){
                        String elements[]=l1.get(4).split(",");
                        boolean found=false;
                        List<String> fixedLenghtList = Arrays.asList(elements);
                        ArrayList<String> listOfString = new ArrayList<String>(fixedLenghtList);
                        for(int i=0;i<listOfString.size();i++)
                        {
                            if(listOfString.get(i).toLowerCase().compareTo("comedy")==0)
                            {
                                found=true;
                            }
                        }
                        if (found) {
                            shows.add(move);
                            adapter_recommended.notifyDataSetChanged();
                        }}
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}