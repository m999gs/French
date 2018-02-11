package com.example.mohitgarg.french;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
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

        num.add(new word("father","père",R.drawable.family_father, R.raw.father));
        num.add(new word("mother","mère",R.drawable.family_mother,R.raw.mother));
        num.add(new word("uncle","oncle",R.drawable.family_father, R.raw.oncle));
        num.add(new word("aunty","aunty",R.drawable.family_mother,R.raw.aunty));
        num.add(new word("son","fils",R.drawable.family_son, R.raw.son));
        num.add(new word("cousin","cousin",R.drawable.family_son, R.raw.cousin));
        num.add(new word("daughter","fille",R.drawable.family_daughter, R.raw.daughter));
        num.add(new word("elder brother","frère aîné",R.drawable.family_older_brother, R.raw.elder_brother));
        num.add(new word("younger brother","frère cadet",R.drawable.family_younger_brother,R.raw.younger_brother));
        num.add(new word("elder sister","sœur ainée",R.drawable.family_older_sister, R.raw.elder_sister));
        num.add(new word("younger sister","sœur cadette",R.drawable.family_younger_sister, R.raw.younger_sister));
        num.add(new word("grandmother","grand-mère",R.drawable.family_grandmother, R.raw.grandmother));
        num.add(new word("grandfather","grand-père",R.drawable.family_grandfather, R.raw.grandfather));
        num.add(new word("teacher","prof",R.drawable.family_father, R.raw.teacher));
        num.add(new word("student","étudiant",R.drawable.family_younger_brother, R.raw.student));
        num.add(new word("friend","ami",R.drawable.family_older_brother, R.raw.friend));
        num.add(new word("best friend","meilleur ami",R.drawable.family_older_brother, R.raw.best_friend));




        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        WordAdapter adapter=new WordAdapter(this,num,R.color.family);
        ListView listview=(ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                word ob=num.get(position);
                                                releaseMediaPlayer();
                                                int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                                                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                                                    media=MediaPlayer.create(FamilyActivity.this,ob.getAudioResourse());
                                                    media.start();
                                                    media.setOnCompletionListener(completionListener);
                                                }

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
    }
}