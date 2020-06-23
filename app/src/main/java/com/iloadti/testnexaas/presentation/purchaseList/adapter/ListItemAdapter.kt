package com.iloadti.testnexaas.presentation.purchaseList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.iloadti.testnexaas.R
import com.iloadti.testnexaas.domain.entities.PurchaseListResponse
import com.iloadti.testnexaas.extension.formatForEUACurrency
import java.math.BigDecimal

internal class ListItemAdapter(
    private val listPurchaseListRespose: List<PurchaseListResponse?>,
    private val itemAction: ItemClickListener
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

        with(holder) {
            name.text = itemCustom?.name
            description.text = when {
                itemCustom?.quantity ?: 0 > 1 -> {
                    "in stock"
                }
                itemCustom?.quantity == 1 -> {
                    "only 1 left in stock"
                }
                else -> {
                    "empty"
                }
            }
            val valueTest = BigDecimal(itemCustom?.price.toString()).divide(BigDecimal("100"))

            itemValue.text = valueTest.formatForEUACurrency()

            imageItem.setImageURI(itemCustom?.image_url)

            holder.itemView.setOnClickListener {
                itemCustom?.let { it1 -> itemAction.onItemClickListener(it1) }
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemSelect: View = view.findViewById<ConstraintLayout>(R.id.itemSelect)
        val name: TextView = view.findViewById<TextView>(R.id.txtVName)
        val description: TextView = view.findViewById<TextView>(R.id.txtVDescription)
        val itemValue: TextView = view.findViewById<TextView>(R.id.txtVItemValue)
        val imageItem: SimpleDraweeView = view.findViewById(R.id.imgItem)
    }
}