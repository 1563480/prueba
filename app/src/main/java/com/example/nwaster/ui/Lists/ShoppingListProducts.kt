package com.example.nwaster.ui.Lists

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nwaster.databinding.BottomNavBinding
import com.example.nwaster.databinding.ShoppingListProductsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingListProducts : AppCompatActivity() {
    private lateinit var binding: ShoppingListProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShoppingListProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name_list")

        val nameText : TextView = binding.nameListProducts
        nameText.text = name
    }
}