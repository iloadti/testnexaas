package com.iloadti.testnexaas.presentation.purchaseList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iloadti.testnexaas.R
import com.iloadti.testnexaas.domain.entities.PurchaseListResponse
import com.iloadti.testnexaas.extension.formatForEUACurrency
import com.iloadti.testnexaas.presentation.itemDetail.view.DetailItemFragment
import com.iloadti.testnexaas.presentation.purchaseList.adapter.ItemClickListener
import com.iloadti.testnexaas.presentation.purchaseList.adapter.ListItemAdapter
import com.iloadti.testnexaas.presentation.purchaseList.state.HomeState
import com.iloadti.testnexaas.presentation.purchaseList.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.math.BigDecimal

class HomeFragment : Fragment(), ItemClickListener {

    val TAG = HomeFragment::class.java.simpleName

    var listPurchaseItem: List<PurchaseListResponse> = listOf()

    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()

        setupView()
    }

    private fun setupViewModel() {
        homeViewModel.run {
            homeState.observe(this@HomeFragment, Observer { state ->
                when (state) {
                    is HomeState.ShowPurchaseList -> showPurchaseList(state)
                    is HomeState.ShowError -> showError(state)
                }
            })
        }
    }

    private fun setupView() {
        listPurchaseItem.let {
            if (listPurchaseItem.isNotEmpty()) {
                txtVCountItems.text = listPurchaseItem.size.toString() + " Items in your cart"
                nSVPurchaseList.adapter = showPurchaseList(it)
            } else
                homeViewModel.fetchPurchaseList()
        }
    }

    private fun showPurchaseList(state: HomeState.ShowPurchaseList) {

        val listPurchaseList: List<PurchaseListResponse> = state.value

        listPurchaseList.let {

            configPurchaseList(listPurchaseList)

            nSVPurchaseList.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            nSVPurchaseList.run {
                adapter = showPurchaseList(it)
            }
        }
    }

    private fun configPurchaseList(listPurchaseList: List<PurchaseListResponse>) {
        txtVCountItems.text = listPurchaseList.size.toString() + " Items in your cart"


        var total = BigDecimal(0)
        var subtotal = BigDecimal(0)
        var shipping = BigDecimal(0)
        var tax = BigDecimal(0)

        listPurchaseList.forEach {
            subtotal = subtotal.add(BigDecimal(it.price.toString()).divide(BigDecimal("100")))
            shipping = shipping.add(BigDecimal(it.shipping.toString()).divide(BigDecimal("100")))
            tax = tax.add(BigDecimal(it.tax.toString()).divide(BigDecimal("100")))
        }

        txtVItemValue.text = subtotal.add(shipping).add(tax).formatForEUACurrency()
        txtVSubtotalValue.text = subtotal.formatForEUACurrency()
        txtVShippingValue.text = shipping.formatForEUACurrency()
        txtVTAXValue.text = tax.formatForEUACurrency()

    }

    private fun showError(state: HomeState.ShowError) {
        Toast.makeText(
            activity,
            "Error Connection",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showPurchaseList(listPurchaseListRespose: List<PurchaseListResponse?>):
            ListItemAdapter = ListItemAdapter(
        listPurchaseListRespose = listPurchaseListRespose,
        itemAction = this
    )

    private fun showItemDetail(purchaseListResponse: PurchaseListResponse) {
        val detailItemFragment = DetailItemFragment.newInstance(purchaseListResponse)


        val ft: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()

        ft.add(R.id.content, detailItemFragment)
        ft.addToBackStack(null);
        ft.commit()
    }

    fun addPurchaseList(listPurchaseListRespose: List<PurchaseListResponse?>) {
        listPurchaseItem = listPurchaseListRespose as List<PurchaseListResponse>
    }

    override fun onItemClickListener(purchaseListResponse: PurchaseListResponse) {
        showItemDetail(purchaseListResponse)
    }
}