package com.example.weatherapp.Utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchUrlResult extends AsyncTask<String, Integer, String> {
    protected String doInBackground(String... urls) {
        String content = "", line;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = rd.readLine()) != null) {
                content += line + "\n";
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return content;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(String result) {
        // this is executed on the main thread after the process is over
        // update your UI here
        Log.i("RESULT", result);
        //displayMessage(result);
    }
}
