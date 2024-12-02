package com.herdialfachri.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.herdialfachri.myapplication.adapter.ProductAdapter
import com.herdialfachri.myapplication.data.Product

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rvProduk)

        // Set GridLayoutManager dengan jumlah kolom 2
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Mendapatkan data dari string resources
        val productNames = resources.getStringArray(R.array.data_name)
        val productDescriptions = resources.getStringArray(R.array.data_description)
        val productImages = resources.obtainTypedArray(R.array.data_photo)

        // Mengubah data ke dalam list produk
        val productList = productNames.mapIndexed { index, name ->
            Product(name, productDescriptions[index], productImages.getResourceId(index, -1))
        }

        // Mengatur adapter dengan data dari string resources
        recyclerView.adapter = ProductAdapter(this, productList)

        productImages.recycle()

        // Tambahkan OnClickListener untuk TextView dengan ID tvFavicon
//        val tvFavicon: TextView = findViewById(R.id.tvFavicon)
//        tvFavicon.setOnClickListener {
//            val intent = Intent(this@MainActivity, ProductActivity::class.java)
//            startActivity(intent)
//        }
    }
}
