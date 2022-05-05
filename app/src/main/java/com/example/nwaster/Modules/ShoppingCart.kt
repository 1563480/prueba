package com.example.nwaster.Modules

import java.util.ArrayList

class ShoppingCart {
    var shoppingCartList: Map<String, ArrayList<Product>> = mutableMapOf()

    fun addProduct(product: Product){

        if (this.shoppingCartList.containsKey(product.category.toString())){
            val productList  = this.shoppingCartList[product.category.toString()]
            productList?.add(product)
        }
        else{
            val productList= ArrayList<Product>()
            productList.add(product)
            (this.shoppingCartList as MutableMap<String, ArrayList<Product>>)[product.category.toString()] =productList
        }
    }
}