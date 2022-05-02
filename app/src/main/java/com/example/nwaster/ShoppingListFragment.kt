package com.example.nwaster

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import android.view.ContextMenu.ContextMenuInfo
import android.widget.AdapterView.AdapterContextMenuInfo
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoppingList_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoppingList_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var Btn_add : Button? = null
    var shop_list: ArrayList<String> = arrayListOf("Warehouse")
    private var instance: ShoppingList_fragment? = null
    var listView : ListView?=null
    private var arrayAdapter : ListViewAdapter?=null

    private val FormLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        if(activityResult.resultCode == RESULT_OK)
        {
            val name_list = activityResult.data?.getStringExtra("name_list")
            Toast.makeText(view?.context,name_list,Toast.LENGTH_SHORT).show()
            addItem(name_list!!)
        }
        else{
            Toast.makeText(view?.context,"ERROR",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.shopping_list_fragment, container, false)
        val view: View = inflater.inflate(R.layout.shopping_list_fragment, container, false)
        //val shop_list = arrayOf("Principal")
        val button2 : FloatingActionButton = view.findViewById(R.id.add_list)

        listView= view.findViewById(R.id.shopping_lists)
        instance = this


        button2.setOnClickListener{
            //Toast.makeText(view?.context, "Añadir lista clickado", Toast.LENGTH_LONG).show()
            val intent = Intent(view.context, AddListForm::class.java)
            FormLauncher.launch(intent)
        }
        arrayAdapter= ListViewAdapter(view.context,shop_list)
        listView?.adapter=arrayAdapter
        registerForContextMenu(listView!!)
        loadContent()
        // Return the fragment view/layout
        return view
    }

    fun loadContent(){
        var path : File = activity?.applicationContext!!.filesDir
        var readFrom : File = File(path, "lists.txt")
        var content : ByteArray = ByteArray(readFrom.length().toInt())

        var stream : FileInputStream = FileInputStream(readFrom)
        stream.read(content)

        var s : String = String(content)
        s = s.substring(1,s.length - 1)

        var split : List<String> = s.split(", ")

        shop_list = ArrayList(split)
        arrayAdapter= ListViewAdapter(activity?.applicationContext,shop_list)
        listView?.adapter=arrayAdapter
        registerForContextMenu(listView!!)

    }
    override fun onDestroyView() {
        var path : File = activity?.applicationContext!!.filesDir
        var writer : FileOutputStream = FileOutputStream(File(path,"lists.txt"))
        writer.write(shop_list.toString().toByteArray())
        writer.close()
        super.onDestroyView()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoppingList_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShoppingList_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    fun addItem(item:String){
        shop_list.add(item)
        listView!!.adapter=arrayAdapter
    }
    fun getInstance(): ShoppingList_fragment? {
        return instance
    }

    fun removeItem(remove:Int){
        shop_list.removeAt(remove)
        listView?.adapter=arrayAdapter
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo?) {
        activity?.menuInflater?.inflate(R.menu.list_menu,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.list_delete ->{
                val info = item.menuInfo as AdapterContextMenuInfo
                val index = info.position
                if (index == 0){
                    Toast.makeText(view?.context, "Unable to delete Warehouse list", Toast.LENGTH_LONG).show()
                }
                else{
                    removeItem(index)
                }
                return true
            }
            R.id.list_edit->{
                val info = item.menuInfo as AdapterContextMenuInfo
                val index = info.position
                if (index == 0){
                    Toast.makeText(view?.context, "Unable to delete Warehouse list", Toast.LENGTH_LONG).show()
                }
                else{
                    //Añadir método para modificar nombre y notas de una lista
                    Toast.makeText(view?.context, "Editing list...", Toast.LENGTH_LONG).show()
                }
                return true
            }
            else -> return super.onContextItemSelected(item)
        }

        //return super.onContextItemSelected(item)
    }

}