package com.example.mohitgarg.french;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private static final String TAG="buckyMessages";
    public MediaPlayer media;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener afChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                media.pause();
                media.seekTo(0);
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                media.start();

            }else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener completionListener=new MediaPlayer.OnCompletionListener(){
        public void onCompletion(MediaPlayer mp){
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format);
        final ArrayList<word> num = new ArrayList<word>();
        num.add(new word("red","rouge",R.drawable.color_red, R.raw.red));
        num.add(new word("mustard yellow","Jaune moutarde",R.drawable.color_mustard_yellow, R.raw.mustard_yellow));
        num.add(new word("dusty yellow","Jaune poussiéreux",R.drawable.color_dusty_yellow, R.raw.dusty_yellow));
        num.add(new word("green","vert",R.drawable.color_green, R.raw.green));
        num.add(new word("brown","marron",R.drawable.color_brown, R.raw.brown));
        num.add(new word("gray","gris",R.drawable.color_gray, R.raw.gray));
        num.add(new word("black","noir",R.drawable.color_black, R.raw.black));
        num.add(new word("white","blanc",R.drawable.color_white, R.raw.white));

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        WordAdapter adapter=new WordAdapter(this,num,R.color.color);
        ListView listview=(ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                word ob=num.get(position);
                                                releaseMediaPlayer();
                                                int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                                                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                                                    media=MediaPlayer.create(ColorsActivity.this,ob.getAudioResourse());
                                                    media.start();
                                                    media.setOnCompletionListener(completionListener);
                                                }
                                                System.gc();
                                            }
                                        }
        );

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                word ob = num.get(position);
                ClipboardManager cm = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(ob.getFrenchTranslation());
                Toast.makeText(getApplicationContext(), "French text Copied", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
    public void releaseMediaPlayer(){
        if(media!=null){
            media.release();
            media=null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
        System.gc();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "main onPause");
        System.gc();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        System.gc();
    }
}
