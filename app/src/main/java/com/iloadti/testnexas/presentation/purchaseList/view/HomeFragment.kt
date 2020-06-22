package com.iloadti.testnexas.presentation.purchaseList.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iloadti.testnexas.R
import com.iloadti.testnexas.domain.entities.PurchaseListResponse
import com.iloadti.testnexas.presentation.purchaseList.adapter.ListItemAdapter
import com.iloadti.testnexas.presentation.purchaseList.state.HomeState
import com.iloadti.testnexas.presentation.purchaseList.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

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

    private fun setupView(){
        listPurchaseItem.let {
            if (listPurchaseItem.isNotEmpty())
                nSVPurchaseList.adapter = showPurchaseList(it)
            else
                homeViewModel.fetchPurchaseList()
        }
    }

    private fun showPurchaseList(state: HomeState.ShowPurchaseList){

        val listPurchaseList : List<PurchaseListResponse> = state.value

        listPurchaseList.let {
            nSVPurchaseList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            nSVPurchaseList.run {
                adapter = showPurchaseList(it)
            }
        }
    }

    private fun showError(state: HomeState.ShowError){
        Log.e("Error Connection: ", state.toString())
    }

    private fun showPurchaseList(listPurchaseListRespose: List<PurchaseListResponse?>) :
            ListItemAdapter = ListItemAdapter(listPurchaseListRespose = listPurchaseListRespose,
                                                onItemSelect = {
                                                    showItemDetail(it)
                                                })

    private fun showItemDetail(purchaseListResponse: PurchaseListResponse){
        
    }

    fun addPurchaseList(listPurchaseListRespose: List<PurchaseListResponse?>){
        listPurchaseItem = listPurchaseListRespose as List<PurchaseListResponse>
    }
}