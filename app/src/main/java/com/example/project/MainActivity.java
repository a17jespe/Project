package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        objektAdapter = new ArrayAdapter<>(this, R.layout.textformating,R.id.textformat, objektArrayList);


        ListView thelist = findViewById(R.id.list);
        thelist.setAdapter(objektAdapter);

        thelist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String name = objektArrayList.get(position).getName();
                String size = objektArrayList.get(position).getSize();
                String location = objektArrayList.get(position).getLocation();
                String login = objektArrayList.get(position).getLogin();
                String ID = objektArrayList.get(position).getID();
                String cost = objektArrayList.get(position).getCost();
                String company = objektArrayList.get(position).getCompany();
                String message = "The object " +  name +", "+ID+ ", is located at " + location + ", has a size of " + size + ", costs "+ cost +", is owned by the company "+ company+" and were created by " + login;
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

//        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a17jespe");
                    for (int i = 0;i < 10; i++) {
                    String name = "name"+i;
                    String location = "location"+i;
                    Integer size = i;
                    String company = "company"+i;
                    Integer cost = i;
                    String id = "ID"+i;
                    String login = "type"+i;
                    Log.e("Stuff: ", name);
                    objektArrayList.add(new byggnadsobjekt(name, size, location, company, cost, id, login));
                    }
                    objektAdapter.notifyDataSetChanged();

    }



//    @SuppressLint("StaticFieldLeak")
//    private class JsonTask extends AsyncTask<String, String, String> {
//
//        private HttpURLConnection connection = null;
//        private BufferedReader reader = null;
//
//        protected String doInBackground(String... params) {
//            try {
//                URL url = new URL(params[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//
//                InputStream stream = connection.getInputStream();
//                reader = new BufferedReader(new InputStreamReader(stream));
//
//                StringBuilder builder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null && !isCancelled()) {
//                    builder.append(line).append("\n");
//                }
//                return builder.toString();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (connection != null) {
//                    connection.disconnect();
//                }
//                try {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//
//        }
//
//        @Override
//        protected void onPostExecute(String json) {
//            Log.e("QUERY ANSWER: ", json);
//            try {
//                objektAdapter.clear();
//                JSONArray jsonArray = new JSONArray(json);
//                for (int i=0; i < jsonArray.length(); i++) // Goes through i in array based on array length.
//                {
//                    JSONObject oneObject = jsonArray.getJSONObject(i); //Assigns object to variable based on current object in the array jsonArray.
//                    String name = oneObject.getString("name");
//                    String location = oneObject.getString("location");
//                    Integer size= oneObject.getInt("size");
//                    String company = oneObject.getString("company");
//                    Integer cost = oneObject.getInt("cost");
//                    String id = oneObject.getString("ID");
//                    String login = oneObject.getString("type");
//                    objektArrayList.add(new byggnadsobjekt(name, size, location, company, cost, id, login));
//                    Log.e("Json: ", name+size+location+company+cost+id+login);
//                }
//
//                objektAdapter.notifyDataSetChanged();
//
//            } catch (JSONException e) {
//                Log.e("JSON Stuff: ",e.getMessage());
//            }

//        }
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.button_about) {
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}