package com.melscience

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.melscience.core.api.navigator.NavigatorHandler
import com.melscience.core.api.navigator.impl.ConductorNavigatorHandlerImpl
import com.melscience.presentation.main.ui.MainController
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin

class MainActivity : AppCompatActivity() {
  private lateinit var router: Router

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setupConductor(savedInstanceState)
  }

  private fun setupConductor(savedInstanceState: Bundle?) {
    router = Conductor.attachRouter(this, container, savedInstanceState)
    declareRouter()

    if (container != null && !router.hasRootController()) {
      router.setRoot(RouterTransaction.with(MainController()))
    }
  }

  override fun onBackPressed() {
    if (!router.handleBack()) {
      super.onBackPressed()
    }
  }

  private fun declareRouter() {
    getKoin().declare(
      instance = ConductorNavigatorHandlerImpl(router),
      secondaryTypes = listOf(NavigatorHandler::class),
      override = true
    )
  }
}
