package com.nss.animelisttest.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nss.animelisttest.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by NIM on 17/2/2560.
 */

public class AnimeAdapterList extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> url_img;
    private final ArrayList<String> anime_title;
    private final ArrayList<String> anime_synopsis;
    private final ArrayList<String> anime_airing;
    public AnimeAdapterList(Activity context, ArrayList<String> url_img,
                            ArrayList<String> anime_title,
                            ArrayList<String> anime_synopsis,
                            ArrayList<String> anime_airing) {
       super(context, R.layout.anime_list,anime_title);
        this.context = context;
        this.url_img = url_img;
        this.anime_title = anime_title;
        this.anime_synopsis = anime_synopsis;
        this.anime_airing = anime_airing;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.anime_list, null, true);
        ImageView img_anime = (ImageView)rowView.findViewById(R.id.img_anime);
        TextView txt_title = (TextView)rowView.findViewById(R.id.txt_title);
        TextView txt_synopsis = (TextView)rowView.findViewById(R.id.txt_synopsis);
        TextView txt_airing = (TextView)rowView.findViewById(R.id.txt_airing);

        Picasso.get().load(url_img.get(position)).into(img_anime);
        txt_title.setText(anime_title.get(position));
        txt_synopsis.setText(anime_synopsis.get(position));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String[] date_sp = anime_airing.get(position).split("-");
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
        String formatedDate = sdf.format(new Date(yaer_airing-1900, m_airing, d_airing));
        txt_airing.setText("Date airing : "+formatedDate+" "+airing_time);

        return rowView;
    }
}
