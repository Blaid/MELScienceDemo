package com.melscience.core.api.resource.impl

import android.content.Context
import androidx.annotation.StringRes
import com.melscience.core.api.resource.ResourcesProvider

class AndroidResourcesProvider(private val context: Context) : ResourcesProvider {
  override fun getString(@StringRes resId: Int) = context.getString(resId)
}