package com.melscience.common.koin

import com.bluelinelabs.conductor.Controller
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.scope.Scope
import org.koin.ext.getFullName
import java.lang.System.identityHashCode

private fun Controller.getScopeName() = TypeQualifier(this::class)

private fun Controller.getScopeId() = this::class.getFullName() + "@" + identityHashCode(this)

private fun Controller.getOrCreateCurrentScope(): Scope? {
  val scopeId = getScopeId()
  return koin.getScopeOrNull(scopeId) ?: createAndBindScope(scopeId, getScopeName())
}

private fun Controller.createAndBindScope(scopeId: String, qualifier: Qualifier): Scope? {
  val scopeDefinition = koin.scopeRegistry
    .getScopeDefinition(qualifier.toString())

  var scope: Scope? = null
  if (scopeDefinition != null) {
    scope = koin.createScope(scopeId, qualifier)
    bindScope(scope)
  }
  return scope
}

/**
 * Bind given scope to current LifecycleOwner
 * @param scope
 * @param event
 */
fun Controller.bindScope(scope: Scope) {
  addLifecycleListener(ControllerScopeObserver(scope))
}

/**
 * Get current Koin scope, bound to current lifecycle
 */
val Controller.currentScope: Scope?
  get() = getOrCreateCurrentScope()