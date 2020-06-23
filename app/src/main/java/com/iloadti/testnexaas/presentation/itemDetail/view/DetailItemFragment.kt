package com.iloadti.testnexaas.presentation.itemDetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.iloadti.testnexaas.R
import com.iloadti.testnexaas.domain.entities.PurchaseListResponse
import com.iloadti.testnexaas.extension.formatForEUACurrency
import com.iloadti.testnexaas.presentation.itemDetail.state.DetailItemState
import com.iloadti.testnexaas.presentation.itemDetail.viewModel.DetailItemViewModel
import kotlinx.android.synthetic.main.detail_item_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.math.BigDecimal

class DetailItemFragment : Fragment() {

    private val detailItemViewModel: DetailItemViewModel by sharedViewModel()

    private var purchaseListResponse: PurchaseListResponse? = null

    companion object {
        fun newInstance(purchaseListResponse: PurchaseListResponse): DetailItemFragment {

            val instance =
                DetailItemFragment()
            val args = Bundle()
            args.putSerializable("item", purchaseListResponse)
            instance.arguments = args
            return instance
        }
    }

    private lateinit var viewModel: DetailItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_item_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {
        val item = arguments?.getSerializable("item")

        item.let {
            purchaseListResponse = item as PurchaseListResponse?
        }


        detailItemViewModel.run {
            detailItemState.observe(this@DetailItemFragment, Observer { state ->
                when (state) {
                    is DetailItemState.ShowItemDetail -> showFillFields(state)
                }
            })
        }
    }

    private fun setupView() {
        purchaseListResponse?.let { fillFields(it) }
    }

    private fun showFillFields(state: DetailItemState.ShowItemDetail){
        val purchaseListResponse: PurchaseListResponse = state.value

        fillFields(purchaseListResponse)
    }

    private fun fillFields(purchaseListResponse: PurchaseListResponse){
        txtVName.text = purchaseListResponse?.name
        txtVQuantity.text = when {
            purchaseListResponse?.quantity ?: 0 > 1 -> {
                "in stock"
            }
            purchaseListResponse?.quantity == 1 -> {
                "only 1 left in stock"
            }
            else -> {
                "empty"
            }
        }

        val valueTest = BigDecimal(purchaseListResponse?.price.toString()).divide(BigDecimal("100"))


        txtVItemValue.text = valueTest.formatForEUACurrency()
        txtVDescription.text = purchaseListResponse.description

        imgItem.setImageURI(purchaseListResponse?.image_url)
    }

}