package com.piousbox.training2.rest;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SiteNews extends AsyncTask<Void, Void, String[]> {

    @Override
    protected String[] doInBackground(Void... params) {
        /**
         * get json
         */
        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpPost httppost = new HttpPost("http://manager.piousbox.com/api/sites/view/travel-guide.mobi.json");
        httppost.setHeader("Content-type", "application/json");
        InputStream inputStream = null;
        String result = null;
        try {
            HttpResponse response = httpclient.execute(httppost);
            org.apache.http.HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
            // json is UTF-8 by default
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            result = sb.toString();
        } catch (Exception e) {
            // Oops
        }
        finally {
            try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
        }

        /**
         * parse json
         */
        String[] out;
        try {

            JSONObject jObject = new JSONObject(result);
            JSONObject s = jObject.getJSONObject("site");
            JSONArray arr = s.getJSONArray("newsitems");
            out = new String[arr.length()];
            for (int i=0; i<arr.length(); i++) {
                out[i] = arr.getJSONObject(i).getString("description");
            }
            Log.v("abba", "parsed json:" + out);
            return out;
        } catch (Exception squish) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String[] items) {
        Log.v("abba", "onPostExecute:" + items);

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
