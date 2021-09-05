package edu.yans.imagepicker.activity.main

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.yans.imagepicker.asset.AssetModel

class MainViewModel : ViewModel() {

    private val _image: MutableLiveData<AssetModel> = MutableLiveData()
    val image: LiveData<AssetModel>
        get() = _image

    fun setModel(assetModel: AssetModel){
        _image.value = assetModel
    }

}