package com.melscience.core.api.converter.impl

import com.google.gson.Gson
import com.melscience.core.api.converter.Converter
import java.lang.reflect.Type

/**
 * Created by ivasi on 09.06.2017.
 */
class GSonConverterImpl : Converter {
  private var gson: Gson = Gson()

  override fun to(any: Any?): String = gson.toJson(any)
  override fun <T> from(json: String?, type: Type?): T = gson.fromJson(json, type)
}