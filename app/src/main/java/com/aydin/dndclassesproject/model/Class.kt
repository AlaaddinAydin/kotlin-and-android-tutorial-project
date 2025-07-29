package com.aydin.dndclassesproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Class(
    @ColumnInfo(name = "isim")
    val isim: String,

    @SerializedName("hit_dice")
    @ColumnInfo(name = "hit_dice")
    val hitDice: String,

    @ColumnInfo(name = "subclasslar")
    val subclasslar: String,

    @ColumnInfo(name = "gorsel")
    val gorsel: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}