package com.example.nwaster

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_product_stock.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class AddProductList : AppCompatActivity() {

    var connection : FirebaseFirestore? = FirebaseFirestore.getInstance()
        private set
    var product_to_add : String? = null
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_stock)
        val user = Firebase.auth.currentUser //Variable superglobal ? intentar eliminar el auth
        val productos = hashMapOf<String,String>()
        val productosStock = arrayListOf<String>()
        setup()
        var arrayAdapter: ArrayAdapter<*>
        this.connection!!.collection("Products")
            .get().addOnSuccessListener { result ->
                for (document in result) {
                    productosStock.add(document.id)
                    val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
                    val fecha = Calendar.getInstance()
                    fecha.add(Calendar.DATE,10)
                    productos.put(document.id,dateFormat.format(fecha.time).toString())
                }
                val listProducts = findViewById<ListView>(R.id.listProducts)
                arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, productosStock)
                listProducts.adapter = arrayAdapter

                var arrayAux = connection!!.collection("users").document(user?.email.toString()).get().addOnSuccessListener { it ->
                    var elementos:HashMap<String,String> = HashMap<String,String>()
                    elementos = it.data?.getValue("productos") as HashMap<String, String>


                    listProducts.setOnItemClickListener() {parent,view,position,id->
                        //System.out.println(parent.getItemAtPosition(position).toString())
                        if (user != null) {
                            product_to_add = parent.getItemAtPosition(position).toString()
                            parent.getChildAt(position).setBackgroundColor(Color.BLUE)
                        }
                    }
                }
            }
        buttonback.setOnClickListener() {
            val returnIntent = Intent()
            returnIntent.putExtra("product_to_add", product_to_add)
            setResult(RESULT_OK, returnIntent)
            finish()


        }
    }
    private fun setup() {
        title = "Añadir producto al stock"

    }
    private fun showBack() {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
        }
        startActivity(homeIntent)
    }
}