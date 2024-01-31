package com.example.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("notes")
class Note(@ColumnInfo("text")val text:String) {
    @PrimaryKey(autoGenerate = true) var id=0
}