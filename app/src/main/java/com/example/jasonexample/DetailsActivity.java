package com.example.jasonexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Object> viewItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "DetailsActivity";
    private SharedPreferences mPreferences;
    private String mSharedPrefFile = "com.example.jasonexample";

    String passId;
    private final String TEXT = "texxt", Text2 = "t2", Text3 = "t3", Text4 = "t4";

    String text1 , text2 , text3 , text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Log.v(TAG,"doing Preference");
        mPreferences = getSharedPreferences(
                mSharedPrefFile, MODE_PRIVATE);
        // Restore preferences
        text1=mPreferences.getString(TEXT,"");

        text2=mPreferences.getString(Text2,"");
        text3=mPreferences.getString(Text3,"");
        text4=mPreferences.getString(Text4,"");
        EditText lEditText = findViewById(R.id.e1);
        EditText lEditText1 = findViewById(R.id.e2);
        EditText lEditText2 = findViewById(R.id.e3);
        EditText lEditText3 = findViewById(R.id.e4);
        lEditText.setText(text1);
        lEditText.setSelection(text1.length());
        lEditText1.setText(text2);
        lEditText1.setSelection(text2.length());
        lEditText2.setText(text3);
        lEditText2.setSelection(text3.length());
        lEditText3.setText(text4);
        lEditText3.setSelection(text4.length());

        Intent intent = getIntent();
        passId = intent.getStringExtra("key");
        Log.v("DetailsActivity", "passID" + passId);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view2);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter2(this, viewItems);
        mRecyclerView.setAdapter(mAdapter);
        addItemsFromJSON();

    }
    @Override
    protected void onPause(){
        super.onPause();
        EditText lEditText = findViewById(R.id.e1);
        EditText lEditText1 = findViewById(R.id.e2);
        EditText lEditText2 = findViewById(R.id.e3);
        EditText lEditText3 = findViewById(R.id.e4);
        text1=lEditText.getText().toString();
        text2=lEditText1.getText().toString();
        text3=lEditText2.getText().toString();
        text4=lEditText3.getText().toString();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(TEXT,text1);
        preferencesEditor.putString(Text2,text2);
        preferencesEditor.putString(Text3,text3);
        preferencesEditor.putString(Text4,text4);
        preferencesEditor.apply();
    }
    private void addItemsFromJSON() {
        try {

            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String post = itemObj.getString("postId");
                String id = itemObj.getString("id");
                String name = itemObj.getString("name");
                String email = itemObj.getString("email");
                String date = itemObj.getString("body");
                Log.v("addComments", "PostID" + post);
                if (post.equals(passId)) {
                    Comments holidays = new Comments(post, id, name, email, date);
                    viewItems.add(holidays);
                }

            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.comments);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }

    public void postV(View view) {
        EditText lEditText = findViewById(R.id.e1);
        EditText lEditText1 = findViewById(R.id.e2);
        EditText lEditText2 = findViewById(R.id.e3);
        EditText lEditText3 = findViewById(R.id.e4);
        String uI = lEditText.getText().toString();
        String I = lEditText1.getText().toString();
        String T = lEditText2.getText().toString();
        String newPost = lEditText3.getText().toString();
        Comments holiday2 = new Comments(passId, uI, I, T, newPost);
        viewItems.add(holiday2);
        lEditText.setText("");

        lEditText1.setText("");

        lEditText2.setText("");

        lEditText3.setText("");

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }
}



