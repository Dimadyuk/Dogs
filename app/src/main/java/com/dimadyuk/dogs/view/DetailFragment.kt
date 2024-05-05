package com.dimadyuk.dogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dimadyuk.dogs.databinding.FragmentDetailBinding
import com.dimadyuk.dogs.util.getProgressDrawable
import com.dimadyuk.dogs.util.loadImage
import com.dimadyuk.dogs.viewmodel.DetailsViewModel

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var dogUuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
            viewModel.fetch(dogUuid)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(viewLifecycleOwner) { dog ->
            with(binding) {
                dog?.let {
                    dogName.text = dog.dogBread
                    dogPurpose.text = dog.breedFor
                    dogTemperament.text = dog.temperament
                    dogLifespan.text = dog.lifeSpan
                    dogImage.loadImage(dog.imageUrl, getProgressDrawable(dogImage.context))
                }
            }
        }
    }
}
