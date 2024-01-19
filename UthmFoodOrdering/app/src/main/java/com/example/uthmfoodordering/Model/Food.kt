package com.example.uthmfoodordering.Model

class Food {
    var Name: String = ""
    var Image: String = ""
    private var Description: String = ""
    private var Price: String = ""
    private var MeduId: String = ""

    constructor()
    constructor(Name: String, Image: String, Description: String, Price: String, MeduId: String) {
        this.Name = Name
        this.Image = Image
        this.Description = Description
        this.Price = Price
        this.MeduId = MeduId
    }
}