package com.iloadti.testnexas.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.iloadti.testnexas.R
import com.iloadti.testnexas.domain.entities.PurchaseListResponse
import com.iloadti.testnexas.presentation.main.state.SignState
import com.iloadti.testnexas.presentation.main.viewmodel.SignViewModel
import com.iloadti.testnexas.presentation.purchaseList.adapter.ListItemAdapter
import com.iloadti.testnexas.presentation.purchaseList.view.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val signViewModel: SignViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle("Purchase List")

        setupViewModel()
        setupView()
//        signViewModel.fetchPurchaseList()
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

    private fun showPurchaseList(listPurchaseListRespose: List<PurchaseListResponse?>) :
            ListItemAdapter = ListItemAdapter(listPurchaseListRespose = listPurchaseListRespose,
        onItemSelect = {
            showItemDetail(it)
        })


    private fun showItemDetail(purchaseListResponse: PurchaseListResponse){

    }


    private fun showError(state: SignState.ShowError){
        Log.e("Error Connection: ", state.toString())
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

}