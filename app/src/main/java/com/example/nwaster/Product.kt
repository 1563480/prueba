package com.example.nwaster
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Product(
    val name:String? = null, val barCode:String? = "0", val carbo:String? = "0", val categor:String? = "Global", val fats:String? = "0", val kcal:String? = "0"
    , val price: String? = "0", val protein:String? = "0") {

}