package com.example.poiondelivery

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context : Context?) : SQLiteOpenHelper(context, "users.db", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE IF NOT EXISTS USER_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, USER_EMAIL TEXT NOT NULL, USER_PASSWORD TEXT NOT NULL)".trimIndent()
        db!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addUser(user : User) : Boolean{
        val cv = ContentValues()
        cv.put("USER_EMAIL", user.login)
        cv.put("USER_PASSWORD", user.password)

        val db = this.writableDatabase

        val insert : Long = db.insert("USER_TABLE", null, cv)
        db.close()
        if (insert == -1L){
            return false
        }else{
            return true
        }

    }

    fun userExist(login: String, pass: String) : Boolean {
        val query = "SELECT * FROM USER_TABLE WHERE USER_EMAIL = '$login' AND USER_PASSWORD = '$pass'"
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(query, null)
        val userExist = cursor.moveToFirst()
        cursor.close()
        db.close()
        return userExist
    }
}