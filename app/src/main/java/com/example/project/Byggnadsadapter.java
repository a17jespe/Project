package com.example.project;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.jar.Attributes;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Byggnadsadapter extends RecyclerView.Adapter<>.ViewHolder{

private List<byggnadsobjekt> Items;
private LayoutInflater layoutInflater;
private OnClickListener onClickListener;

Byggnadsadapter(Context context, List<byggnadsobjekt> items, OnClickListener onClickListener){
    this.layoutInflater = LayoutInflater.from(context);
    this.Items = items;
    this.onClickListener = onClickListener;
}

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.fragment_first, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(Items.get(position).getName());
    }

    public int getItemCount() {
        return Items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(Items.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(byggnadsobjekt item);
    }
}
