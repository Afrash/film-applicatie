package com.example.filmapplicatie;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NetworkUtils extends AsyncTask<String, Void, ArrayList<Movie>> {

    private OnElementApiListener listener;
    private static String TAG = NetworkUtils.class.getName();


    public NetworkUtils(OnElementApiListener onElementApiListener){
        Log.i(TAG, "Constructor is called");
        this.listener = onElementApiListener;
    }
//?api_key=b6f53c81e5115a4f1b13c9f2e25785a0&language=en-US&page=1&include_adult=false
    final static String TMDB_BASE_URL = "https://api.themoviedb.org/3/search/movie";
    final static String PARAM_QUERY = "query=";



    final static String PARAM_APIKEY = "api_key";
    final static String apiKey = "b6f53c81e5115a4f1b13c9f2e25785a0";



    //bouwd de URL om github te query
    public static URL buildURL(String movieSearchQuery){
        //eerst word de Uri gemaakt
        Uri builtUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_APIKEY, apiKey)
                .appendQueryParameter(PARAM_QUERY, movieSearchQuery)
                .build();

        //hier maak je een URL van de Uri
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }



    private ArrayList<Movie> createElementsFromJson(String response) {
        Log.i(TAG, "createElementFromJson called");
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            JSONObject jsonResults = new JSONObject(response);
            JSONArray movieList = jsonResults.getJSONArray("results");
            //Json op de site is een string dus personslist.size = index = 0
            Log.i(TAG, "moviesArray length = " + movieList.length());

            for (int i = 0; i < movieList.length(); i++){
                //json object voor het element
                JSONObject movie = movieList.getJSONObject(i);
                //objecten die worden meegegeven voor de RecyclerView
                String popularity = movie.getString("popularity");
                String vote_count = movie.getString("vote_count");
                String image = movie.getString("poster_path");
                String identificationNumber = movie.getString("id");
                String language= movie.getString("original_language");
                String title = movie.getString("original_title");
                String vote_average= movie.getString("vote_average");
                String overview  = movie.getString("overview");
                String release_date = movie.getString("release_date");
                //.getJSONObject("attributes").

                String date = getPlaceDate(release_date);

                Movie movie_item = new Movie(popularity,
                        vote_count,
                        image,
                        identificationNumber,
                        language,
                        title,
                        vote_average,
                        overview,
                        date);
                movies.add(movie_item);
            }
        } catch (JSONException e) {
            Log.e(TAG, "error " + e.getMessage());
        }

        Log.e(TAG, "number of movies in list =  " + movies.size());
        return movies;
    }

    private String doSendRequestToAPI(String urlApiString) {
        //krijgt URL, stuurt resultaat terug, resultaat is de json String

        //inputstream krijgt binnenkomende tekst binnen
        InputStream inputStream = null;
        int responseCode = -1;

        // Het resultaat dat we gaan retourneren
        String response = "";

        // de i bij log staat voor informatie
        Log.i(TAG, "doInBackground '" + urlApiString + "'");

        // Maak verbinding met de urlApiString en haal het response op
        try {
            URL url = new URL(urlApiString);
            URLConnection urlConnection = url.openConnection();
            Log.i(TAG, "response is correct");
            // Als het niet gelukt is om een URL connection op te zetten moeten we stoppen
            if (!(urlConnection instanceof HttpURLConnection)) {
                Log.e(TAG, "Niet gelukt om een URL connectie te maken!");
                return null;
            }

            // Initiëer de connectie en maak verbinding
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            //GET aanvraag naar de server, krijgt de data van de server
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // De verbinding is gelukt. Lees hier de data.
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                Log.i(TAG, "response = " + response);
            } else {
                // Verbinding lukte, maar de server geeft een foutcode
                Log.e(TAG, "Fout in responsCode: code = " + responseCode);
            }
        } catch (MalformedURLException e) {
            // De URL was niet correct geformuleerd.
            Log.e(TAG, "MalformedURLEx " + e.getMessage());
            return null;
        } catch (IOException e) {
            // Het lukte niet om verbinding te maken.
            Log.e(TAG, "IOException " + e.getMessage());
            return null;
        }
        // Hier hebben we een resultaat
        Log.e(TAG, "Succesfull response send");

        return response;
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // Het lukte niet om verbinding te maken.
                    Log.e(TAG, "doInBackground IOException " + e.getMessage());
                }
            }
        }
        return sb.toString();
    }

    //word opgeroepen na de doInBackground methode
    @Override
    protected void onPostExecute(ArrayList<Movie> response) {
        Log.i(TAG, "onPostExecute called");
        Log.i(TAG, "response = " + response);
        //onElementAvailable is een methode van de interface onElementApiListener
        listener.onElementAvailable(response);
    }

    //word als eerst opgeroepen
    @Override
    protected ArrayList<Movie> doInBackground(String... inputParams) {
        Log.i(TAG, "doInBackground called");
        String url = inputParams[0];
        Log.i(TAG, "inputParams = " + url);

        String response = null;
        response = doSendRequestToAPI(url);

        ArrayList<Movie> movies = createElementsFromJson(response);

        Log.i(TAG, "response = " + response);
        Log.i(TAG, "doInBackGround finished");


        return movies;
    }

    public String getPlaceDate(String date) {
        Log.i(TAG, "change Date");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(date));
            String currentTime = formatter.format(calendar.getTime());
            return currentTime;
        } catch (Exception e){
            return "Geen data beschikbaar";
        }
    }

    //Main activity en Networkutilsclass kunnen communiceren met elkaar
    public interface OnElementApiListener{
        public void onElementAvailable(ArrayList<Movie> movies);
    }
}
