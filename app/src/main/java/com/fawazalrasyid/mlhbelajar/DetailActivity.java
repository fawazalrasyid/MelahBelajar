package com.fawazalrasyid.mlhbelajar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fawazalrasyid.mlhbelajar.adapter.CardDetailViewHolder;
import com.fawazalrasyid.mlhbelajar.model.App;
import com.fawazalrasyid.mlhbelajar.model.CardDetail;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    DatabaseReference reference;

    private FirebaseRecyclerAdapter<CardDetail, CardDetailViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private InterstitialAd mInterstitialAd;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Ads Admob
        reference = FirebaseDatabase.getInstance().getReference().child("AppSett").child("Ads");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final App app = dataSnapshot.getValue(App.class);

                View adContainer = findViewById(R.id.adMobView);

                //Banner
                AdView mAdView = new AdView(getBaseContext());
                mAdView.setAdSize(AdSize.BANNER);
                mAdView.setAdUnitId(app.getBanner());
                ((RelativeLayout)adContainer).addView(mAdView);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);

                //Interstitial
                mInterstitialAd = new InterstitialAd(getBaseContext());
                mInterstitialAd.setAdUnitId(app.getInterstisial());
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        mInterstitialAd.show();
                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //onclick
        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0,0);
                finish();
            }
        });

        Button more = (Button) findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this, MoreActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0,0);
                finish();
            }
        });


        // Get Extra from MainActivity
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        reference = FirebaseDatabase.getInstance().getReference().child("Detail").child(String.valueOf(id));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final App app = dataSnapshot.getValue(App.class);

                TextView tv = (TextView)findViewById(R.id.txt);
                ImageView iv = (ImageView)findViewById(R.id.bg);

                tv.setText(app.getTxt());
                Picasso.get()
                        .load(app.getBg())
                        .into(iv);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRecycler = findViewById(R.id.rv_home);
        GridLayoutManager gridLayout = new GridLayoutManager(DetailActivity.this, 1 );
        mRecycler.setLayoutManager(gridLayout);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<CardDetail>()
                .setQuery(query, CardDetail.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<CardDetail, CardDetailViewHolder>(options) {

            @Override
            public CardDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new CardDetailViewHolder(inflater.inflate(R.layout.item_card, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull CardDetailViewHolder holder, int position, @NonNull final CardDetail model) {
                holder.bindToCard(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DetailActivity.this, WebActivity.class);
                        intent.putExtra("url", model.url);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        overridePendingTransition(0,0);
                        startActivity(intent);
                    }
                });
            }
        };

        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null){
            mAdapter.startListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase){
        Query query = mDatabase.child("Detail").child(String.valueOf(id)).child("card");
        return query;
    }


    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}