package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Final_Project2 extends AppCompatActivity {

    private int[] songID = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_project2);

        Intent intent = getIntent();
        String response = intent.getStringExtra("LIST");
        parseJSONAndUpdateUI(response);
    }

    private void parseJSONAndUpdateUI(String response) {
        TextView option1 = findViewById(R.id.option1);
        TextView option2 = findViewById(R.id.option2);
        TextView option3 = findViewById(R.id.option3);
        TextView option4 = findViewById(R.id.option4);
        TextView option5 = findViewById(R.id.option5);
        TextView option6 = findViewById(R.id.option6);
        TextView option7 = findViewById(R.id.option7);
        TextView option8 = findViewById(R.id.option8);
        TextView option9 = findViewById(R.id.option9);
        TextView option10 = findViewById(R.id.option10);
        try {
            JSONObject songResponse = new JSONObject(response);
            JSONArray hits = songResponse.getJSONArray("hits");

            for(int k = 0; k <= 9; k++){
                songID[k] = hits.getJSONObject(k).getJSONObject("result").getInt("id");
            }

            option1.setText(""+hits.getJSONObject(0).getJSONObject("result").getString("full_title")+"");
            option2.setText(""+hits.getJSONObject(1).getJSONObject("result").getString("full_title")+"");
            option3.setText(""+hits.getJSONObject(2).getJSONObject("result").getString("full_title")+"");
            option4.setText(""+hits.getJSONObject(3).getJSONObject("result").getString("full_title")+"");
            option5.setText(""+hits.getJSONObject(4).getJSONObject("result").getString("full_title")+"");
            option6.setText(""+hits.getJSONObject(5).getJSONObject("result").getString("full_title")+"");
            option7.setText(""+hits.getJSONObject(6).getJSONObject("result").getString("full_title")+"");
            option8.setText(""+hits.getJSONObject(7).getJSONObject("result").getString("full_title")+"");
            option9.setText(""+hits.getJSONObject(8).getJSONObject("result").getString("full_title")+"");
            option10.setText(""+hits.getJSONObject(9).getJSONObject("result").getString("full_title")+"");

        } catch (JSONException e) {
            Log.d("ERROR!!","YOU ARE FACING A JSON PARSING ERROR!!");
            e.printStackTrace();
        }
    }

    public void openPreviousActivity(View view) {
        Intent searchActivity = new Intent(this, MainActivity.class);
        startActivity(searchActivity);
    }

    public void openLyricsPage(View view) {
        Intent lyricsActivity = new Intent(this, LyricsDisplayActivity.class);
        Object tag = view.getTag();
        String tagAsString = tag.toString();
        int tagAsInt = Integer.parseInt(tagAsString);
        lyricsActivity.putExtra("SONGID", songID[tagAsInt]);
        startActivity(lyricsActivity);
    }
}