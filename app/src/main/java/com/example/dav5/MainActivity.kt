package com.example.dav5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var email_input: TextInputEditText
    private lateinit var pass_input1: TextInputEditText
    private lateinit var pass_input2: TextInputEditText
    private lateinit var submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        registerListeners()
    }

    private fun init() {
        email_input = findViewById(R.id.email)
        pass_input1 = findViewById(R.id.pass1)
        pass_input2 = findViewById(R.id.pass2)

        submit = findViewById(R.id.submit)
    }

    private fun registerListeners() {
        submit.setOnClickListener {
            val email = email_input.text.toString()
            val password = email_input.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "email or password fields should not be empty",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (pass_input1 != pass_input2) {
                Toast.makeText(this, "passwords do not match", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (pass_input1.length() < 9) {
                Toast.makeText(this, "password should be 9 characters or more", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "user has been registered", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "something went wrong, try again", Toast.LENGTH_LONG)
                            .show()
                    }
                }


        }
    }
}