package com.android.littlebits.inventions.networking;

import com.squareup.okhttp.Response;

/**
 * Created by bhavya.narra on 4/1/2017.
 */

/**
 * A callback listener to surface api callbacks to the main level
 */

public interface ReponseTaskHandler {
    void onSuccess(Response response);
    void onFailure(Exception ex);
}
