package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.model.Article;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ListArticleAdapter extends ArrayAdapter<Article> {
    private int resource;
    public ListArticleAdapter(@NonNull Context context, int resource, @NonNull List<Article> articles) {
        super(context, resource, articles);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(resource,parent,false);
        }
        TextView title=listItemView.findViewById(R.id.title);
        TextView description=listItemView.findViewById(R.id.description);
        TextView publishedAt=listItemView.findViewById(R.id.publishedAt);
        ImageView image=listItemView.findViewById(R.id.image_article);
        title.setText(String.valueOf(getItem(position).getTitle()));
        description.setText(getItem(position).getDescription());
        publishedAt.setText(getItem(position).getPublishedAt());
        URL url= null;
        try {
            url = new URL(getItem(position).getUrlToImage());
            Bitmap bitmap= BitmapFactory.decodeStream(url.openStream());
            image.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return listItemView;
    }
}
