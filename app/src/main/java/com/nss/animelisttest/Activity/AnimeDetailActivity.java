package com.nss.animelisttest.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nss.animelisttest.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AnimeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_detail);
        getSupportActionBar().hide();


        ImageView img_detail_anime = (ImageView)findViewById(R.id.img_detail_anime);
        TextView txt_anime_detail_title = (TextView)findViewById(R.id.txt_anime_detail_title);
        TextView txt_anime_detail_genres = (TextView)findViewById(R.id.txt_anime_detail_genres);
        TextView txt_anime_detail_synopsis = (TextView)findViewById(R.id.txt_anime_detail_synopsis);
        TextView txt_anime_detail_episodes = (TextView)findViewById(R.id.txt_anime_detail_episodes);
        TextView txt_anime_detail_airing = (TextView)findViewById(R.id.txt_anime_detail_airing);


        String getAnime_title = getIntent().getStringExtra("Anime_title");
        String getAnime_url = getIntent().getStringExtra("Anime_url");
        String getAnime_synopsis = getIntent().getStringExtra("Anime_synopsis");
        String getAnime_airing = getIntent().getStringExtra("Anime_airing");
        String getAnime_episodes = getIntent().getStringExtra("Anime_episodes");
        String getAnime_genres = getIntent().getStringExtra("Anime_genres");

        String[] date_sp = getAnime_airing.split("-");
        int yaer_airing = Integer.parseInt(date_sp[0]);
        int m_airing = Integer.parseInt(date_sp[1]);
        int d_airing = Integer.parseInt(date_sp[2].substring(0,2));
        String [] time_sp = date_sp[2].split("T");
        String [] timezone_sp = time_sp[1].split(":");
        String time_h = timezone_sp[0];
        String time_m = timezone_sp[1];
        String time_s = timezone_sp[2];
        String time_GMT = "(JST)";
        String airing_time = time_h+":"+time_m+time_GMT;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = sdf.format(new Date(yaer_airing-1900, m_airing, d_airing));
        txt_anime_detail_airing.setText("Date airing : "+formatedDate+" "+airing_time);

        Picasso.get().load(getAnime_url).into(img_detail_anime);

        txt_anime_detail_title.setText(getAnime_title);
        txt_anime_detail_genres.setText("Genres : "+getAnime_genres);
        txt_anime_detail_synopsis.setText(getAnime_synopsis);
        txt_anime_detail_episodes.setText("Episodes : "+getAnime_episodes);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
