package com.example.nwaster

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_addproduct.*
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_product_stock.*
import com.example.nwaster.R.layout.activity_add_product_stock
import com.example.nwaster.databinding.ActivityAddProductStockBinding

//import com.example.nwaster.ActivityAddProductStockBinding

class Database {
    var connection : FirebaseFirestore? = FirebaseFirestore.getInstance()
        private set

    fun addUser(email : String) {

        if (this.connection!=null) {
            val products = hashMapOf<String, String>()
            val shoppingList = arrayListOf<String>()
            this.connection!!.collection("users").document(email).set(
                hashMapOf(
                    "email" to email,
                    "productos" to products,
                    "listaCompra" to shoppingList
                )
            )
        }
    }

    fun addProduct(product: Product) {
        if (this.connection!=null) {
            this.connection!!.collection("Products").document().set(
                hashMapOf(
                    "name" to product.name,
                    "barCode" to product.barCode,
                    "carbohydrates" to product.carbo,
                    "category" to product.categor,
                    "fats" to product.fats,
                    "kcal" to product.kcal,
                    "price" to product.price,
                    "protein" to product.protein
                )
            )
        }
        else{
            System.err.println("Error de conexión con la BD")
        }
    }


    fun getUser(email : String) {
        this.connection!!.collection("users")
            .document(email)
            .get()
            .addOnCompleteListener{ it ->
                if(it.result.data != null){
                    System.out.println("Ya esta añadido")
                }else{
                    addUser(email)
                }
            }
            .addOnFailureListener {
                System.err.println("Algo ha fallado")
            }
    }

    fun getCollection(collection : String): MutableList<String> {
        //lateinit var binding: ActivityAddProductStockBinding

        //binding = ActivityAddProductStockBinding.inflate(layout)
        val productos = arrayListOf<String>()
        //val listProducts = findViewById<ListView>(R.id.listProducts)
        this.connection!!.collection(collection)
            .get().addOnSuccessListener { result ->
                for (document in result) {
                    productos.add(document.id)

                    //Log.d(TAG, "${document.id}=>${document.data}")
                }
                println(productos)

            }
            .addOnFailureListener{exception->
                Log.w(TAG,"Error getting documents.",exception)
            }

            return productos
    }

    //eliminar un producto del stock
    fun deleteProduct(user: String, product:String) {
        System.out.println("Eliminando "+product)


        this.connection!!.collection("users").document(user).get()
            .addOnCompleteListener { it ->
                if(it.result.data?.getValue("productos") != null){
                    val productos = it.result.data!!.getValue("productos") as HashMap<String,String>
                    productos.remove(product)
                    this.connection!!.collection("users").document(user).update("productos",productos)
                    addProductToShoppingList(user,product)
                }

            }
    }

    // funcion para añadir un producto a la lista de compra
    fun addProductToShoppingList(user:String, product:String){
        this.connection!!.collection("users").document(user).get().addOnCompleteListener { it ->
            val list = it.result!!.data!!.getValue("listaCompra") as ArrayList<String>
            list.add(product)
            this.connection!!.collection("users").document(user).update("listaCompra",list)
        }

    }

}