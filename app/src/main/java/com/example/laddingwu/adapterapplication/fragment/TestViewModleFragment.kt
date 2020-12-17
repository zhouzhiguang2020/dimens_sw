package com.example.laddingwu.adapterapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.laddingwu.adapterapplication.R
import com.example.laddingwu.adapterapplication.view.MyViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [TestViewModleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TestViewModleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    // Get a reference to the ViewModel scoped to this Fragment
    //activityViewModels获取的是 activity 的viewmodel
    val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_view_modle_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.testfun()

    }

}