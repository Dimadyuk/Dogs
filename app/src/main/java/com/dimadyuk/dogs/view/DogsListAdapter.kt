package com.dimadyuk.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dimadyuk.dogs.databinding.ItemDogBinding
import com.dimadyuk.dogs.model.DogBreed

class DogsListAdapter(val dogsList: ArrayList<DogBreed>) : RecyclerView.Adapter<DogsListAdapter.DogViewHolder>(),
    DogClickListener {

    fun updateDogsList(newDogsList: List<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.item_dog, parent, false)
        val view = ItemDogBinding.inflate(inflater, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dog = dogsList[position]
        holder.view.listener = this
        val dog = dogsList[position]
        holder.itemView.tag = dog

//
//        val dogNameTextView: TextView = holder.view.findViewById(R.id.dogName)
//        val lifespanTextView: TextView = holder.view.findViewById(R.id.lifespan)
//        val dogImage: ImageView = holder.view.findViewById(R.id.dogImage)
//
//        dogNameTextView.text = dog.dogBread
//        lifespanTextView.text = dog.lifeSpan
//        dogImage.loadImage(dog.imageUrl, getProgressDrawable(holder.view.context))
//
//        holder.view.setOnClickListener { view ->
//            val action = ListFragmentDirections.actionListToDetail(dog.uuid)
//            Navigation.findNavController(view).navigate(action)
//        }

    }

    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)

    override fun onDogClicked(v: View) {
        val dog = v.tag as? DogBreed
        val uuid = dog?.uuid
        uuid?.let {
            val action = ListFragmentDirections.actionListToDetail(uuid)
            Navigation.findNavController(v).navigate(action)
        }
    }
}
