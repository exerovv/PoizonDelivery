package com.example.poiondelivery

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userLogin : EditText = findViewById(R.id.email_log)
        val userPassword : EditText = findViewById(R.id.pass_log)
        val button : Button = findViewById(R.id.button_log)
        val regLink : TextView = findViewById(R.id.no_acc)

        regLink.setOnClickListener{
            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()
            if(login == "" || password == ""){
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            }
            else{
                val db = DbHelper(this)
                if (db.userExist(login, password))
                    Toast.makeText(this, "Пользователь $login найден", Toast.LENGTH_LONG).show()
                else{
                    Toast.makeText(this, "Пользователя не существует", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}