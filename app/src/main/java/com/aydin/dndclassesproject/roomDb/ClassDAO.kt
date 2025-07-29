package com.aydin.dndclassesproject.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aydin.dndclassesproject.model.Class

@Dao
interface ClassDAO {
    @Query("SELECT * FROM class")
    suspend fun getAll() : List<Class>

    @Query("DELETE FROM class")
    suspend fun deleteAll()

    @Query("SELECT * FROM class WHERE uuid = :classId")
    suspend fun getClassById(classId : Int) : Class

    @Insert
    suspend fun insertAll(vararg classes: Class) : List<Long>
}