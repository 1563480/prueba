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
    var listView : ListView?=null
    private var arrayAdapter :ListViewAdapter?=null

    private val FormLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        if(activityResult.resultCode == Activity.RESULT_OK)
        {
            val name_list = activityResult.data?.getStringExtra("name_list")
            Toast.makeText(view?.context,name_list, Toast.LENGTH_SHORT).show()
            addItem(name_list!!)
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

            val intent = Intent(root.context, ShoppingListProducts::class.java)
            intent.putExtra("name_list", name)
            startActivity(intent)
        })
        return root
    }

    override fun onDestroyView() {
        var path : File = activity?.applicationContext!!.filesDir
        var writer : FileOutputStream = FileOutputStream(File(path,"lists.txt"))
        writer.write(shop_list.toString().toByteArray())
        writer.close()
        super.onDestroyView()
        _binding = null
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
        arrayAdapter= ListViewAdapter(activity?.applicationContext, shop_list)
        listView?.adapter=arrayAdapter
        registerForContextMenu(listView!!)

    }


    fun addItem(item:String){
        shop_list.add(item)
        listView!!.adapter=arrayAdapter
    }


    fun removeItem(remove:Int){
        shop_list.removeAt(remove)
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