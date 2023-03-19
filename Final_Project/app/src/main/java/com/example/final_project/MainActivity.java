package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
    }



    public void triggerFetchData(View view) {
        EditText editText = findViewById(R.id.userInput);
        String query = editText.getText().toString();
        fetchData(query);
    }

    private void fetchData(String query) {
        String url = "https://genius-song-lyrics1.p.rapidapi.com/search/?q=" + query;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("Response: ","Response: "+response);
                    dataTransfer(response);
                }, error -> {
                    Log.d("Fetching failed", "Fetching Failed");
                    error.printStackTrace();
        }) {
            @Override
            public Map <String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-RapidAPI-Key", "bf6a64cc15mshf0092dc6b0e9cdap11bd43jsnd01dab862d10");
                headers.put("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com");
                return headers;
            }
        };

        queue.add(stringRequest);
    }

    private void dataTransfer(String response) {
        Intent listOfSongs = new Intent(this, Final_Project2.class);
        listOfSongs.putExtra("LIST",response);
        startActivity(listOfSongs);
    }
}