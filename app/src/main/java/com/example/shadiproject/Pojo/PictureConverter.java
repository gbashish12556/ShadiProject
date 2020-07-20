package com.example.shadiproject.Pojo;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class PictureConverter {

    @TypeConverter
    public static String toJson(Picture picture) {

        if(picture == null)
            return null;

        return new Gson().toJson(picture);
    }

    @TypeConverter
    public static Picture toPicture(String json) {

        if(json == null)
            return null;

        return new Gson().fromJson(json, Picture.class);
    }
}
