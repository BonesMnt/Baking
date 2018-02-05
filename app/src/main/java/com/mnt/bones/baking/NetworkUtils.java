package com.mnt.bones.baking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by fabio.a on 05/02/18.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String RECIPES_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";


    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl() throws IOException {
        URL recipesUrl = new URL(RECIPES_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) recipesUrl.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null){
                return null;
            }

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                String output = scanner.next();
                Log.d(TAG, output);
                return output;
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    /**
     * This method parses JSON from a web response and returns an ArrayList of Movie
     *
     * @param jsonString JSON response from server
     * @return ArrayList of Movie with all related data
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static ArrayList<Recipe> getRecipesDataFromJson(String jsonString)
            throws JSONException {

        ArrayList<Recipe> recipesList = new ArrayList<>();

        //JSON objects
        final String OWN_NAME = "name";
        final String OWN_INGREDIENTS = "ingredients";
        final String OWN_STEPS = "steps";
        final String OWN_SERVING = "serving";
        final String OWN_QUANTITY = "quantity";
        final String OWN_MEASURE = "measure";
        final String OWN_INGREDIENT = "ingredient";
        final String OWN_SHORT_DESCRIPTION = "shortDescription";
        final String OWN_DESCRIPTION = "description";
        final String OWN_VIDEO_URL = "videoURL";
        final String OWN_THUMBNAIL_URL = "thumbnailURL";

        JSONArray recipeArray = new JSONArray(jsonString);

        for (int i = 0; i < recipeArray.length(); i++) {

            JSONObject recipeJson = recipeArray.getJSONObject(i);

            String name = recipeJson.optString(OWN_NAME);

            JSONArray ingredientsArray = recipeJson.getJSONArray(OWN_INGREDIENTS);
            ArrayList<Ingredient> ingredientsList = new ArrayList<>();

            for (int j = 0; j < ingredientsArray.length(); j++){
                JSONObject ingredientJson = ingredientsArray.getJSONObject(j);

                String quantity = "" + ingredientJson.getInt(OWN_QUANTITY);
                String measure = ingredientJson.optString(OWN_MEASURE);
                String ingredient = ingredientJson.optString(OWN_INGREDIENT);

                Ingredient ingredientObject = new Ingredient(quantity, measure, ingredient);
                ingredientsList.add(ingredientObject);
            }

            JSONArray stepsArray = recipeJson.getJSONArray(OWN_STEPS);
            ArrayList<Step> stepsList = new ArrayList<>();

            for (int j = 0; j < stepsArray.length(); j++){
                JSONObject stepJson = stepsArray.getJSONObject(j);

                String shortDescription = stepJson.optString(OWN_SHORT_DESCRIPTION);
                String description = stepJson.optString(OWN_DESCRIPTION);
                String videoUrl = stepJson.optString(OWN_VIDEO_URL);
                String thumbnailUrl = stepJson.optString(OWN_THUMBNAIL_URL);

                Step stepObject = new Step(shortDescription, description, videoUrl, thumbnailUrl);
                stepsList.add(stepObject);
            }

            String serving = recipeJson.optString(OWN_SERVING);

            Recipe recipeObject = new Recipe(name, ingredientsList, stepsList, serving);
            Log.v(TAG, recipeObject.toString());
            recipesList.add(recipeObject);



        }

        return recipesList;

    }


    /**
     * This method checks if there is Internet Connection and return it.
     *
     * @param context use to get connectivity service status
     * @return the status .
     */
    public static boolean hasInternetConnection(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }


}
