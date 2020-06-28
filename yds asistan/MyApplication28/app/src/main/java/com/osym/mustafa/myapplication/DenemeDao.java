package com.osym.mustafa.myapplication;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DenemeDao  {
    @Query("select * from denemeler")
    public List<Deneme>getAll();
    @Insert
    public void insertAll(Deneme...denemes);
    @Delete
    public void deleteAll(Deneme...denemes);
    @Query("select * from denemeler")
    LiveData<List<Deneme>>getAllLive();
    @Query("delete from denemeler")
    void clearDb();

}
