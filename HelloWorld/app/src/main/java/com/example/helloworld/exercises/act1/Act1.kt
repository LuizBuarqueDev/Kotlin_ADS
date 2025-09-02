package com.example.helloworld.exercises.act1

import com.example.helloworld.exercises.act1.models.Produto

class Act1 {
    companion object {
        fun maiorDeTres(a: Int, b: Int, c: Int): Int {
            return maxOf(a, b, c)
        }

        fun String.inverterEVogais(): String {
            val invertida = this.reversed()
            return invertida.replace(Regex("[aeiouAEIOU]"), "*")
        }

        @JvmStatic
        fun main(args: Array<String>) {
            //1)
            println(maiorDeTres(10, 5, 8)) // 10
            val lista = listOf(3, 7, 2, 9, 5)
            val maior = lista.reduce { acc, elem -> maiorDeTres(acc, elem, Int.MIN_VALUE) }
            println(maior)

            //2)
            val p = Produto("Notebook", 3000.0, "Eletrônicos")
            p.aplicarDesconto(10.0)
            println(p) // preco = 2700.0

            //3)
            val nomes = listOf("Ana", "alice", "André", "João", "amanda", "Al")
            val filtrados = nomes
                .filter { it.length > 3 && it.startsWith("A", ignoreCase = true) }
                .map { it.uppercase() }
            println(filtrados) // [ALICE, ANDRÉ, AMANDA]

            //4)
            println("Luiz".inverterEVogais())
        }
    }
}