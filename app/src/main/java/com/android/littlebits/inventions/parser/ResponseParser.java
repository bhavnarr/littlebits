package com.android.littlebits.inventions.parser;

import com.android.littlebits.inventions.models.InventionDetails;
import com.android.littlebits.inventions.models.ProductImages;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhavya.narra on 3/31/2017.
 */

/**
 * ResponseParser contains methods to parse JSON data from REST API
 */
public class ResponseParser {

    public JSONObject mJObject;
    private static final String RESULTS = "results";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String LIKE_COUNT = "like_count";
    private static final String COMMENT_COUNT = "comment_count";
    private static final String PRODUCTS = "products";
    private static final String IMAGE = "image";
    private static final String TAGS = "tags";

    public ResponseParser(Response response) {
        try {
            mJObject = new JSONObject(response.body().string());
        } catch (final JSONException ex1) {
            ex1.printStackTrace();
        } catch (final IOException ex2) {
            ex2.printStackTrace();
        }
    }

    /**
     *
     * @param obj json response
     * @return res.name, res.description
     * res.likescount, res.commentscount
     * res.tags
     */
    public InventionDetails parseResDetails(JSONObject obj) {
        String res_name = "";
        String res_desc="";
        String res_tags="";
        int comment_cnt = 0;
        int like_cnt = 0;
        InventionDetails item = new InventionDetails();
        try {
            JSONObject res = obj.getJSONObject(RESULTS);
            res_name = res.getString(NAME);
            item.setName(res_name);
            res_desc = res.getString(DESCRIPTION);
            item.setDescription(res_desc);
            res_tags = res.getString(TAGS);
            item.setTags(res_tags);
            comment_cnt = res.getInt(COMMENT_COUNT);
            item.setcCount(comment_cnt);
            like_cnt = res.getInt(LIKE_COUNT);
            item.setlCount(like_cnt);
        } catch (JSONException ex1) {
            ex1.printStackTrace();
        }
        return item;
    }

    /**
     *
     * @param obj - json response
     * @return res.product.imageurl and res.product.name
     */
    public List<ProductImages> getProducts(JSONObject obj) {
        List<ProductImages> img_list = new ArrayList();
        try {
            JSONObject res = obj.getJSONObject(RESULTS);
            JSONArray products = res.getJSONArray(PRODUCTS);
            for(int i=0; i<products.length(); i++) {
                JSONObject jObj = products.getJSONObject(i);
                String url = jObj.getString(IMAGE);
                String name = jObj.getString(NAME);
                ProductImages img = new ProductImages();
                img.setImageUrl(url);
                img.setimg_name(name);
                img_list.add(img);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return img_list;
    }
}
