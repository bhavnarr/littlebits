package com.android.littlebits.inventions.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.littlebits.inventions.R;

/**
 * Created by bhavya.narra on 4/1/2017.
 */

/**
 * ViewHolder for result.product.image
 * displays image and corresponding name
 */
public class ProdImageViewHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mTextView;

    public ProdImageViewHolder(View itemView) {

        super(itemView);
        mImageView = (ImageView)itemView.findViewById(R.id.list_item_prod_image_view);
        mTextView = (TextView)itemView.findViewById(R.id.list_item_prod_text_view);

    }
}
