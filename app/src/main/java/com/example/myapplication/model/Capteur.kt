package com.example.myapplication.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Capteur (@StringRes val stringResourceId: Int,
                    @DrawableRes val imageResourceId: Int,
                    @StringRes val stringResourcePrix: Int,

    )