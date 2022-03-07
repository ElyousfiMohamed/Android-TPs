package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

import com.example.myapplication.model.*;

import org.w3c.dom.Text;


public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detail);

        Intent intent=getIntent();
        Article article=(Article) intent.getSerializableExtra("article");
        TextView title=findViewById(R.id.title);
        TextView description=findViewById(R.id.description);
        TextView publishedAt=findViewById(R.id.publishedAt);
        TextView source = findViewById(R.id.source);
        TextView content=findViewById(R.id.content);
        ImageView image=findViewById(R.id.image_article);

        title.setText(article.getTitle());
        description.setText(article.getDescription());
        publishedAt.setText("PublishedAt : "+article.getPublishedAt());
        content.setText("Content : "+article.getContent());
        source.setText("Source : "+article.getSource().getName());
        URL url= null;
        try {
            url = new URL(article.getUrlToImage());
            Bitmap bitmap= BitmapFactory.decodeStream(url.openStream());
            image.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
