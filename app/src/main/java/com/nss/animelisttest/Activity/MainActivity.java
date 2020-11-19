package com.nss.animelisttest.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.nss.animelisttest.Adapter.AnimeAdapterList;
import com.nss.animelisttest.R;
import com.nss.animelisttest.service.api.Service;
import com.nss.animelisttest.service.base.BaseTask;
import com.nss.animelisttest.service.http.URLConnecting;
import com.nss.animelisttest.service.model.AnimeListWinterModel;
import com.nss.animelisttest.service.model.ResponseService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list_anime;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_anime = (ListView)findViewById(R.id.list_anime);
        TaskgetResponseAnimeList taskgetResponseAnimeList = new TaskgetResponseAnimeList(context);
        taskgetResponseAnimeList.execute();
    }

    public static void MyAlertDialog(String msg_error,Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(msg_error);
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private class TaskgetResponseAnimeList extends BaseTask<ResponseService>{

        public TaskgetResponseAnimeList(Context context){
            super(context);
        }

        @Override
        public ResponseService prepareCallAPI() throws Exception {
            Service service = new Service(URLConnecting.url);
            ResponseService responseService = service.ResponseAnimeList();
            return responseService;
        }

        @Override
        public void onResultSuccess(ResponseService result) {
            if (result.isError()){
                //MyAlertDialog(result.getErrorCode()+" "+result.getMessageResponse(),context);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(false);
                builder.setMessage(result.getErrorCode()+" "+result.getMessageResponse());
                builder.setPositiveButton("Retry",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TaskgetResponseAnimeList taskgetResponseAnimeList = new TaskgetResponseAnimeList(context);
                                taskgetResponseAnimeList.execute();
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }else {
                int anime_size = result.getGetJsonAnime().getAnime().size();
                final ArrayList<String> url_img = new ArrayList<>();
                final ArrayList<String> anime_title = new ArrayList<>();
                final ArrayList<String> anime_synopsis = new ArrayList<>();
                final ArrayList<String> anime_airing = new ArrayList<>();
                final ArrayList<String> anime_episodes = new ArrayList<>();
                final ArrayList<String> anime_genres = new ArrayList<>();

                for (AnimeListWinterModel.AnimeBean in_list_data : result.getGetJsonAnime().getAnime()){

                    url_img.add(in_list_data.getImage_url());
                    anime_title.add(in_list_data.getTitle());
                    anime_synopsis.add(in_list_data.getSynopsis());
                    anime_airing.add(in_list_data.getAiring_start());
                    anime_episodes.add(String.valueOf(in_list_data.getEpisodes()));
                    String genres_type = "";
                    int size_genres = in_list_data.getGenres().size();
                    int count_genres = 1;
                    for (AnimeListWinterModel.AnimeBean.GenresBean genresBean : in_list_data.getGenres()){
                        if (count_genres!=size_genres){
                            genres_type += genresBean.getName()+",";
                        }else {
                            genres_type += genresBean.getName();
                        }
                        count_genres ++;
                    }
                    anime_genres.add(genres_type);
                }

                AnimeAdapterList animeAdapterList = new AnimeAdapterList(MainActivity.this,url_img,anime_title,anime_synopsis,anime_airing);
                list_anime.setAdapter(animeAdapterList);
                list_anime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent goDetail = new Intent(MainActivity.this,AnimeDetailActivity.class);
                        goDetail.putExtra("Anime_title",anime_title.get(position));
                        goDetail.putExtra("Anime_url",url_img.get(position));
                        goDetail.putExtra("Anime_synopsis",anime_synopsis.get(position));
                        goDetail.putExtra("Anime_airing",anime_airing.get(position));
                        goDetail.putExtra("Anime_episodes",anime_episodes.get(position));
                        goDetail.putExtra("Anime_genres",anime_genres.get(position));
                        goDetail.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(goDetail);
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.first_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_ic_refresh) {
            TaskgetResponseAnimeList taskgetResponseAnimeList = new TaskgetResponseAnimeList(context);
            taskgetResponseAnimeList.execute();
        }
        return super.onOptionsItemSelected(item);

    }
}
