package com.melscience.core.api.error

interface ErrorHandler {
  fun getErrorMessage(throwable: Throwable): String
}