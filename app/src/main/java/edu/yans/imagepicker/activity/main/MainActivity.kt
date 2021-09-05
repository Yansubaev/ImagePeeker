package edu.yans.imagepicker.activity.main

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import edu.yans.imagepicker.R
import edu.yans.imagepicker.asset.AssetModel
import edu.yans.imagepicker.fragment.AssetPickerFragment

class MainActivity : AppCompatActivity() {

    private lateinit var image: ImageView

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.img_image)

        viewModel.image.observe(this, { model ->
            image.setImageBitmap(model.thumbnail)
        })

        image.setOnClickListener {
            AssetPickerFragment()
                .apply {
                    this.setOnImageSelectedListener {
                        setImageFromAssets(it)
                    }
                }.show(supportFragmentManager, "Picker")
        }
    }

    private fun setImageFromAssets(model: AssetModel?) {
        model?.let { model ->
            viewModel.setModel(model)
        }
    }
}