package com.dimadyuk.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimadyuk.dogs.model.DogBreed

class ListViewModel : ViewModel() {
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val dog1 = DogBreed(
            "1",
            "Corgi",
            "12 years",
            "Herding",
            "Sheep",
            "Bold, Friendly, Outgoing",
            "https://images.dog.ceo/breeds/corgi-cardigan/n02113186_10116.jpg"
        )
        val dog2 = DogBreed(
            "2",
            "Labrador",
            "12 years",
            "Sporting",
            "Water",
            "Kind, Outgoing, Agile",
            "https://images.dog.ceo/breeds/labrador/n02099712_1012.jpg"
        )
        val dog3 = DogBreed(
            "3",
            "Husky",
            "12 years",
            "Working",
            "Sled",
            "Outgoing, Alert, Gentle",
            "https://images.dog.ceo/breeds/husky/n02110185_1011.jpg"
        )
        val dogList = arrayListOf(dog1, dog2, dog3)

        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }
}