package com.example.poiondelivery

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPassActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val userEmail : EditText = findViewById(R.id.email_fp)
        val button : Button = findViewById(R.id.button_code)
        val authLink : TextView = findViewById(R.id.forgot_and_remembered)

        authLink.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            val email = userEmail.text.toString().trim()
            if(email == "" || !(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
                Toast.makeText(this, "Некорректный E-mail", Toast.LENGTH_SHORT).show()
            }
            else {
                val db = DbHelper(this)
                val success = db.checkEmail(email)

                if(success){
                    Toast.makeText(this, "Код восстановления отправлен на $email", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Аккаунта с $email не существует", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}