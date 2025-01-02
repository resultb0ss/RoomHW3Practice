package com.example.roomhw3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomhw3.databinding.ListItemBinding


class PersonAdapter(val onItemClick: (item: Person) -> Unit) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    var personList = mutableListOf<Person>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Person>) {
        personList.clear()
        personList.addAll(newList)
        notifyItemInserted(0)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonViewHolder {

        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PersonViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: PersonViewHolder,
        position: Int
    ) {
        val person = personList[position]
        holder.binding.itemNameTV.text = person.name
        holder.binding.itemPhoneTV.text = person.phone
        holder.binding.itemIconDeleteIV.setImageResource(R.drawable.ic_delete_24)
//            binding.createTimeTV.text = person.time.toString().asTime()
        holder.itemView.setOnClickListener {
            onItemClick(person)
        }
    }

    override fun getItemCount() = personList.size


    class PersonViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


}

//private fun String.asTime(): String {
//    val time = Date(this.toLong())
//    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
//    return timeFormat.format(time)
//}
