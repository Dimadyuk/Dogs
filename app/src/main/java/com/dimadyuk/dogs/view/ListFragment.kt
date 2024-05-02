package com.dimadyuk.dogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimadyuk.dogs.databinding.FragmentListBinding
import com.dimadyuk.dogs.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val dogsListAdapter: DogsListAdapter = DogsListAdapter(arrayListOf())

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.refresh()

        binding.dogsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }
        binding.refreshLayout.setOnRefreshListener {
            binding.dogsList.visibility = View.GONE
            binding.listError.visibility = View.GONE
            binding.loadingView.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogs.observe(viewLifecycleOwner) { dogs ->
            dogs?.let {
                binding.dogsList.visibility = View.VISIBLE
                dogsListAdapter.updateDogsList(dogs)
            }
        }
        viewModel.dogsLoadError.observe(viewLifecycleOwner) { isError ->
            isError?.let {
                binding.listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                    binding.dogsList.visibility = View.GONE
                }
            }
        }
    }
}
