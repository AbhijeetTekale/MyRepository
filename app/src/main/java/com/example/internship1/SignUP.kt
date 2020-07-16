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
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUP : AppCompatActivity() {

    val Auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btnAlready.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val signUp = findViewById(R.id.btnSignUp) as Button
        signUp.setOnClickListener {
            register();
        }

    }
    private fun register()
    {
        prgsUP.visibility = View.VISIBLE
        val username = findViewById(R.id.edtUser) as EditText
        val pass = findViewById(R.id.edtPass) as EditText
        val Repass = findViewById(R.id.edtRepass) as EditText

        var user = username.getText().toString().trim()
        var Pass = pass.getText().toString().trim()
        var rePass = Repass.getText().toString().trim()

        if(!(Pass==rePass))
            Toast.makeText(this,"Passwords are not matching",Toast.LENGTH_SHORT).show()
        else
            if(user.isEmpty() && Pass.isEmpty())
                Toast.makeText(this,"Enter Username or password",Toast.LENGTH_SHORT).show()
            else
                Auth.createUserWithEmailAndPassword(user,Pass).addOnCompleteListener(this,
                    OnCompleteListener { task ->
                        if(task.isSuccessful)
                        {
                            val user = Auth.getCurrentUser()
                            user?.sendEmailVerification()?.addOnCompleteListener { task ->
                                if(task.isSuccessful)
                                    Toast.makeText(this,"Email sent successfully",Toast.LENGTH_SHORT).show()
                                else
                                    Toast.makeText(this,"Email not sent",Toast.LENGTH_SHORT).show()

                            }
                            Toast.makeText(this,"User Registered Successfully",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity :: class.java))
                            finish()
                        }
                        else
                        {
                            Toast.makeText(this,"User Registration Failed",Toast.LENGTH_SHORT).show()
                        }
                    })
        prgsUP.visibility = View.VISIBLE
    }

}
