package com.example.nwaster.ui.Lists

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nwaster.*
import com.example.nwaster.ui.Lists.ListViewAdapter
import com.example.nwaster.databinding.FragmentListsBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ListsFragment : Fragment() {

    private var _binding: FragmentListsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var shop_list: ArrayList<String> = arrayListOf("Warehouse")
    var shop_list_notes: ArrayList<String> = arrayListOf("Default")
    var shop_list_products: ArrayList<ArrayList<String>> = arrayListOf(arrayListOf("Hola","Buenas","Product3"))
    var listView : ListView?=null
    private var arrayAdapter :ListViewAdapter?=null


    private val FormLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        if(activityResult.resultCode == Activity.RESULT_OK)
        {
            val name_list = activityResult.data?.getStringExtra("name_list")
            val notes_list = activityResult.data?.getStringExtra("notes_list")
            addItem(name_list!!,notes_list!!, arrayListOf("null"))
        }
        else{
            Toast.makeText(view?.context,"ERROR", Toast.LENGTH_SHORT).show()
        }

    }

    private val FormLauncher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        if(activityResult.resultCode == Activity.RESULT_OK)
        {
            var list_products = activityResult.data?.getStringArrayListExtra("products_of_list")
            if (list_products.isNullOrEmpty())
            {
                list_products = arrayListOf("null")
            }
            val val_position = activityResult.data?.getIntExtra("position",0)
            addProduct(list_products!!,val_position!!)
            //AÑADIR PRODUCTOS SELECCIONADOS AL ARRAY DE PRODUCTOS REFERENTES A LA LISTA QUE YA HABÍA
            //addItem(name_list!!,notes_list!!)
        }
        else{
            Toast.makeText(view?.context,"ERROR", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(ListsViewModel::class.java)

        _binding = FragmentListsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        /*
        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

         */
        listView = binding.listsBottom
        binding.addListBottom.setOnClickListener{
            //Toast.makeText(view?.context, "Añadir lista clickado", Toast.LENGTH_LONG).show()
            val intent = Intent(root.context, AddListForm::class.java)
            FormLauncher.launch(intent)
        }
        arrayAdapter= ListViewAdapter(root.context, shop_list)
        listView?.adapter=arrayAdapter
        registerForContextMenu(listView!!)
        loadContent()

        listView?.setOnItemClickListener(AdapterView.OnItemClickListener { arg0, arg1, position, arg3 -> // TODO Auto-generated method stub
            val name: String = shop_list.get(position)
            val notes: String = shop_list_notes.get(position)
            val products : ArrayList<String> = shop_list_products.get(position)

            val intent = Intent(root.context, ShoppingListProducts::class.java)
            intent.putExtra("name_list", name)
            intent.putExtra("notes_list", notes)
            intent.putExtra("products_list", products)
            intent.putExtra("position", position)
            FormLauncher2.launch(intent)
            //startActivity(intent)
        })
        return root
    }

    override fun onDestroyView() {
        var path : File = activity?.applicationContext!!.filesDir
        var writer : FileOutputStream = FileOutputStream(File(path,"lists.txt"))
        var writer2 : FileOutputStream = FileOutputStream(File(path,"lists_notes.txt"))
        var writer3 : FileOutputStream = FileOutputStream(File(path,"lists_products.txt"))
        writer.write(shop_list.toString().toByteArray())
        writer.close()
        writer2.write(shop_list_notes.toString().toByteArray())
        writer2.close()
        writer3.write(shop_list_products.toString().toByteArray())
        writer3.close()
        super.onDestroyView()
        //_binding = null
    }

    fun loadContent(){
        var path : File = activity?.applicationContext!!.filesDir
        var readFrom : File = File(path, "lists.txt")
        var readFrom2 : File = File(path, "lists_notes.txt")
        var readFrom3 : File = File(path, "lists_products.txt")

        if (readFrom.exists() && readFrom2.exists() && readFrom3.exists()) {


            var content: ByteArray = ByteArray(readFrom.length().toInt())
            var content2: ByteArray = ByteArray(readFrom2.length().toInt())
            var content3: ByteArray = ByteArray(readFrom3.length().toInt())

            var stream: FileInputStream = FileInputStream(readFrom)
            stream.read(content)

            var stream2: FileInputStream = FileInputStream(readFrom2)
            stream2.read(content2)

            var stream3: FileInputStream = FileInputStream(readFrom3)
            stream3.read(content3)

            var s: String = String(content)
            s = s.substring(1, s.length - 1)

            var split: List<String> = s.split(", ")

            var s2: String = String(content2)
            s2 = s2.substring(1, s2.length - 1)

            var split2: List<String> = s2.split(", ")

            var s3: String = String(content3)
            s3 = s3.substring(1, s3.length - 1)

            var split3: List<String> = s3.split("[", "], ", "]")

            var filteredList = split3.filter { x -> x.length > 0 }

            shop_list_products = ArrayList(ArrayList())
            for (i in filteredList) {
                var split4: List<String> = i.split(", ")
                val aux = ArrayList(split4)
                shop_list_products.add(aux)
                val x = 0
            }

            shop_list = ArrayList(split)
            shop_list_notes = ArrayList(split2)
            arrayAdapter = ListViewAdapter(activity?.applicationContext, shop_list)
            listView?.adapter = arrayAdapter
            registerForContextMenu(listView!!)
        }

    }


    fun addItem(item:String,notes:String,products:ArrayList<String>){
        shop_list.add(item)
        shop_list_notes.add(notes)
        shop_list_products.add(products)
        listView!!.adapter=arrayAdapter
    }
    fun addProduct(products:ArrayList<String>,position:Int){
        shop_list_products.add(position,products)
        shop_list_products.removeAt(position+1)
        listView!!.adapter=arrayAdapter
    }


    fun removeItem(remove:Int){
        shop_list.removeAt(remove)
        shop_list_notes.removeAt(remove)
        shop_list_products.removeAt(remove)
        listView?.adapter=arrayAdapter
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        activity?.menuInflater?.inflate(R.menu.list_menu,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.list_delete ->{
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
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
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
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