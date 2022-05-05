package com.example.nwaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val db = Database()
    private val db2 = FirebaseFirestore.getInstance()


    private val GOOGLE_SIGN_IN = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Setup
        setup()
        //session()


    }

    private fun setup() {
        title = "Authentication"


        addProduct.setOnClickListener {
            addProduct.setOnClickListener {
                if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString()).addOnCompleteListener() {
                        if (it.isSuccessful) {
                            showHome()
                            db.getUser(emailEditText.text.toString())
                        } else {
                            showAlert()
                        }
                    }
                }

            }
        }



        googleButton.setOnClickListener() {


            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id2))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
            val user = Firebase.auth.currentUser
            if (user != null) {
                user.email?.let { it1 -> db.getUser(it1) }
            }



        }

        register.setOnClickListener() {
            showRegister()
        }


    }
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en la autenticación")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome() {
        val homeIntent = Intent(this, BottomNavActivity::class.java).apply {
        }
        startActivity(homeIntent)
    }

    private fun showRegister() {
        val homeIntent = Intent(this, RegisterActivity::class.java).apply {
        }
        startActivity(homeIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome()
                            //db.getUser(account.email.toString())
                        }
                    }
                }
            } catch(e: ApiException) {
                showAlert()
            }

        }
    }


}

