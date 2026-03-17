package com.example.calculadora_imc

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_results)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var person: MainActivity.Person? = null
        val bundle = intent.extras
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                person = bundle.getParcelable("person", MainActivity.Person::class.java)
            } else{
                person = bundle.getParcelable("person")
            }
        }
        if (person == null) return
        val outputView = findViewById<TextView>(R.id.outputView)
        val imc = calculateImc(person)
        println(imc)

        when(imc){
            in 0.0..15.0 -> outputView.text = resultSet["15"]
            in 15.1..16.0 -> outputView.text = resultSet["16"]
            in 16.1..18.5 -> outputView.text = resultSet["18,5"]
            in 18.6..25.0 -> outputView.text = resultSet["25"]
            in 25.1..30.0 -> outputView.text = resultSet["30"]
            in 30.1..35.0 -> outputView.text = resultSet["35"]
            in 35.1..40.0 -> outputView.text = resultSet["40"]
            else -> outputView.text = "Super Obesity"
        }

    }

    val resultSet = hashMapOf(
        "15" to "Starving",
        "16" to "a Litte Under Weight",
        "18,5" to "Normal Weight",
        "25" to "Over Weight",
        "30" to "Obesity 1",
        "35" to "Obesity 2",
        "40" to "Obesity 3"
    )

    fun calculateImc(person: MainActivity.Person): Double{
        return person.weight / (person.height * person.height)
    }
}