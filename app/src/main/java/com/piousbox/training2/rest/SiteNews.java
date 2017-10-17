package com.piousbox.training2.rest;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

public class SiteNews extends AsyncTask<Void, Void, String[]> {

    @Override
    protected String[] doInBackground(Void... params) {
        String[] out;
        try {
            URL url = new URL("http://manager.piousbox.com/api/sites/view/travel-guide.mobi.json");
            InputStream is = url.openStream();
            javax.json.JsonReader rdr = Json.createReader(is);
            JsonObject obj = rdr.readObject();
            Log.v("abba", "obj:" + obj);

            JsonObject obj2 = obj.getJsonObject("site");
            Log.v("abba", "obj:" + obj2);

            JsonArray news  = obj2.getJsonArray("newsitems");
            Log.v("abba", "array:" + news);

            out = new String[news.size()];

            for (int i = 0; i < news.size(); i++) {
                JsonObject temp = news.getJsonObject(i);
                out[i] = temp.getString("description");
            }

            return out;
        } catch (Exception e) {
            System.out.println("catastrophic exception:" + e.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.v("abba", "onPostExecute");

        // Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
        // etResponse.setText(result);
    }

    /**
     * trash
     */
    private String title;
    private String description;

    public static String getContent() {
        return "some content";
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "@TODO: replace this, toString of SiteNews.java";
    }

}
