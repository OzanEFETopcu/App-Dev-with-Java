 package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

 public class LyricsDisplayActivity extends AppCompatActivity {

     private RequestQueue queue;
     private String lyrics_extracted = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_display);
        Intent intent = getIntent();
        int songID = intent.getIntExtra("SONGID", 0);
        Log.d("SONGID", String.valueOf(songID));

        queue = Volley.newRequestQueue(this);

        fetchLyrics(songID);
    }

    private void fetchLyrics(int songID) {
        String url = "https://genius-song-lyrics1.p.rapidapi.com/song/lyrics/?id=" + songID + "&text_format=plain";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            response -> {
                Log.d("Response: ","Response: "+response);
                parseLyrics(response);
            }, error -> {
                Log.d("Fetching failed", "Fetching Failed");
                error.printStackTrace();
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-RapidAPI-Key", "bf6a64cc15mshf0092dc6b0e9cdap11bd43jsnd01dab862d10");
                headers.put("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com");
                return headers;
            }
        };

        queue.add(stringRequest);
    }

     private void parseLyrics(String response) {
         TextView lyrics = findViewById(R.id.lyrics);
         try {
             JSONObject lyrics_response = new JSONObject(response);
             lyrics_extracted = lyrics_response.getJSONObject("lyrics").getJSONObject("lyrics").getJSONObject("body").getString("plain");
             lyrics.setText(""+lyrics_extracted+"");
         } catch (JSONException e) {
             e.printStackTrace();
         }
     }
 }