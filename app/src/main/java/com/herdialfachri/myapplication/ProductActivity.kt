package com.herdialfachri.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView
import android.widget.TextView
import com.herdialfachri.myapplication.data.Product

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar_detail)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this@ProductActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Mengambil data dari Intent
        val product: Product? = intent.getParcelableExtra("product")

        // Mengatur tampilan dengan data produk
        val productImageView: ImageView = findViewById(R.id.productImage)
        val productNameTextView: TextView = findViewById(R.id.productName)
        val productDescriptionTextView: TextView = findViewById(R.id.productDescription)

        product?.let {
            productImageView.setImageResource(it.photo)
            productNameTextView.text = it.name
            productDescriptionTextView.text = it.description
        }
    }
}
