package com.example.roomhw3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomhw3.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class MainActivity : AppCompatActivity(), PersonAdapter.PersonClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    private lateinit var viewModel: PersonViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PersonAdapter(this, this)
        binding.mainActivityRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[PersonViewModel::class.java]

        viewModel.persons.observe(this, { list -> list?.let {
            Log.d("@@@","viewModel observer ${list}")
            adapter.updateList(it) }})


    }


    override fun onResume() {
        super.onResume()
        initFields()
    }

    override fun onItemClicked(person: Person) {
        viewModel.deletePerson(person)
        Toast.makeText(this, "Контакт удален", Toast.LENGTH_LONG).show()
    }

    private fun initFields() {
        binding.mainActivitySaveButtonBTN.setOnClickListener {
            saveData()
        }
    }


    fun saveData() {
        val personName = binding.mainActivityNameEditTextET.text.toString()
        val phone = binding.mainActivityPhoneEditTextET.text.toString()
        val  timeStamp = formatMillisecond(Date().time)


        if (personName.isNotEmpty() && phone.isNotEmpty()) {
            viewModel.insertPerson(Person(name = personName, phone = phone, time = timeStamp))
            Toast.makeText(this, "Контакт добавлен", Toast.LENGTH_SHORT).show()
        }

        binding.mainActivityNameEditTextET.text.clear()
        binding.mainActivityPhoneEditTextET.text.clear()
    }

    private fun formatMillisecond(time: Long): String{
        val timeFormat = SimpleDateFormat("EEE, HH:mm")
        timeFormat.timeZone = TimeZone.getTimeZone("GMT+04")
        return timeFormat.format(Date(time))
    }


}