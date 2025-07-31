package com.example.cafe_project.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.bumptech.glide.Glide
import com.example.cafe_project.Activity.Domain.ItemsModel
import com.example.cafe_project.Activity.common.CoffeeSize
import com.example.cafe_project.R
import com.example.cafe_project.databinding.ActivityDetailBinding
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managementCart : ManagmentCart
    private var selectedCoffeeSize: String = CoffeeSize.SMALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        managementCart = ManagmentCart(this)

        bundle()
        initSizeList()

    }

    private fun initSizeList() {
        binding.apply {
            // Set default to SMALL
            smallBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
            mediumBtn.setBackgroundResource(0)
            largeBtn.setBackgroundResource(0)

            smallBtn.setOnClickListener {
                selectedCoffeeSize = CoffeeSize.SMALL
                smallBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(0)
            }

            mediumBtn.setOnClickListener {
                selectedCoffeeSize = CoffeeSize.MEDIUM
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                largeBtn.setBackgroundResource(0)
            }

            largeBtn.setOnClickListener {
                selectedCoffeeSize = CoffeeSize.LARGE
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
            }
        }
    }


    private fun bundle() {
        binding.apply {
            item = intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity).load(item.picUrl[0]).into(binding.picMain)

            titleTxt.text = item.title
            descriptionTxt.text = item.description
            priceTxt.text = "$"+item.price
            ratingTxt.text = item.rating.toString()


            addToCartBtn.setOnClickListener {
                item.numberInCart = Integer.valueOf(
                    numberItemTxt.text.toString()
                )
                managementCart.insertItems(item)
            }

            backBtn.setOnClickListener {
                startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            }

            plusCart.setOnClickListener {
                numberItemTxt.text = (item.numberInCart + 1 ).toString()
                item.numberInCart++
            }

            minusBtn.setOnClickListener {
                if(item.numberInCart > 1){
                    numberItemTxt.text = (item.numberInCart - 1).toString()
                    item.numberInCart--
                }
            }

        }
    }
}