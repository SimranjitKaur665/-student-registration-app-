package com.example.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasedemo.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("User")

        binding.button.setOnClickListener {
            val id = binding.id.text.toString()
            val name = binding.name.text.toString()
            val age = binding.age.text.toString()
            val address = binding.address.text.toString()
            val email = binding.email.text.toString()

            val user1 = User(id, name, age, address, email)
            database.child(id).setValue(user1).addOnSuccessListener {
                binding.id.text.clear()
                binding.name.text.clear()
                Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Ops... Something went wrong.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}