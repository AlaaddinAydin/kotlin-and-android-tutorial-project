package com.aydin.dndclassesproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aydin.dndclassesproject.databinding.RecyclerRowBinding
import com.aydin.dndclassesproject.model.Class
import com.aydin.dndclassesproject.view.ClassListFragmentDirections
import com.aydin.dndclassesproject.viewmodel.ListFragmentViewModel

class ListFragmentRecyclerViewAdapter(val classList : ArrayList<Class>) : RecyclerView.Adapter<ListFragmentRecyclerViewAdapter.listFragmentViewHolder>() {

    class listFragmentViewHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listFragmentViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return listFragmentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return classList.size
    }

    fun updateList(newList: List<Class>){
        classList.clear()
        classList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: listFragmentViewHolder, position: Int) {
        holder.binding.recyclerRowName.text = classList[position].isim
        holder.binding.recyclerRowHit.text = classList[position].hitDice

        holder.itemView.setOnClickListener {
            val action = ClassListFragmentDirections.actionClassListFragmentToClassDetailFragment(classList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }


}