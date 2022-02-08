package com.ademkayaaslan.telaffuzluzikirmatik.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ademkayaaslan.telaffuzluzikirmatik.R
import com.ademkayaaslan.telaffuzluzikirmatik.viewmodel.DhikrViewModel

class DhikrFragment : Fragment() {

    companion object {
        fun newInstance() = DhikrFragment()
    }

    private lateinit var viewModel: DhikrViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dhikr_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DhikrViewModel::class.java)
        // TODO: Use the ViewModel
    }

}