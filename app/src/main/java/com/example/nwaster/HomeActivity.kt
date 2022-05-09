package com.example.nwaster

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_stock.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class HomeActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val db2 = Database()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)

        //setup
        setup()

        val user = Firebase.auth.currentUser
        var arrayAdapter: ArrayAdapter<*>

        this.db.collection("users").document(user?.email.toString())
            .get().addOnSuccessListener { result ->
                if (user != null) {
                    println(user.email)
                }


                if(result.data?.getValue("productos") != null) {
                    var arrayProductos:HashMap<String,String> = result.data!!.getValue("productos") as HashMap<String, String>
                    var productosStock: Set<String> = arrayProductos.keys
                    val listStock = findViewById<ListView>(R.id.stockProducts)
                    arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, productosStock.toTypedArray())
                    listStock.adapter = arrayAdapter

                    //Al clicar en un elemento se borra del stock y se añade a la lista de compra
                    listStock.setOnItemClickListener(AdapterView.OnItemClickListener { _, _, position, id ->
                        val item = arrayAdapter.getItem(position)
                        db2.deleteProduct(user?.email.toString(),item.toString())
                        finish()
                        startActivity(getIntent())
                    })
                }




            }



        //val horaActual = Instant.now()
        //println(horaActual.get )

    }
    private fun setup() {
        title = "Home"
        addStock.setOnClickListener() {
            showAddStock()
            //db2.getCollection("Products")
        }
        //addProduct.setOnClickListener() {
          //  showAddProduct()
       // }
    }

    private fun showAddStock() {
        val homeIntent = Intent(this, AddProductStock::class.java).apply {
        }
        startActivity(homeIntent)
    }
    private fun showAddProduct() {
        val homeIntent = Intent(this, StockActivity::class.java).apply {
        }
        startActivity(homeIntent)
    }


}