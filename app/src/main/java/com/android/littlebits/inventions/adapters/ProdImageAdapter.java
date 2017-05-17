package com.android.littlebits.inventions.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.littlebits.inventions.R;
import com.android.littlebits.inventions.models.ProductImages;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by bhavya.narra on 4/1/2017.
 */

/*
 * ProdImageAdapter to display result.product.images
 */
public class ProdImageAdapter extends RecyclerView.Adapter<ProdImageViewHolder> {

    private Context mContext;
    private ArrayList<ProductImages> img_list;

    public ProdImageAdapter(Context c, ArrayList<ProductImages> list) {
        this.mContext = c;
        this.img_list = list;
    }
    @Override
    public ProdImageViewHolder  onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new ProdImageViewHolder(view);
    }

    //FIXME - all images are not loading into recyclerview
    @Override
    public void onBindViewHolder(ProdImageViewHolder holder, int position) {
        final ProductImages img = img_list.get(position);
        holder.mTextView.setText(img.getImgName());
        Picasso.with(mContext).load(img.getImageUrl()).placeholder(R.mipmap.placeholder)
                                                        .resize(120, 60)
                                                       .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return img_list.size();
    }
}
