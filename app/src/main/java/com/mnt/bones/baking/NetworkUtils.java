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
     * @param moviesJsonStr JSON response from server
     * @return ArrayList of Movie with all related data
     * @throws JSONException If JSON data cannot be properly parsed
     */
    /* TO-DO
    public static ArrayList<String> getRecipesDataFromJson(String moviesJsonStr)
            throws JSONException {

        ArrayList<String> recipesList = new ArrayList<>();

        //JSON objects
        final String OWN_NAME = "name";
        final String OWN_INGREDIENTS = "ingredients";
        final String OWN_STEPS = "steps";
        final String OWN_SERVING = "serving";

        JSONObject jsonData = new JSONObject(moviesJsonStr);
        //JSONArray recipeArray = jsonData.getJSONArray(OWN_RESULTS);

        for (int i = 0; i < recipeArray.length(); i++) {

            JSONObject movieJson = recipeArray.getJSONObject(i);

            String poster = movieJson.optString(OWN_POSTER);
            String overview = movieJson.optString(OWN_OVERVIEW);
            String movieId = movieJson.optString(OWN_MOVIE_ID);
            String rating = "" + movieJson.getDouble(OWN_RATING);
            String releaseDate = movieJson.optString(OWN_RELEASE_DATE);
            String originalTitle = movieJson.optString(OWN_ORIGINAL_TITLE);

            String[] releaseYear = releaseDate.split("-");



            Log.v(TAG, "to do");


        }

        return movieList;

    }
    */

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
