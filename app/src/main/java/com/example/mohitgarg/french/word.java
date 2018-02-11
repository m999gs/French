package com.example.mohitgarg.french;

public class word {
    private String EnglishWords;
    private String FrenchTranslation;
    private int image;
    private int audioResourse;

    word(String a, String b, int c, int audio) {
        EnglishWords = a;
        FrenchTranslation = b;
        image = c;
        audioResourse = audio;
    }

    word(String a, String b, int audio) {
        EnglishWords = a;
        FrenchTranslation = b;
        image = 0;
        audioResourse = audio;
    }

    public String getEnglishWords() {
        return EnglishWords;
    }

    public String getFrenchTranslation() {
        return FrenchTranslation;
    }

    public int getimage() {
        return image;
    }

    public int getAudioResourse() {
        return audioResourse;
    }

}