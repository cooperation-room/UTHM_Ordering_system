package com.example.uthmfoodordering.Model

class Category {
    var name: String = ""
    var image: String = ""

    constructor()
    constructor(name: String, image: String) {
        this.name = name
        this.image = image
    }
}
