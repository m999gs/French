package com.example.mohitgarg.french;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

class WordAdapter extends ArrayAdapter<word> {
    private int BackColor;
    WordAdapter(Activity numbersActivity, ArrayList<word> num, int co) {
        super(numbersActivity,0,num);
        BackColor=co;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        word obj=getItem(position);
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView text1=(TextView) listItemView.findViewById(R.id.text1);
        assert obj != null;
        text1.setText(obj.getFrenchTranslation());

        TextView text2=(TextView) listItemView.findViewById(R.id.text2);
        text2.setText(obj.getEnglishWords());

        ImageView img=(ImageView) listItemView.findViewById(R.id.image);
        if((obj.getimage())==0){
            img.setVisibility(View.GONE);
        }
        else {
            img.setImageResource(obj.getimage());
            img.setVisibility(View.VISIBLE);
        }

        View textContainer=listItemView.findViewById(R.id.linearcolor);
        View textContainer2=listItemView.findViewById(R.id.playbutton);
        int color= ContextCompat.getColor(getContext(),BackColor);
        textContainer.setBackgroundColor(color);
        textContainer2.setBackgroundColor(color);
        System.gc();
        return listItemView;
    }

}