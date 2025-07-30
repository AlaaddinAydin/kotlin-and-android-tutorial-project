package com.aydin.dndclassesproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aydin.dndclassesproject.R
import com.aydin.dndclassesproject.adapter.ListFragmentRecyclerViewAdapter
import com.aydin.dndclassesproject.databinding.FragmentClassListBinding
import com.aydin.dndclassesproject.service.ClassAPIService
import com.aydin.dndclassesproject.viewmodel.ListFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClassListFragment : Fragment() {
    private var _binding: FragmentClassListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListFragmentViewModel
    private var adapter = ListFragmentRecyclerViewAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClassListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListFragmentViewModel::class.java]
        viewModel.getDataFromRoom()

        binding.RecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.RecyclerView.adapter = adapter

        binding.SwipeRefreshLayout.setOnRefreshListener {
            viewModel.getDataFromInternetAndSaveToRoom()
            binding.SwipeRefreshLayout.isRefreshing= false
        }

        observeData()
    }

    private fun observeData(){
        viewModel.classLiveData.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}