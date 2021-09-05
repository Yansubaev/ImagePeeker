package edu.yans.imagepicker.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.yans.imagepicker.R
import edu.yans.imagepicker.asset.AssetListAdapter
import edu.yans.imagepicker.asset.AssetModel

class AssetPickerFragment : DialogFragment() {

    private lateinit var assetListRecycler: RecyclerView

    private lateinit var adapter: AssetListAdapter

    private var onImageSelectedListener: ((AssetModel?) -> Unit)? = null

    fun setOnImageSelectedListener(l: (AssetModel?) -> Unit){
        onImageSelectedListener = l
    }

    fun removeOnImageSelectedListener(){
        onImageSelectedListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_asset_picker, container, false)
        assetListRecycler = rootView.findViewById(R.id.rec_asset_list)
        assetListRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = AssetListAdapter(requireContext(), getAssetList())
        assetListRecycler.adapter = adapter

        adapter.setOnClickListener { i, assetModel ->
            onImageSelectedListener?.invoke(assetModel)
            dismiss()
        }

        return rootView
    }

    private fun getAssetList(): List<AssetModel> {
        val assets = requireActivity().assets
        val list = assets.list("Images")?.toList()

        val models: MutableList<AssetModel> = mutableListOf()

        list?.let { list ->
            for (asset in list) {
                val bytes = assets.open("Images/$asset").use { inputStream ->
                    inputStream.readBytes()
                }

                val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                models.add(AssetModel(asset, bmp))
            }
        }
        return models
    }
}