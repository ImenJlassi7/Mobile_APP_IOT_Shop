package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.model.Composant

class ComposantInfo {
    fun loadAoutils(): List<Composant> {
        return listOf<Composant>(
            Composant(R.string.cap5, R.drawable.led,R.string.cap23),
            Composant(R.string.cap6, R.drawable.sonner,R.string.cap24),
            Composant(R.string.cap7, R.drawable.resistor,R.string.cap25),
            Composant(R.string.cap8, R.drawable.transistor,R.string.cap26),
            Composant(R.string.cap11, R.drawable.servo,R.string.cap27),
            Composant(R.string.cap12, R.drawable.pot,R.string.cap28)
        )
    }
}