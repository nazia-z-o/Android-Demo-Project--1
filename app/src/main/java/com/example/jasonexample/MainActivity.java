package com.example.jasonexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Object> viewItems = new ArrayList<>();

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(this, viewItems);
        mRecyclerView.setAdapter(mAdapter);

        addItemsFromJSON();


    }

    private void addItemsFromJSON() {
        try {

            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);
//                int user= (int)itemObj.get("userId");
//                int idd= (int)itemObj.get("id");
                String user = itemObj.getString("userId");
                String id = itemObj.getString("id");
                String name = itemObj.getString("title");
                String date = itemObj.getString("body");

                Holidays holidays = new Holidays(user,id,name, date);
                viewItems.add(holidays);
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException{

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.holidays);
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

    public void post(View view) {

        final EditText lEditText = new EditText(this);
        final EditText lEditText1 = new EditText(this);
        final EditText lEditText2 = new EditText(this);
        final EditText lEditText3 = new EditText(this);
        final Button saveB = new Button(this);
        final LinearLayout mLinerLayout = findViewById(R.id.layOUT);
        saveB.setText("Save");
        lEditText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        lEditText.setText ("");
        lEditText.setHint("USER ID");
        lEditText1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        lEditText1.setText ("");
        lEditText1.setHint("ID");
        lEditText2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        lEditText2.setText ("");
        lEditText2.setHint("Title");
        lEditText3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        lEditText3.setText ("");
        lEditText3.setHint("Post");
        mLinerLayout.addView(lEditText);
        mLinerLayout.addView(lEditText1);
        mLinerLayout.addView(lEditText2);
        mLinerLayout.addView(lEditText3);
        mLinerLayout.addView(saveB);
        saveB.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         String user=  lEditText.getText().toString();
                                        String id = lEditText1.getText().toString();
                                        String name= lEditText2.getText().toString();
                                        String date= lEditText3.getText().toString();
                                         // ChangeJSON(newPost,holidays.getId());
                                         Holidays holidays = new Holidays(user,id,name, date);
                                         viewItems.add(holidays);
                                         mLinerLayout.removeView(lEditText);
                                         mLinerLayout.removeView(lEditText1);
                                         mLinerLayout.removeView(lEditText2);
                                         mLinerLayout.removeView(lEditText3);
                                         mLinerLayout.removeView(saveB);
                                     }
                                 }    );

}
}