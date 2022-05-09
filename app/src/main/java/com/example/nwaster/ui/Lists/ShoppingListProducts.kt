package com.example.nwaster.ui.Lists

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.nwaster.AddListForm
import com.example.nwaster.AddProductList
import com.example.nwaster.AddProductStock
import com.example.nwaster.R
import com.example.nwaster.databinding.BottomNavBinding
import com.example.nwaster.databinding.ShoppingListProductsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingListProducts : AppCompatActivity() {
    private lateinit var binding: ShoppingListProductsBinding
    var products_list: ArrayList<String> = arrayListOf()
    var listView : ListView?=null
    var position : Int = 0
    private var arrayAdapter :ListViewAdapter?=null
    private val FormLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        if(activityResult.resultCode == Activity.RESULT_OK)
        {
            val product_to_add = activityResult.data?.getStringExtra("product_to_add")
            Toast.makeText(this,product_to_add, Toast.LENGTH_SHORT).show()
            addItem(product_to_add!!)
        }
        else{
            Toast.makeText(this,"ERROR", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShoppingListProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listView = binding.productsOfList

        val name = intent.getStringExtra("name_list")
        val notes = intent.getStringExtra("notes_list")
        val products = intent.getStringArrayListExtra("products_list")
        position = intent.getIntExtra("position",0)
        if (products!!.get(0) != "null")
        {
            products_list = products
        }

        val nameText : TextView = binding.nameListProducts
        nameText.text = name

        val notesText : TextView = binding.notesListProducts
        notesText.text = notes

        binding.expanableBtnScanManualLists.setOnClickListener(){
            val intent = Intent(this, AddProductList::class.java)
            FormLauncher.launch(intent)
        }
        arrayAdapter= ListViewAdapter(this, products_list)
        listView?.adapter=arrayAdapter
        registerForContextMenu(listView!!)
    }

    fun addItem(item:String){
        products_list.add(item)
        listView!!.adapter=arrayAdapter
    }

    fun removeItem(remove:Int){
        products_list.removeAt(remove)
        listView?.adapter=arrayAdapter
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater?.inflate(R.menu.list_menu,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.list_delete ->{
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val index = info.position
                removeItem(index)
                return true
            }
            R.id.list_edit->{
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val index = info.position
                Toast.makeText(this, "Editing product...", Toast.LENGTH_LONG).show()

                return true
            }
            else -> return super.onContextItemSelected(item)
        }

        //return super.onContextItemSelected(item)
    }

    override fun onBackPressed() {
        val returnIntent = Intent()
        returnIntent.putExtra("products_of_list", products_list)
        returnIntent.putExtra("position", position)
        setResult(RESULT_OK, returnIntent)
        finish()
        super.onBackPressed()
    }
}