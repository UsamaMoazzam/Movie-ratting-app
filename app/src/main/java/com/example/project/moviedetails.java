package com.example.project;
import androidx.annotation.NonNull;import androidx.appcompat.app.AppCompatActivity;import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class moviedetails extends AppCompatActivity {
    String data1,data2,data3,data4,data5,data6,data7;
    String imageid;
    show shows=new show("","","","","","","","");
    Parcelable  fav;
    Button b1,b2,b3,b4;
    TextView tx1,tx2,tx3,tx4,tx5,tx6,tx7;
    ImageView imageViewe;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails);
        tx1=findViewById(R.id.editText);
        tx2=findViewById(R.id.editText2);
        tx3=findViewById(R.id.editText3);
        tx4=findViewById(R.id.editText4);
        tx5=findViewById(R.id.Runtime1);
        tx6=findViewById(R.id.textView5);
        tx7=findViewById(R.id.cast);
        imageViewe=findViewById(R.id.imageView2);
        getData();
        setData();
        final DatabaseReference reference;
        final DatabaseReference reference1;
        reference=FirebaseDatabase.getInstance().getReference().child("Favourite");
        reference1=FirebaseDatabase.getInstance().getReference().child("Recently Watched");
        b1=findViewById(R.id.id1);
        b2=findViewById(R.id.id2);
        b3=findViewById(R.id.id3);
        b4=findViewById(R.id.id4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference1.push().setValue(shows);
                Toast.makeText(moviedetails.this,""+"Added in Recently Watched",Toast.LENGTH_SHORT).show();
            }});
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef1 = FirebaseDatabase.getInstance().getReference().child("Recently Watched");
                myRef1.addValueEventListener(new ValueEventListener() {
                    String key=null;
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            GenericTypeIndicator<HashMap<String, String>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, String>>() {};
                            Map<String, String> objectHashMap = dataSnapshot1.getValue(objectsGTypeInd);
                            ArrayList<String> objectArrayList = new ArrayList<String>(objectHashMap.values());
                            String name=objectArrayList.get(4);
                            if(name.compareTo(data1)==0)
                            {
                                key =dataSnapshot1.getKey();
                            }
                        }
                        if(key!=null)
                        {   DatabaseReference myRef1= FirebaseDatabase.getInstance().getReference().child("Recently Watched").child(key);
                        myRef1.removeValue();
                            Toast.makeText(moviedetails.this,""+"Removed in RECENTLY watched",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(moviedetails.this,""+"Database Error",Toast.LENGTH_SHORT).show();
                    }}


                    ); }
        });
       b3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               reference.push().setValue(shows);
               Toast.makeText(moviedetails.this,""+"Added in favourites",Toast.LENGTH_SHORT).show();
           }});
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef1 = FirebaseDatabase.getInstance().getReference().child("Favourite");
                myRef1.addValueEventListener(new ValueEventListener() {
                    String key=null;
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            GenericTypeIndicator<HashMap<String, String>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, String>>() {};
                            Map<String, String> objectHashMap = dataSnapshot1.getValue(objectsGTypeInd);
                            ArrayList<String> objectArrayList = new ArrayList<String>(objectHashMap.values());
                            String name=objectArrayList.get(4);
                            if(name.compareTo(data1)==0)
                            {
                                key =dataSnapshot1.getKey();
                            }
                        }
                        if(key!=null)
                        {   DatabaseReference myRef1= FirebaseDatabase.getInstance().getReference().child("Favourite").child(key);
                            myRef1.removeValue();
                            Toast.makeText(moviedetails.this,""+"Removed in  Favourite",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(moviedetails.this,""+"Database Error",Toast.LENGTH_SHORT).show();
                    }}); }
        });
 }
    private void getData() {
        if(getIntent().hasExtra("moviename") && getIntent().hasExtra("movierating")&&getIntent().hasExtra("moviegenre")&&getIntent().hasExtra("moviedescription")&& getIntent().hasExtra("movieimg"))
        {

                    data1=getIntent().getStringExtra("moviename");shows.name=data1;
                    fav=getIntent().getParcelableExtra("movie");
                    data2=getIntent().getStringExtra("movierating");shows.rating=data2;
                    data3=getIntent().getStringExtra("moviegenre");shows.setGenre(data3);
                    data4=getIntent().getStringExtra("moviedescription");shows.setDescription(data4);
                    data5=getIntent().getStringExtra("movietime");shows.setTime(data5);
                    data6=getIntent().getStringExtra("moviedate");shows.date=data6;
                    data7=getIntent().getStringExtra("cast");shows.cast=data7;
                    imageid=getIntent().getStringExtra("movieimg");shows.img=imageid;
        }
        else
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
    }
    private void setData() {
        tx1.setText(data1);
        tx2.setText(data2);
        tx3.setText(data3);
        tx4.setText(data4);
        tx5.setText(data5);
        tx6.setText(data6);
        tx7.setText(data7);
        Picasso.with(this).load(imageid).into(imageViewe);
    }
}
