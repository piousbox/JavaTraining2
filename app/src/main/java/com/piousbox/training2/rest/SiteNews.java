package com.piousbox.training2.rest;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.piousbox.training2.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

public class SiteNews extends AsyncTask<Void, Void, String[]> {

    private WeakReference<Activity> myActivity;

    public SiteNews(Activity a) {
        this.myActivity = new WeakReference<Activity>(a);
    }

    @Override
    protected String[] doInBackground(Void... params) {
        /**
         * get json
         */
        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpGet get = new HttpGet("http://manager.piousbox.com/api/sites/view/travel-guide.mobi.json");
        get.setHeader("Content-type", "application/json");
        InputStream inputStream = null;
        String result = null;

        try {
            HttpResponse response = httpclient.execute(get);
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
            Log.v("abba", "exception in sitenews:" + e);
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
                out[i] = arr.getJSONObject(i).getString("name");
            }
            Log.v("abba", "parsed json:" + out.toString());
            String[] tempStr = new String[2];
            tempStr[0] = "a";
            tempStr[1] = "b";
            Log.v("abba", "this is simple" + tempStr);
            return out;
        } catch (Exception squish) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String[] items) {
        Log.v("abba", "onPostExecute:" + items);

        Activity a = myActivity.get();
        Log.v("abba", "activity:" + a);

        if (a != null) {
            final ListView listView = (ListView) a.findViewById(R.id.listview);
            final ArrayAdapter adapter = new ArrayAdapter(a, android.R.layout.simple_list_item_1, items);
            listView.setAdapter(adapter);
        }
    }

    /**
     * trash
     */
    /* private String title;
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
    } */

}
