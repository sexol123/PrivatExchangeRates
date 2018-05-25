package vn.oleksandr.sandul.privatexchangerates.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import vn.oleksandr.sandul.privatexchangerates.R
import vn.oleksandr.sandul.privatexchangerates.databinding.FragmentMainBinding
import vn.oleksandr.sandul.privatexchangerates.ui.adapter.MainAdapter


class MainFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding
    private lateinit var adapter : MainAdapter
    private lateinit var viewModel : MainViewModel

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.recycler.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter()
        binding.recycler.adapter = adapter

        viewModel.currencyLiveData.observe(this, Observer<List<CurrencyModel>> {
            binding.progress.visibility = View.VISIBLE
            adapter.setDate(it)
            binding.progress.visibility = View.INVISIBLE
        })

        viewModel.errorLiveData.observe(this, Observer<Throwable> {
            Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }
}