package com.melscience.core.api.resource

import androidx.annotation.*

interface ResourcesProvider {
  fun getString(@StringRes resId: Int): String
}