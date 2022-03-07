package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.adapter.ListArticleAdapter;
import com.example.myapplication.model.Article;
import com.example.myapplication.model.ListArticleResponse;
import com.example.myapplication.service.RestServiceAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText motCle,dateDebut;
    ListView newsList;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        motCle = findViewById(R.id.text_search);
        dateDebut = findViewById(R.id.date_search);
        newsList=findViewById(R.id.newsList);
        btn = findViewById(R.id.search_button);

        StrictMode.ThreadPolicy strictMode=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(strictMode);

        List<Article> data=new ArrayList<>();
        ListArticleAdapter adapter=new ListArticleAdapter(this,R.layout.list_news,data);
        newsList.setAdapter(adapter);

        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),ArticleActivity.class);
                intent.putExtra("article",data.get(i));
                startActivity(intent);

            }
        });

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        RestServiceAPI serviceAPI=retrofit.create(RestServiceAPI.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "onClick: ");
                data.clear();
                String key=motCle.getText().toString();
                String date=dateDebut.getText().toString();
                Log.i("key", key);
                Log.i("date", date);
                Call<ListArticleResponse> callArticles =serviceAPI.articles(key,date,"3cf8c4eb320946bf9d88200dd85311b3");

                callArticles.enqueue(new Callback<ListArticleResponse>() {
                    @Override
                    public void onResponse(Call<ListArticleResponse> call, Response<ListArticleResponse> response) {
                        ListArticleResponse listArticles=response.body();
                        Log.i("TAG", "onResponse: ");
                        for(Article a:listArticles.getArticles()) {
                            data.add(a);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ListArticleResponse> call, Throwable t) {
                        Log.e("error","Erreur de réseau");
                    }
                });
            }
        });

    }
}