package com.dimadyuk.dogs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dimadyuk.dogs.model.DogBreed
import com.dimadyuk.dogs.model.DogDatabase
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : BaseViewModel(application) {
    val dogLiveData = MutableLiveData<DogBreed>()
    fun fetch(dogUuid: Int) {
        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(dogUuid)
            dogLiveData.value = dog
        }
    }
}
