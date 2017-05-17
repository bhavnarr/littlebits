package com.android.littlebits.inventions.models;

/**
 * Created by bhavya.narra on 4/1/2017.
 */

/*
 * A POJO object to represent result.product.images
 *
 */
public class ProductImages {

    String img_url;
    String img_name;

    public void setImageUrl(String img_url) {
        this.img_url = img_url;
    }

    public void setimg_name(String img_name) {
        this.img_name = img_name;
    }

    public String getImageUrl() {
        return this.img_url;
    }

    public String getImgName() {
        return this.img_name;
    }
}
