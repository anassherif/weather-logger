package com.anas.weatherlogger.base.viewmodel

import androidx.lifecycle.ViewModel
import com.anas.weatherlogger.base.domain.BaseUseCase

abstract class BaseViewModel(val baseUseCase: BaseUseCase): ViewModel() {
}