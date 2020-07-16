package com.example.internship1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class MainActivity : AppCompatActivity() {

    val Auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCreate.setOnClickListener {
            val intent = Intent(this,SignUP::class.java)
            startActivity(intent)
        }
        val signIn = findViewById(R.id.btnSignIn) as Button
        signIn.setOnClickListener {
            login();
        }
    }
    private fun login()
    {
        prgsIn.visibility = View.VISIBLE
        val username = findViewById(R.id.InUser) as EditText
        val pass = findViewById(R.id.InPass) as EditText

        var user = username.getText().toString().trim()
        var Pass = pass.getText().toString().trim()


       if(user.isEmpty() && Pass.isEmpty())
            Toast.makeText(this,"Enter Username or password",Toast.LENGTH_SHORT).show()
       else
       {
            Auth.signInWithEmailAndPassword(user,Pass).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if(task.isSuccessful)
                    {
                        var User = Auth.getCurrentUser()
                        startActivity(Intent(this,SignIn::class.java))
                        Toast.makeText(this,"Sign in successful",Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(this,"Sign In Failed",Toast.LENGTH_SHORT).show()
                    }
                })
        }
        prgsIn.visibility = View.GONE
    }

}
