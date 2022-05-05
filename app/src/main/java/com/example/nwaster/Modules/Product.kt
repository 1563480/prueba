package com.example.nwaster.Modules

class Product {
    var name:String? = ""
    var barCode:String? = "0"
    var carbohydrates:String? = "0"
    var category:String? = "Global"
    var fats:String? = "0"
    var kcal:String? = "0"
    var price: String? = "0"
    var protein:String? = "0"
    var expand:Boolean=false

    constructor()
    constructor(name: String,barCode : String, carbo:String,categor:String, fats:String, kcal:String, price:String, protein: String ) {
        this.name = name
        this.barCode = barCode
        this.carbohydrates = carbo
        this.category = categor
        this.fats = fats
        this.kcal = kcal
        this.price = price
        this. protein = protein
    }
}