package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.model.Capteur
import com.example.myapplication.model.Outil

class CapteurInfo {
    fun loadAoutils(): List<Capteur> {
        return listOf<Capteur>(
            Capteur(R.string.cap1, R.drawable.dht11,R.string.cap17),
            Capteur(R.string.cap2, R.drawable.ultra,R.string.cap18),
            Capteur(R.string.cap3, R.drawable.incendie,R.string.cap19),
            Capteur(R.string.cap4, R.drawable.mvm,R.string.cap20) ,
            Capteur(R.string.cap9, R.drawable.lightsensor,R.string.cap21),
        Capteur(R.string.cap10, R.drawable.pressure,R.string.cap22)
        )
    }
}