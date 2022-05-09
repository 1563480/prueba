package com.example.nwaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_recover.*

class RecoverActivity : AppCompatActivity() {
    private var email = ""
    private val user = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        btnResetPassword.setOnClickListener() {
            email = editPasswordReset.text.toString()
            if(!email.isEmpty()) {
                resetPassword()
            }
            else{
                Toast.makeText(this, "Introduce un email para reestablecer la contraseña", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun resetPassword() {
        user.setLanguageCode("es") // español el correo
        user.sendPasswordResetEmail(email).addOnCompleteListener() { task->
            if(task.isSuccessful) {
                Toast.makeText(this, "Se ha enviado un correo para reestablecer la contraseña", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No se ha podido enviar el correo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}