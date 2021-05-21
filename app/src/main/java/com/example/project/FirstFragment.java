package com.example.project;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.project.dummy.DummyContent;

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

/**
 * A fragment representing a list of Items.
 */

public class FirstFragment extends Fragment {

    private Integer mColumnCount;
    private ArrayList<byggnadsobjekt> objektArrayList = new ArrayList<>();
    private ArrayAdapter<byggnadsobjekt> objektAdapter;

    private static final String ARG_COLUMN_COUNT = "column-count";

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
                mColumnCount = jsonArray.length();
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

    // TODO: Customize parameter argument names

    // TODO: Customize parameters
    // private int mColumnCount=5;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FirstFragment newInstance(int columnCount) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_list, container, false);

//        view.findViewById(R.id.button_newobject).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(fragment_first.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS));
        }
        return view;
    }
}