package com.mnt.bones.baking;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by fabio.a on 05/02/18.
 */

public class RecipeService extends AsyncTask<Void, Void, String> {

    private Context mContext;
    private AsyncTaskDelegate delegateTask;

    public RecipeService(Context context, AsyncTaskDelegate responder) {
        this.mContext = context;
        this.delegateTask = responder;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {

        String string = "";

        try {
            string = NetworkUtils.getResponseFromHttpUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return string;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
