package com.example.mohitgarg.french;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.text.ClipboardManager;
import android.widget.Toast;


import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer media;
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

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format);
        final ArrayList<word> num = new ArrayList<word>();
        num.add(new word("one", "Un", R.drawable.number_one, R.raw.one));
        num.add(new word("two", "deux", R.drawable.number_two, R.raw.two));
        num.add(new word("three", "trois", R.drawable.number_three, R.raw.three));
        num.add(new word("four", "quatre", R.drawable.number_four, R.raw.four));
        num.add(new word("five", "cinq", R.drawable.number_five, R.raw.five));
        num.add(new word("six", "six", R.drawable.number_six, R.raw.six));
        num.add(new word("seven", "sept", R.drawable.number_seven, R.raw.seven));
        num.add(new word("eight", "huit", R.drawable.number_eight, R.raw.eight));
        num.add(new word("nine", "neuf", R.drawable.number_nine, R.raw.nine));
        num.add(new word("ten", "Dix", R.drawable.number_ten, R.raw.ten));
        num.add(new word("eleven", "onze", R.drawable.number_one, R.raw.eleven));
        num.add(new word("twelve", "douze", R.drawable.number_two, R.raw.twelve));
        num.add(new word("thirteen", "treize", R.drawable.number_three, R.raw.thirteen));
        num.add(new word("fourteen", "quatorze", R.drawable.number_four, R.raw.fourteen));
        num.add(new word("fifteen", "quinze", R.drawable.number_five, R.raw.fifteen));
        num.add(new word("sixteen", "seize", R.drawable.number_six, R.raw.sixteen));
        num.add(new word("seventeen", "dix-sept", R.drawable.number_seven, R.raw.seventeen));
        num.add(new word("eighteen", "dix-huit", R.drawable.number_eight, R.raw.eighteen));
        num.add(new word("nineteen", "dix-neuf", R.drawable.number_nine, R.raw.nineteen));
        num.add(new word("twenty", "vingt", R.drawable.number_ten, R.raw.twenty));
        num.add(new word("twenty one", "vingtet Un", R.drawable.number_one, R.raw.twenty_one));
        num.add(new word("twenty two", "vingt-deux", R.drawable.number_two, R.raw.twenty_two));
        num.add(new word("twenty three", "vingt-trois", R.drawable.number_three, R.raw.twenty_three));
        num.add(new word("twenty four", "vingt-quatre", R.drawable.number_four, R.raw.twenty_four));
        num.add(new word("twenty five", "vingt-cinq", R.drawable.number_five, R.raw.twenty_five));
        num.add(new word("twenty six", "vingt-six", R.drawable.number_six, R.raw.twenty_six));
        num.add(new word("twenty seven", "vingt-sept", R.drawable.number_seven, R.raw.twenty_seven));
        num.add(new word("twenty eight", "vingt-huit", R.drawable.number_eight, R.raw.twenty_eight));
        num.add(new word("twenty nine", "vingt-neuf", R.drawable.number_nine, R.raw.twenty_nine));
        num.add(new word("thirty", "trente", R.drawable.number_ten, R.raw.thirty));
        num.add(new word("thirty one", "trenteet un", R.drawable.number_one, R.raw.thirty_one));
        num.add(new word("thirty two", "trente-deux", R.drawable.number_two, R.raw.thirty_two));
        num.add(new word("thirty three", "trente-trois", R.drawable.number_three, R.raw.thirty_three));
        num.add(new word("thirty four", "trente-quatre", R.drawable.number_four, R.raw.thirty_four));
        num.add(new word("thirty five", "trente-cinq", R.drawable.number_five, R.raw.thirty_five));
        num.add(new word("thirty six", "trente-six", R.drawable.number_six, R.raw.thirty_six));
        num.add(new word("thirty seven", "trente-sept", R.drawable.number_seven, R.raw.thirty_seven));
        num.add(new word("thirty eight", "trente-huit", R.drawable.number_eight, R.raw.thirty_eight));
        num.add(new word("thirty nine", "trente-neuf", R.drawable.number_nine, R.raw.thirty_nine));
        num.add(new word("forty", "quarante", R.drawable.number_ten, R.raw.forty));
        num.add(new word("forty one", "quaranteet un", R.drawable.number_one, R.raw.forty_one));
        num.add(new word("forty two", "quarante-deux", R.drawable.number_two, R.raw.forty_two));
        num.add(new word("forty three", "quarante-trois", R.drawable.number_three, R.raw.forty_three));
        num.add(new word("forty four", "quarante-quatre", R.drawable.number_four, R.raw.forty_four));
        num.add(new word("forty five", "quarante-cinq", R.drawable.number_five, R.raw.forty_five));
        num.add(new word("forty six", "quarante-six", R.drawable.number_six, R.raw.forty_six));
        num.add(new word("forty seven", "quarante-sept", R.drawable.number_seven, R.raw.forty_seven));
        num.add(new word("forty eight", "quarante-huit", R.drawable.number_eight, R.raw.forty_eight));
        num.add(new word("forty nine", "quarante-neuf", R.drawable.number_nine, R.raw.forty_nine));
        num.add(new word("fifty", "cinquante", R.drawable.number_ten, R.raw.fifty));
        num.add(new word("fifty one", "cinquanteet un", R.drawable.number_one, R.raw.fifty_one));
        num.add(new word("fifty two", "cinquante-deux", R.drawable.number_two, R.raw.fifty_two));
        num.add(new word("fifty three", "cinquante-trois", R.drawable.number_three, R.raw.fifty_three));
        num.add(new word("fifty four", "cinquante-quatre", R.drawable.number_four, R.raw.fifty_four));
        num.add(new word("fifty five", "cinquante-cinq", R.drawable.number_five, R.raw.fifty_five));
        num.add(new word("fifty six", "cinquante-six", R.drawable.number_six, R.raw.fifty_six));
        num.add(new word("fifty seven", "cinquante-sept", R.drawable.number_seven, R.raw.fifty_seven));
        num.add(new word("fifty eight", "cinquante-huit", R.drawable.number_eight, R.raw.fifty_eight));
        num.add(new word("fifty nine", "cinquante-neuf", R.drawable.number_nine, R.raw.fifty_nine));
        num.add(new word("sixty", "soixante", R.drawable.number_ten, R.raw.sixty));
        num.add(new word("sixty one", "soixanteet un", R.drawable.number_one, R.raw.sixty_one));
        num.add(new word("sixty two", "soixante-deux", R.drawable.number_two, R.raw.sixty_two));
        num.add(new word("sixty three", "soixante-trois", R.drawable.number_three, R.raw.sixty_three));
        num.add(new word("sixty four", "soixante-quatre", R.drawable.number_four, R.raw.sixty_four));
        num.add(new word("sixty five", "soixante-cinq", R.drawable.number_five, R.raw.sixty_five));
        num.add(new word("sixty six", "soixante-six", R.drawable.number_six, R.raw.sixty_six));
        num.add(new word("sixty seven", "soixante-sept", R.drawable.number_seven, R.raw.sixty_seven));
        num.add(new word("sixty eight", "soixante-huit", R.drawable.number_eight, R.raw.sixty_eight));
        num.add(new word("sixty nine", "soixante-neuf", R.drawable.number_nine, R.raw.sixty_nine));
        num.add(new word("seventy", "soixante-dix", R.drawable.number_ten, R.raw.seventy));
        num.add(new word("seventy one", "soixante et onze", R.drawable.number_one, R.raw.seventy_one));
        num.add(new word("seventy two", "soixante-douze", R.drawable.number_two, R.raw.seventy_two));
        num.add(new word("seventy three", "soixante-treize", R.drawable.number_three, R.raw.seventy_three));
        num.add(new word("seventy four", "soixante-quatorze", R.drawable.number_four, R.raw.seventy_four));
        num.add(new word("seventy five", "soixante-quinze", R.drawable.number_five, R.raw.seventy_five));
        num.add(new word("seventy six", "soixante-seize", R.drawable.number_six, R.raw.seventy_six));
        num.add(new word("seventy seven", "soixante-dix-sept", R.drawable.number_seven, R.raw.seventy_seven));
        num.add(new word("seventy eight", "soixante-dix-huit", R.drawable.number_eight, R.raw.seventy_eight));
        num.add(new word("seventy nine", "soixante-dix-neuf", R.drawable.number_nine, R.raw.seventy_nine));
        num.add(new word("eighty", "quatre-vingts", R.drawable.number_ten, R.raw.eighty));
        num.add(new word("eighty one", "quatre-vingt-un", R.drawable.number_one, R.raw.eighty_one));
        num.add(new word("eighty two", "quatre-vingt-deux", R.drawable.number_two, R.raw.eighty_two));
        num.add(new word("eighty three", "quatre-vingt-trois", R.drawable.number_three, R.raw.eighty_three));
        num.add(new word("eighty four", "quatre-vingt-quatre", R.drawable.number_four, R.raw.eighty_four));
        num.add(new word("eighty five", "quatre-vingt-cinq", R.drawable.number_five, R.raw.eighty_five));
        num.add(new word("eighty six", "quatre-vingt-six", R.drawable.number_six, R.raw.eighty_six));
        num.add(new word("eighty seven", "quatre-vingt-sept", R.drawable.number_seven, R.raw.eighty_seven));
        num.add(new word("eighty eight", "quatre-vingt-huit", R.drawable.number_eight, R.raw.eighty_eight));
        num.add(new word("eighty nine", "quatre-vingt-neuf", R.drawable.number_nine, R.raw.eighty_nine));
        num.add(new word("ninety", "quatre-vingt-dix", R.drawable.number_ten, R.raw.ninety));
        num.add(new word("ninety one", "quatre-vingt-onze", R.drawable.number_one, R.raw.ninety_one));
        num.add(new word("ninety two", "quatre-vingt-douze", R.drawable.number_two, R.raw.ninety_two));
        num.add(new word("ninety three", "quatre-vingt-treize", R.drawable.number_three, R.raw.ninety_three));
        num.add(new word("ninety four", "quatre-vingt-quatorze", R.drawable.number_four, R.raw.ninety_four));
        num.add(new word("ninety five", "quatre-vingt-quinze", R.drawable.number_five, R.raw.ninety_five));
        num.add(new word("ninety six", "quatre-vingt-seize", R.drawable.number_six, R.raw.ninety_six));
        num.add(new word("ninety seven", "quatre-vingt-dix-sept", R.drawable.number_seven, R.raw.ninety_seven));
        num.add(new word("ninety eight", "quatre-vingt-dix-huit", R.drawable.number_eight, R.raw.ninety_eight));
        num.add(new word("ninety nine", "quatre-vingt-dix-neuf", R.drawable.number_nine, R.raw.ninety_nine));
        num.add(new word("Hundred", "cent", R.drawable.number_ten, R.raw.cent));


        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        WordAdapter adapter = new WordAdapter(this, num, R.color.number);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                word ob = num.get(position);
                                                releaseMediaPlayer();
                                                int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                                                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                                                    media = MediaPlayer.create(NumbersActivity.this, ob.getAudioResourse());
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

    public void releaseMediaPlayer() {
        if (media != null) {
            media.release();
            media = null;

            audioManager.abandonAudioFocus(afChangeListener);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }
}