package com.android.littlebits.inventions.networking;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by bhavya.narra on 3/31/2017.
 */

/**
 * A helper singleton class to make and manage network requests made in the app.
 */
public class ServiceManager {

    private static final String TAG = "Inv-ServManager";
    private static final String base_url = "http://littlebits.cc/api/";
    private static final String invention_url = "v2/inventions/7311";

    private OkHttpClient sClient;
    private Request mRequest;
    private static ServiceManager sInstance;

    /**
     * Private constructor
     *
     * We setup OkHttp to use API endpoint
     */
    private  ServiceManager() {
        sClient = new OkHttpClient();
        mRequest = new Request.Builder()
                    .url(base_url+invention_url)
                    .build();
    }

    public static ServiceManager getServiceManager() {
        if(sInstance == null) {
            sInstance = new ServiceManager();
        }
        return sInstance;
    }

    /**
     *
     * @param responseTaskHandler
     */
    public void getInventionDetail(final ReponseTaskHandler responseTaskHandler ) {
        sClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                responseTaskHandler.onFailure(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                responseTaskHandler.onSuccess(response);
            }
        });
    }
}
