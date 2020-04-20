package com.anas.weatherlogger.base.domain

/**
 * BaseUseCase is used to hold any common logic and all abstract methods to be overridden by its subtypes.
 */
abstract class BaseUseCase {
    abstract fun onDestroy()
}