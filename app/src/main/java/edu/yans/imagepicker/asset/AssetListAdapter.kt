package edu.yans.imagepicker.asset

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.yans.imagepicker.R

class AssetListAdapter(
    private val context: Context,
    private val models: List<AssetModel>
    ) : RecyclerView.Adapter<AssetListViewHolder>() {

    private var onClickListener: ((Int, AssetModel) -> Unit)? = null

    fun setOnClickListener(l: (Int, AssetModel) -> Unit){
        onClickListener = l
    }

    fun removeOnClickListener(){
        onClickListener = null
    }

    override fun getItemCount(): Int = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetListViewHolder =
        AssetListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_asset_model, parent, false))

    override fun onBindViewHolder(holder: AssetListViewHolder, position: Int) {
        holder.innitViews(models[position])
        holder.rootView.setOnClickListener { onClickListener?.invoke(position, models[position]) }
    }
}

class AssetListViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView){

    private val thumbnail: ImageView = rootView.findViewById(R.id.img_thumbnail)

    private val name: TextView = rootView.findViewById(R.id.txt_name)

    @SuppressLint("SetTextI18n")
    fun innitViews(model: AssetModel){
        name.text = model.name
        thumbnail.setImageBitmap(model.thumbnail)
    }



}