package com.esys.mvvmboilerplate.modules.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.esys.mvvmboilerplate.base.BaseActivity
import com.esys.mvvmboilerplate.base.BaseApplication
import com.esys.mvvmboilerplate.databinding.ActivityHomeBinding
import com.esys.mvvmboilerplate.utils.ViewModelFactory

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initViews() {

    }

    override fun setupEvents() {
        binding.btnHomeTryMe.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                homeViewModel.tryShowLoading()
            }
        }
    }

    override fun setupViewModel() {
        val app = this.application as BaseApplication
        homeViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(app.apiHelper, app.preferenceHelper, app.dbHelper)
        ).get(HomeViewModel::class.java)
    }

    override fun observeViewModel() {
        //Observe Loading
        homeViewModel.loading.observe(this, { isLoading ->
            if (isLoading) {
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        })
    }
}