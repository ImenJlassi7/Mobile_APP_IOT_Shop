package com.example.myapplication.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Outil (@StringRes val stringResourceId: Int,
             @DrawableRes val imageResourceId: Int,
                  @StringRes val stringResourceText: Int,
                  @StringRes val stringResourcePrix: Int
)