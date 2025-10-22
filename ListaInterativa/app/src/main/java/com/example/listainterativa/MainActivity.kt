package com.example.listainterativa
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listainterativa.ui.TaskAdapter
import com.example.listainterativa.ui.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TaskAdapter
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextTaskTitle = findViewById<EditText>(R.id.editTextTaskTitle)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTasks)

        adapter = TaskAdapter(
            onTaskClicked = { task -> viewModel.toggleTask(task) },
            onDeleteClicked = { task -> viewModel.deleteTask(task) }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.tasks.observe(this, Observer { newList ->
            adapter.submitList(newList)
        })

        buttonAdd.setOnClickListener {
            val title = editTextTaskTitle.text.toString().trim()
            if (title.isNotEmpty()) {
                viewModel.addTask(title)
                editTextTaskTitle.text.clear()
            }
        }
    }
}