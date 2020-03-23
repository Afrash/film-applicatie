package com.example.filmapplicatie;

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

public class NetworkUtils extends AsyncTask<String, Void, ArrayList<Element>> {

    private OnElementApiListener listener;
    private static String TAG = NetworkUtils.class.getName();

    public NetworkUtils(OnElementApiListener onElementApiListener){
        Log.i(TAG, "Constructor is called");
        this.listener = onElementApiListener;
    }

    private ArrayList<Element> createElementsFromJson(String response) {
        Log.i(TAG, "createElementFromJson called");
        ArrayList<Element> elements = new ArrayList<>();

        try {
            JSONObject jsonResults = new JSONObject(response);
            JSONArray elementList = jsonResults.getJSONArray("features");
            //Json op de site is een string dus personslist.size = index = 0
            Log.i(TAG, "elementArray length = " + elementList.length());

            for (int i = 0; i < elementList.length(); i++){
                //json object voor het element
                JSONObject element = elementList.getJSONObject(i);
                //objecten die worden meegegeven voor de RecyclerView
                String image = element.getJSONObject("attributes").getString("URL");
                String title = element.getJSONObject("attributes").getString("AANDUIDINGOBJECT");
                String geographical_location = element.getJSONObject("attributes").getString("GEOGRAFISCHELIGGING");
                String identificationNumber = element.getJSONObject("attributes").getString("IDENTIFICATIE");
                String artist = element.getJSONObject("attributes").getString("KUNSTENAAR");
                String description = element.getJSONObject("attributes").getString("OMSCHRIJVING");
                String material = element.getJSONObject("attributes").getString("MATERIAAL");
                String underground = element.getJSONObject("attributes").getString("ONDERGROND");
                String placement_date = element.getJSONObject("attributes").getString("PLAATSINGSDATUM");

                String date = getPlaceDate(placement_date);

                Element element_item = new Element(image,
                        title,
                        geographical_location,
                        identificationNumber,
                        artist,
                        description,
                        material,
                        underground,
                        date);
                elements.add(element_item);
            }
        } catch (JSONException e) {
            Log.e(TAG, "error " + e.getMessage());
        }

        Log.e(TAG, "number of elements in list =  " + elements.size());
        return elements;
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
    protected void onPostExecute(ArrayList<Element> response) {
        Log.i(TAG, "onPostExecute called");
        Log.i(TAG, "response = " + response);
        //onElementAvailable is een methode van de interface onElementApiListener
        listener.onElementAvailable(response);
    }

    //word als eerst opgeroepen
    @Override
    protected ArrayList<Element> doInBackground(String... inputParams) {
        Log.i(TAG, "doInBackground called");
        String url = inputParams[0];
        Log.i(TAG, "inputParams = " + url);

        String response = null;
        response = doSendRequestToAPI(url);

        ArrayList<Element> elements = createElementsFromJson(response);

        Log.i(TAG, "response = " + response);
        Log.i(TAG, "doInBackGround finished");


        return elements;
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
        public void onElementAvailable(ArrayList<Element> elements);
    }
}
