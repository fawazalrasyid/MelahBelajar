package com.fawazalrasyid.mlhbelajar.adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fawazalrasyid.mlhbelajar.R;
import com.fawazalrasyid.mlhbelajar.model.CardDetail;
import com.squareup.picasso.Picasso;

public class CardDetailViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public ImageView ivImg;
    public FrameLayout rv;

    public CardDetailViewHolder (View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.txtcard);
        ivImg = itemView.findViewById(R.id.imgcard);
        rv = itemView.findViewById(R.id.rv);

    }

    public void bindToCard(CardDetail card, View.OnClickListener onClickListener){
        tvName.setText(card.txt);
        Picasso.get()
                .load(card.img)
                //.resize(80, 80)
                .into(ivImg);
        rv.setOnClickListener(onClickListener);

    }
}
