package com.android.littlebits.inventions.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.littlebits.inventions.R;
import com.android.littlebits.inventions.adapters.ProdImageAdapter;
import com.android.littlebits.inventions.models.ProductImages;
import com.android.littlebits.inventions.networking.ServiceManager;
import com.android.littlebits.inventions.networking.ReponseTaskHandler;
import com.android.littlebits.inventions.parser.ResponseParser;
import com.squareup.okhttp.Response;

import java.util.ArrayList;


public class BuildingStepsActivity extends AppCompatActivity {

    private static final String TAG = "Inv-Steps";
    private RecyclerView mRecyclerView;
    private ProdImageAdapter mProdImageAdapter;
    private ServiceManager mServiceManager;
    private ArrayList<ProductImages> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.building_steps);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(layoutManager);
        mList = new ArrayList<ProductImages>();
        mProdImageAdapter = new ProdImageAdapter(BuildingStepsActivity.this, mList);
        mRecyclerView.setAdapter(mProdImageAdapter);
        setupUi();
    }

    //TODO - add progress dialog until image loading sucessful
    public void setupUi() {
        mServiceManager = ServiceManager.getServiceManager();
        mServiceManager.getInventionDetail(new ReponseTaskHandler() {
            @Override
            public void onFailure(Exception ex) {
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(Response response) {
                ResponseParser responseParser = new ResponseParser(response);
                if(responseParser != null) {
                    Log.d(TAG, "responseParser is not null");
                }
                mList.addAll(responseParser.getProducts(responseParser.mJObject));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProdImageAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
