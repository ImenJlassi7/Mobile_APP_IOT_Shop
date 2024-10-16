package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.model.Outil

class Source() {
    fun loadAoutils(): List<Outil> {
        return listOf<Outil>(
            Outil(R.string.outil1, R.drawable.ras,R.string.outil1txt,R.string.cap13),
            Outil(R.string.outil2, R.drawable.esp32,R.string.outil2txt,R.string.cap14),
            Outil(R.string.outil3, R.drawable.bb,R.string.outil3txt,R.string.cap15),
            Outil(R.string.outil4, R.drawable.arduino,R.string.outil4txt,R.string.cap16)
        )
    }
}
