package com.osym.mustafa.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Deneme.class,Not.class},version = 1)
public abstract  class AppDatabase extends RoomDatabase {
    public DenemeDao getDenemeDao;
    private  static AppDatabase instance;
    public abstract DenemeDao getDenemDao();
    public abstract NotDao getNotDao();
    public static AppDatabase getInstance(Context context)
    {
        if (instance==null){

            instance=Room.databaseBuilder(context,AppDatabase.class,"yds_db").
                    allowMainThreadQueries().
                    build();
        }
        return instance;
    }
}
