package com.aspire.sawacompose.viewmodel

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.aspire.sawacompose.di.AssistedViewModelFactory
import com.aspire.sawacompose.di.daggerMavericksViewModelFactory
import com.aspire.sawacompose.model.SettingState
import com.aspire.sawacompose.repo.SettingRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SettingViewModel @AssistedInject constructor(
    @Assisted initialState: SettingState,
    private val repo: SettingRepo
) :
    MavericksViewModel<SettingState>(initialState) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<SettingViewModel, SettingState> {
        override fun create(state: SettingState): SettingViewModel
    }

    companion object :
        MavericksViewModelFactory<SettingViewModel, SettingState> by daggerMavericksViewModelFactory()

    fun setLanguage(language: String) {
        repo.setLanguage(language)
    }

    fun getLanguage() {
        setState { copy(language = repo.getLanguage()) }
    }

    fun getTheme() {
        setState { copy(theme = repo.getTheme()) }
    }

    fun setTheme(theme: String) {
        repo.setTheme(theme)
    }
}