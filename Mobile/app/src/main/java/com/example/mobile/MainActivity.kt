package com.example.mobile

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btn1.setOnClickListener {
            replaceFragment(Fragment1())
        }

        binding.btn2.setOnClickListener {
            replaceFragment(Fragment2())
        }

        binding.btn3.setOnClickListener {
            replaceFragment(Fragment3())
        }

        val Bundle = Bundle().apply {

        }
    }
   private fun replaceFragment(fragment: Fragment) {
       val fragmentManager = supportFragmentManager
       val fragmentTransaction = fragmentManager.beginTransaction()
       fragmentTransaction.replace(R.id.fragment_container,fragment)
       fragmentTransaction.commit()
    }

    private fun callForMe () {
        var uri = Uri.parse("tel:+5581996346840")
        val intent = Intent(Intent.ACTION_CALL,uri)
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }
}