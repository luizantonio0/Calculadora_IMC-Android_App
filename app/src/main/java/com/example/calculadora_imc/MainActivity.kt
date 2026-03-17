package com.example.calculadora_imc

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.Serializable
import kotlinx.parcelize.Parcelize
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @Parcelize
    data class Person(val name: String, val height: Double, val weight: Double): Parcelable


    fun onSave(view: View){
        val name = findViewById<EditText>(R.id.name).text.toString()
        val height = findViewById<EditText>(R.id.height).text.toString().toDouble()
        val weight = findViewById<EditText>(R.id.weight).text.toString().toDouble()

        val person = Person(name, height, weight)

        val intent = Intent(this, results::class.java)
        intent.putExtra("person", person)
        startActivity(intent)

    }
}