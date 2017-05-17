package com.android.littlebits.inventions.models;

/**
 * Created by bhavya.narra on 3/31/2017.
 */

/*
 * A POJO object to represent an result.details
 *
 */
public class InventionDetails {

    private String name;
    private String desc;
    private String tags;
    private int lCount;
    private int cCount;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String discription) {
        this.desc = discription;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setlCount(int lCount) {
        this.lCount = lCount;
    }
    public void setcCount(int cCount) {
        this.cCount = cCount;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.desc;
    }
    public String getTags() {
        return this.tags;
    }
    public int getlCount() {
        return this.lCount;
    }
    public int getcCount() {
         return this.cCount;
    }
}
