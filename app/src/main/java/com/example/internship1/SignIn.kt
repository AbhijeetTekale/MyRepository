package com.example.internship1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        button1.setOnClickListener {
            Toast.makeText(this,"You Clicked In", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,SignIn::class.java))
        }
        button2.setOnClickListener {
            Toast.makeText(this,"You Clicked UP", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,SignUP::class.java))
        }
    }
}
