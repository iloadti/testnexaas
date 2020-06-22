package com.iloadti.testnexas.presentation.purchaseList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.iloadti.testnexas.R
import com.iloadti.testnexas.domain.entities.PurchaseListResponse

internal class ListItemAdapter(
        private val listPurchaseListRespose: List<PurchaseListResponse?>,
        private val onItemSelect: (PurchaseListResponse) -> Unit
) : RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_custom_list, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPurchaseListRespose.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemCustom = listPurchaseListRespose[position]

        with(holder){
            name.text = itemCustom?.name
            description.text = itemCustom?.description
            itemValue.text = itemCustom?.price.toString()
            imageItem.setImageURI(itemCustom?.image_url)


        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById<TextView>(R.id.txtVName)
        val description: TextView = view.findViewById<TextView>(R.id.txtVDescription)
        val itemValue: TextView = view.findViewById<TextView>(R.id.txtVItemValue)
        val imageItem: SimpleDraweeView = view.findViewById(R.id.imgItem)
    }
}