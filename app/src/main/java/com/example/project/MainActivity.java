package com.example.project;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

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

//    ArrayList<byggnadsobjekt> objektArrayList = new ArrayList<>();
//    ArrayAdapter<byggnadsobjekt> objektAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        objektArrayList = new ArrayList<>();
//        objektAdapter = new ArrayAdapter<>(this, R.layout.fragment_first_list,R.id.listcontent , objektArrayList);


    }




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
//                    String ID = oneObject.getString("id");
//                    String Login = oneObject.getString("type");
//                    objektArrayList.add(new byggnadsobjekt(name, size, location, company, cost,ID, Login));
//                    Log.e("Json: ", name);
//                }
//
//                objektAdapter.notifyDataSetChanged();
//
//            } catch (JSONException e) {
//                Log.e("JSON Stuff: ",e.getMessage());
//            }
//        }

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
        if (id == R.id.button_displayobject) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, FirstFragment.class, null)
                    .commit();
        }
        if (id == R.id.button_about) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, SecondFragment.class, null)
                    .commit();
        }

        if (id == R.id.get_data) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, FirstFragment.class, null)
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }

}