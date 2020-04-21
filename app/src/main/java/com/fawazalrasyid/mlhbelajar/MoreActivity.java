package com.fawazalrasyid.mlhbelajar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fawazalrasyid.mlhbelajar.model.App;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MoreActivity extends AppCompatActivity {

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        TextView cr = (TextView)findViewById(R.id.cr);
        cr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://fawazalrasyid.cf/")));
            }
        });

        //onclick
        Button detail = (Button) findViewById(R.id.detail);
        detail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, DetailActivity.class);
                i.putExtra("id","1");
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0,0);
                finish();
            }
        });

        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, MainActivity.class);
                i.putExtra("id","0");
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0,0);
                finish();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("AppSett");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final App app = dataSnapshot.getValue(App.class);

                TextView tv = (TextView)findViewById(R.id.AppName);
                ImageView iv = (ImageView)findViewById(R.id.AppLogo);
                tv.setShadowLayer(1, 0, 0, Color.BLACK);

                tv.setText(app.getName());
                Picasso.get()
                        .load(app.getLogo())
                        .into(iv);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
