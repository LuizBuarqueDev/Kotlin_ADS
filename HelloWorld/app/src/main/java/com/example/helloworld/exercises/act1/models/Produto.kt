package com.example.helloworld.exercises.act1.models

data class Produto(var nome:String, var preco: Double, var cagoria: String) {
    fun aplicarDesconto(porcentagem: Double) {
        if (porcentagem in 0.0..100.0){
            preco -= preco * (porcentagem /100)
        }
    }
}