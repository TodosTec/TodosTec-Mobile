package com.example.todostectest.CadastroUsuario;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.todostectest.R;

public class ImageAdapter extends BaseAdapter {
    private String[] imageUrls = {
            "https://cdn.icon-icons.com/icons2/2643/PNG/512/male_man_boy_black_tone_avatar_people_person_icon_159369.png",
            "https://cdn.icon-icons.com/icons2/2643/PNG/512/female_woman_people_person_avatar_black_tone_icon_159364.png",
            "https://cdn.icon-icons.com/icons2/2643/PNG/512/avatar_female_woman_person_people_white_tone_icon_159360.png",
            "https://cdn.icon-icons.com/icons2/2643/PNG/512/female_woman_person_people_avatar_icon_159367.png",
            "https://cdn.icon-icons.com/icons2/2643/PNG/512/male_man_people_person_avatar_white_tone_icon_159363.png",
            "https://cdn.icon-icons.com/icons2/2643/PNG/512/male_man_boy_person_avatar_people_white_tone_icon_159357.png"
    };
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public Object getItem(int position) {
        return imageUrls[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(230, 230)); // Defina o tamanho desejado das imagens
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        Glide.with(context)
                .load(imageUrls[position])
                .into(imageView);

        return imageView;
    }

    public String getImageUrl(int position) {
        return imageUrls[position];
    }
}
