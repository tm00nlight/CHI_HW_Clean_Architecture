package com.example.chi_hw_clean_architecture.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chi_hw_clean_architecture.data.model.Marvel
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMarvels(list: List<Marvel>)

    @Query("select * from Marvel")
    fun fetchMarvels(): Flow<List<Marvel>>
}