package com.example.mixedsale.data.db.converter

import androidx.room.TypeConverter
import java.util.*

class Converts {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}