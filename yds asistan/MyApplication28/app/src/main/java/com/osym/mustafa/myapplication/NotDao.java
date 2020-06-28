package com.osym.mustafa.myapplication;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
@Dao
public interface NotDao {
    @Query("select * from notlar")
    public List<Not> getAll();
    @Insert
    public void insertAll(Not...notes);
    @Delete
    public void deleteAll(Not...notes);
    @Query("select * from  notlar")
    LiveData<List<Not>> getAllLive();
    @Query("delete from notlar")
    void clearDb();
}
