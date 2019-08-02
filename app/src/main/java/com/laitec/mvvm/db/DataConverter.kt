package com.laitec.mvvm.db

import android.util.TypedValue
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.util.*


@TypeConverters
class DataConverter {

    @TypeConverter
    fun convertToString(obj : JsonObject) : String {
        return Gson().fromJson(obj, String::class.java)
    }

    @TypeConverter
    fun convertToJsonObject(value : String) : JsonObject {
        return Gson().toJson(value) as JsonObject
    }

    @TypeConverter
    fun convertDateToString(date : Date) : String {
        return date.toString()
    }
}