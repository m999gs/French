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

public class PhrasesActivity extends AppCompatActivity {
    MediaPlayer media;
    private static final String TAG="StateMessages";
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
        num.add(new word("I am doing business", "Je fais des affaires",R.raw.i_am_doing_business));
        num.add(new word("I am looking for a job", "je cherche un emploi",R.raw.i_am_looking_for_a_job));
        num.add(new word("I am a housewife", "Je suis une femme au foyer",R.raw.i_am_a_housewife));
        num.add(new word("He is my father", "C'est mon père",R.raw.he_is_my_father));
        num.add(new word("She is my mother", "Elle est ma mère",R.raw.she_is_my_mother));
        num.add(new word("He is my classmate", "Il est mon camarade de classe",R.raw.he_is_my_classmate));
        num.add(new word("He is my colleague", "Il est mon collègue",R.raw.he_is_my_colleague));
        num.add(new word("Are you coming?", "Viens-tu?",R.raw.are_you_coming));
        num.add(new word("Yes,I'm coming", "Oui j'arrive",R.raw.yes_im_coming));
        num.add(new word("i am from india", "Je viens d'Inde",R.raw.i_am_from_india));
        num.add(new word("where are you from?", "D'où êtes-vous?",R.raw.where_are_you_from));
        num.add(new word("i am from france", "je viens de France",R.raw.i_am_from_france));
        num.add(new word("Let's go.", "Allons-y.",R.raw.let_go));
        num.add(new word("Come here.", "Venez ici",R.raw.come_here));
        num.add(new word("Where are you going?", "Où allez-vous?",R.raw.where_are_you_going));
        num.add(new word("what is your name?", "comment vous appelez-vous?",R.raw.what_is_your_name));
        num.add(new word("My name is ...", "Mon nom est ...",R.raw.my_name_is));
        num.add(new word("How are you feeling?", "Comment allez-vous?",R.raw.how_are_you_feeling));
        num.add(new word("I'm feeling good.", "Je me sens bien.",R.raw.im_feeling_good));
        num.add(new word("I am fine and you?", "Je vais bien et vous?",R.raw.i_am_fine_and_you));
        num.add(new word("How is your brother?", "Comment va votre frère?",R.raw.how_is_your_brother));
        num.add(new word("May I know your name?", "Puis-je connaître ton nom?",R.raw.may_i_know_your_name));
        num.add(new word("I am working in Google", "Je travaille sur Google",R.raw.i_am_working_in_google));
        num.add(new word("He is working as a software engineer in WIPRO", "Il travaille comme ingénieur logiciel dans WIPRO",R.raw.he_is_working_as_a_software_engineer_in));
        num.add(new word("What is your educational qualification?", "Quelle est votre qualification scolaire?",R.raw.what_is_your_educational_qualification));
        num.add(new word("I am a graduate in Engineering", "Je suis diplômé en ingénierie",R.raw.i_am_a_graduate_in_engineering));
        num.add(new word("There are seven members in our family", "Il y a sept membres dans notre famille",R.raw.there_are_seven_members_in_our_family));
        num.add(new word("I feel hungry", "j'ai faim",R.raw.i_feel_hungry));
        num.add(new word("I feel sleepy", "Je suis fatigué",R.raw.i_feel_sleepy));
        num.add(new word("Don't disturb me", "Ne me dérange pas",R.raw.dont_disturb_me));
        num.add(new word("Don't eat too much", "Ne mangez pas trop",R.raw.dont_eat_too_much));
        num.add(new word("Don't confuse me", "Ne me confondez pas",R.raw.dont_confuse_me));
        num.add(new word("Thank you", "Je vous remercie",R.raw.thank_you));
        num.add(new word("I will never forget your timely help", "Je n'oublierai jamais votre aide en temps opportun",R.raw.i_will_never_forget));
        num.add(new word("Thanks for calling", "Merci d'avoir appeler",R.raw.thanks_for_calling));
        num.add(new word("No problem", "Pas de problème",R.raw.no_problem));
        num.add(new word("You are welcome", "Je vous en prie",R.raw.you_are_welcome));
        num.add(new word("I'm going to class now.", "Je vais en classe maintenant.",R.raw.im_going_to_class_now));


        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);



        WordAdapter adapter = new WordAdapter(this, num, R.color.phrases);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                word ob=num.get(position);
                                                releaseMediaPlayer();
                                                int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                                                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                                                    media=MediaPlayer.create(PhrasesActivity.this,ob.getAudioResourse());
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
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "main onPause");
        System.gc();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
        System.gc();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        System.gc();
    }
}
