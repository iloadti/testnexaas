package com.iloadti.testnexaas.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.iloadti.testnexaas.R
import com.iloadti.testnexaas.domain.entities.PurchaseListResponse
import com.iloadti.testnexaas.presentation.itemDetail.view.DetailItemFragment
import com.iloadti.testnexaas.presentation.main.state.SignState
import com.iloadti.testnexaas.presentation.main.viewmodel.SignViewModel
import com.iloadti.testnexaas.presentation.purchaseList.adapter.ItemClickListener
import com.iloadti.testnexaas.presentation.purchaseList.view.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val signViewModel: SignViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle("Purchase List")

        setupViewModel()
        setupView()
    }

    private fun setupView(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, HomeFragment(), "HomeFragment")
            .commit()
    }

    private fun setupViewModel() {
        signViewModel.run {
            signState.observe(this@MainActivity, Observer { state ->
                when (state) {
                    is SignState.ShowPurchaseList -> showPurchaseList(state)
                    is SignState.ShowError -> showError(state)
                }
            })
        }
    }

    private fun showPurchaseList(state: SignState.ShowPurchaseList){
        Log.e("OK Connection: ", state.toString())

        val listPurchaseList : List<PurchaseListResponse> = state.value

        val regcomplainfragment = HomeFragment()
        regcomplainfragment.listPurchaseItem = listPurchaseList

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.content, regcomplainfragment)
        ft.commit()
    }

    private fun showItemDetail(purchaseListResponse: PurchaseListResponse){
        val detailItemFragment = DetailItemFragment.newInstance(purchaseListResponse)

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.add(R.id.content, detailItemFragment)
        ft.commit()
    }


    private fun showError(state: SignState.ShowError){
        Toast.makeText(
            this,
            "Error Connection",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onItemClickListener(purchaseListResponse: PurchaseListResponse) {
        showItemDetail(purchaseListResponse)
    }

}