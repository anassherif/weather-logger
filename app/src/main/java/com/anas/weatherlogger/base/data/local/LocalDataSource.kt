package com.anas.weatherlogger.base.data.local

/**
 * Base LocalDataSource that is needed to handle all common logic related to the local storage
 */
abstract class LocalDataSource {
    abstract fun closeConnection()
}