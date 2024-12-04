package com.example.roomhw3

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomhw3.databinding.ListItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class PersonAdapter(context: Context, private val listener: PersonClickListener) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    var personList = mutableListOf<Person>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Person>){
        personList.addAll(personList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonViewHolder {

        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.itemIconDeleteIV.setOnClickListener{
            listener.onItemClicked(personList[PersonViewHolder(binding).adapterPosition])
        }

        return PersonViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: PersonViewHolder,
        position: Int
    ) {
        val person = personList[position]
        holder.bind(person)
    }

    override fun getItemCount() = personList.size


    class PersonViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            @SuppressLint("SetTextI18n")
            fun bind(person: Person){

                binding.itemNameTV.text = person.name
                binding.itemPhoneTV.text = person.phone
                binding.itemIconDeleteIV.setImageResource(R.drawable.ic_delete_24)
                binding.createTimeTV.text = person.time.toString().asTime()

            }
    }

    fun addItem(item: Person, onSuccess: () -> Unit) {
        if (!personList.contains(item)) {
            personList.add(item)
            personList.sortBy { it.name }
            notifyItemInserted(0)
        }
        onSuccess
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: MutableList<Person>){
        personList = newList
        personList.sortBy { it.name }
        notifyDataSetChanged()
    }

    interface PersonClickListener{
        fun onItemClicked(person: Person)
    }




}

private fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}
