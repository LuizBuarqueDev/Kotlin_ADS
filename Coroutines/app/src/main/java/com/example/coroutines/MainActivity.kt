package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val activityScope = CoroutineScope(Dispatchers.Main + Job())

    private lateinit var resultText: TextView
    private lateinit var buttonTask1: Button
    private lateinit var buttonTask2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.resultText)
        buttonTask1 = findViewById(R.id.buttonTask1)
        buttonTask2 = findViewById(R.id.buttonTask2)

        buttonTask1.setOnClickListener {
            activityScope.launch {
                resultText.text = "Buscando dados..."
                val data = fetchDataFromNetwork()
                resultText.text = "Resultado da rede: $data"
            }
        }

        buttonTask2.setOnClickListener {
            activityScope.launch {
                resultText.text = "Calculando números..."
                val numbers = simulateHeavyCalculation()
                resultText.text = "Soma dos números: $numbers"
            }
        }
    }
    private suspend fun fetchDataFromNetwork(): String = withContext(Dispatchers.IO) {
        delay(2000)
        val dataList = listOf("Usuário A", "Usuário B", "Usuário C")
        dataList.random()
    }
    private suspend fun simulateHeavyCalculation(): Int = withContext(Dispatchers.Default) {
        delay(3000)
        val numbers = List(1_000_000) { Random.nextInt(0, 100) }
        numbers.sum()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }
}