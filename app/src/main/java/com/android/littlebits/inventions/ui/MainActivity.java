package com.android.littlebits.inventions.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.littlebits.inventions.R;
import com.android.littlebits.inventions.models.InventionDetails;
import com.android.littlebits.inventions.networking.ReponseTaskHandler;
import com.android.littlebits.inventions.networking.ServiceManager;
import com.android.littlebits.inventions.parser.ResponseParser;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {

    ServiceManager sManager;
    public static final String TAG = "Inv-main";
    private TextView mName;
    private TextView mDescription;
    private TextView mLikes;
    private TextView mComments;
    private TextView mTags;
    private InventionDetails mItem;
    private ImageView mLaunchProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = (TextView) findViewById(R.id.name);
        mDescription = (TextView) findViewById(R.id.description_text);
        mTags = (TextView) findViewById(R.id.tags_txt);
        mLikes = (TextView) findViewById(R.id.likes);
        mComments = (TextView) findViewById(R.id.comment);
        mLaunchProducts = (ImageView) findViewById(R.id.right_icon);
        mLaunchProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BuildingStepsActivity.class);
                startActivity(i);
            }
        });
        setUpUi();
    }

    //TODO - add splashscreen for cold launch
    //FIXME- UI for TAGS (understand what TGAS signifies and place accordingly)
    public void setUpUi() {
        Log.d(TAG, "callApi");
        sManager = ServiceManager.getServiceManager();
        sManager.getInventionDetail(new ReponseTaskHandler() {
            @Override
            public void onFailure(Exception ex) {
                Log.d(TAG, "ONFAILURE");
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(Response response) {
                Log.d(TAG, "ONSUCCESS");
                ResponseParser responseParser = new ResponseParser(response);
                mItem = responseParser.parseResDetails(responseParser.mJObject);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mName.setText(mItem.getName());
                        mName.setTypeface(null, Typeface.BOLD);
                        mTags.setText(mItem.getTags());
                        mDescription.setText(Html.fromHtml(mItem.getDescription()));
                        Log.d(TAG, "desc: "+ mItem.getDescription());
                        mLikes.setText(String.valueOf(mItem.getlCount()));
                        mComments.setText(String.valueOf(mItem.getcCount()));
                    }
                });
            }
        });
    }


}

