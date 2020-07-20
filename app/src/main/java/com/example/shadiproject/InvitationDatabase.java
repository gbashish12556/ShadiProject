package com.example.shadiproject;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.shadiproject.Pojo.PersonInfo;
import com.example.shadiproject.Pojo.PictureConverter;


@Database(entities = PersonInfo.class, version = 3, exportSchema = false)
@TypeConverters(PictureConverter.class)
public abstract class InvitationDatabase extends RoomDatabase {

    private static InvitationDatabase instance;

    public abstract InvitationDao invitationDao();

    public static synchronized InvitationDatabase getInstance(Context context){
        if(instance ==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), InvitationDatabase.class, "issues_database")
                    .addCallback(mCallBack)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    private static RoomDatabase.Callback mCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
