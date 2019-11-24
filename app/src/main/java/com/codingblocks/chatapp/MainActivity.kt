package com.codingblocks.chatapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginBtn.setOnClickListener {
            auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                .addOnSuccessListener {
                    Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    if (it.localizedMessage.contains("already", true)) {
                        signIn(email.text.toString(), pass.text.toString())
                    } else {
                        Toast.makeText(this, "${it.localizedMessage}", Toast.LENGTH_LONG).show()
                    }

                }
        }
    }

    private fun signIn(email: String, pass: String) {
            auth.signInWithEmailAndPassword(email,pass)
                .addOnSuccessListener {
                    Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_LONG).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "${it.localizedMessage}", Toast.LENGTH_LONG).show()
                }
    }
}
