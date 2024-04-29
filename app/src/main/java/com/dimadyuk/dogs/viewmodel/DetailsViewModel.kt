package com.dimadyuk.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimadyuk.dogs.model.DogBreed

class DetailsViewModel : ViewModel() {
    val dogLiveData = MutableLiveData<DogBreed>()
    fun fetch() {
        val dog = DogBreed(
            "1",
            "Corgi",
            "12 years",
            "Herding",
            "Sheep",
            "Bold, Friendly, Outgoing",
            "https://images.dog.ceo/breeds/corgi-cardigan/n02113186_10116.jpg"
        )
        dogLiveData.value = dog
    }
}