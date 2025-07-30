package com.example.cafe_project.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.cafe_project.Activity.ViewModel.MainViewModel
import com.example.cafe_project.Adapter.ItemListCategoryAdapter
import com.example.cafe_project.R
import com.example.cafe_project.databinding.ActivityItemListBinding
import java.util.ResourceBundle.getBundle

class ItemListActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemListBinding
    private val viewModel = MainViewModel()
    private var id : String =""
    private var title : String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()

    }

    private fun initList() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            viewModel.loadItems(id).observe(this@ItemListActivity, Observer {
                listView.layoutManager = LinearLayoutManager(this@ItemListActivity,
                    LinearLayoutManager.VERTICAL, false)
                listView.adapter = ItemListCategoryAdapter(it)
                progressBar.visibility = View.GONE
            })
            backBtn.setOnClickListener {
                finish()
            }
        }
    }

    private fun getBundle() {
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!

        binding.categoryTxt.text = title
    }
}