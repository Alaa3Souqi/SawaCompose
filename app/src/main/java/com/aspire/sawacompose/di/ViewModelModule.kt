package com.aspire.sawacompose.di

import com.aspire.sawacompose.viewmodel.SettingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    fun viewModelFactory(factory: SettingViewModel.Factory): AssistedViewModelFactory<*, *>

}
