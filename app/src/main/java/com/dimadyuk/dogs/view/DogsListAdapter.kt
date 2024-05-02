package com.dimadyuk.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dimadyuk.dogs.R
import com.dimadyuk.dogs.model.DogBreed
import com.dimadyuk.dogs.util.getProgressDrawable
import com.dimadyuk.dogs.util.loadImage

class DogsListAdapter(val dogsList: ArrayList<DogBreed>) : RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {
    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateDogsList(newDogsList: List<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogsList[position]

        val dogNameTextView: TextView = holder.view.findViewById(R.id.dogName)
        val lifespanTextView: TextView = holder.view.findViewById(R.id.lifespan)
        val dogImage: ImageView = holder.view.findViewById(R.id.dogImage)

        dogNameTextView.text = dog.dogBread
        lifespanTextView.text = dog.lifeSpan
        dogImage.loadImage(dog.imageUrl, getProgressDrawable(holder.view.context))

        holder.view.setOnClickListener { view ->
            dog.breedId?.let {
                Navigation.findNavController(view)
                    .navigate(ListFragmentDirections.actionListToDetail(it.toInt()))
            }
        }
    }
}
