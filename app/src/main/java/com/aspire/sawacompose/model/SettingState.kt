package com.aspire.sawacompose.model

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.PersistState
import com.aspire.sawacompose.unitls.Constraints.ENGLISH
import com.aspire.sawacompose.unitls.Constraints.PINK

data class SettingState(
    val theme: String = PINK,
    val language: String = ENGLISH
) :
    MavericksState