package com.seyyedehtorkanhesari.seyyedehtorkanhesarihw2;

import android.database.Cursor;

import java.util.ArrayList;

public class Commons {
    public static SocialMedia media;
    public static Cursor cursor;
    public static ArrayList<SocialMedia> data;
    public static SocialMedia getMedia() {
        return media;

    }
    public static void setMedia(SocialMedia media) {
        Commons.media = media;
    }
    public static ArrayList<SocialMedia> getData() {
        return data;
    }
    public static void setData(ArrayList<SocialMedia> data) {
        Commons.data = data;
    }
}

