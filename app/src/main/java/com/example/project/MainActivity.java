package com.example.project;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<byggnadsobjekt> objektArrayList = new ArrayList<>();
    private ArrayAdapter<byggnadsobjekt> objektAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        objektArrayList = new ArrayList<>();
        objektAdapter = new ArrayAdapter<>(this, R.layout.layoutlistobjekts,R.id.listtextView , objektArrayList);

        ListView thelistview = findViewById(R.id.my_listview);
        thelistview.setAdapter(objektAdapter);

        thelistview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String name = objektArrayList.get(position).getName();
                Integer size = objektArrayList.get(position).getSize();
                String location = objektArrayList.get(position).getLocation();
                Integer cost = objektArrayList.get(position).getCost();
                String company = objektArrayList.get(position).getCompany();
                String message = "The object " +  name + " has a size of " + size + ", a cost of " + cost + ", is located at"+ location +", and is owned by "+ company;
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }


    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            Log.e("QUERY ANSWER: ", json);
            try {
                objektAdapter.clear();
                JSONArray jsonArray = new JSONArray(json);
                for (int i=0; i < jsonArray.length(); i++) // Goes through i in array based on array length.
                {
                    JSONObject oneObject = jsonArray.getJSONObject(i); //Assigns object to variable based on current object in the array jsonArray.
                    String name = oneObject.getString("name");
                    String location = oneObject.getString("location");
                    Integer size= oneObject.getInt("size");
                    String company = oneObject.getString("company");
                    Integer cost = oneObject.getInt("cost");
                    objektArrayList.add(new byggnadsobjekt(name, size, location, company, cost)); //Adds a new mountain in arraylist with name, location and height (int) values.
                }

                objektAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                Log.e("JSON Stuff: ",e.getMessage());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.button_displayobject) {
            new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a17jespe");
        }
        if (id == R.id.button_about) {
            return true;
        }

        if (id == R.id.button_newobject) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}