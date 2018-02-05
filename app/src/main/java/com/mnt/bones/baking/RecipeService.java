package com.mnt.bones.baking;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fabio.a on 05/02/18.
 */

public class RecipeService extends AsyncTask<Void, Void, ArrayList<Recipe>> {

    private static final String TAG = RecipeService.class.getSimpleName();
    private Context mContext;
    private AsyncTaskDelegate delegateTask;

    public RecipeService(Context context, AsyncTaskDelegate responder) {
        this.mContext = context;
        this.delegateTask = responder;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        delegateTask.onPreStart();
    }

    @Override
    protected ArrayList<Recipe> doInBackground(Void... voids) {

        try {
            String jsonString = NetworkUtils.getResponseFromHttpUrl();
            ArrayList<Recipe> recipesList = NetworkUtils.getRecipesDataFromJson(jsonString);

            Log.d(TAG, "Get data from JSON success");

            return recipesList;
        } catch (IOException e) {
            Log.e(TAG, "Failed to Load Data");
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            Log.e(TAG, "Failed to Load JSONData");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Recipe> recipes) {
        if (recipes != null){
            delegateTask.onFinish(recipes);
        }
    }
}
