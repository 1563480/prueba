package com.example.nwaster.ui.warehouse

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nwaster.databinding.FragmentWarehouseBinding
import com.google.zxing.integration.android.IntentIntegrator

class WarehouseFragment : Fragment() {

    // get reference to the adapter class

    //private val db= Database()
    private var _binding: FragmentWarehouseBinding? = null
    private val binding get() = _binding!!

    private var cont=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(WarehouseViewModel::class.java)
        _binding = FragmentWarehouseBinding.inflate(inflater, container, false)
        val root: View = binding.root
        /*
        val : TextView = binding.eListView
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/



        binding.expanableBtnScanBarCode.setOnClickListener{
            //initScanner()
            var barcode:Long=0
            if (cont==0){
                barcode= 8480000820952
            }
            if (cont==1){
                barcode=5449000131805
            }
            if (cont==2){
                barcode= 80052760
            }

            //db.getProduct("Product",barcode,binding )
            cont +=1
        }

        return root
    }



    /*
    estos dos métodos se encargar de realizar el escaneo de código de barras.
    Para no tener que escanear todoo el rato un producto en el click listener comento la funcion initScan
     */
    //Scaner barcode
    private fun initScanner(){
        val integrator= IntentIntegrator.forSupportFragment(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("inserta en el rectangulo")
        integrator.setBeepEnabled(true)

        integrator.initiateScan()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (result != null){
            if (result.contents==null){
                Toast.makeText(context,"Cancelado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"El valor es: ${result.contents}", Toast.LENGTH_SHORT).show()

            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)

        }
    }
    //----

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}