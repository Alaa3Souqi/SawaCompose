package com.aspire.sawacompose.repo

import android.content.SharedPreferences
import com.aspire.sawacompose.unitls.Constraints.ARABIC
import com.aspire.sawacompose.unitls.Constraints.ARABIC_LABEL
import com.aspire.sawacompose.unitls.Constraints.BLUE
import com.aspire.sawacompose.unitls.Constraints.ENGLISH
import com.aspire.sawacompose.unitls.Constraints.ENGLISH_LABEL
import com.aspire.sawacompose.unitls.Constraints.LANGUAGE
import com.aspire.sawacompose.unitls.Constraints.PINK
import com.aspire.sawacompose.unitls.Constraints.THEME
import javax.inject.Inject

class SettingRepo @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun getLanguage(): String =
        sharedPreferences.getString(LANGUAGE, ENGLISH) ?: ENGLISH

    fun setLanguage(language: String) {
        if (language == ARABIC_LABEL)
            sharedPreferences.edit().putString(LANGUAGE, ARABIC).apply()
        else if (language == ENGLISH_LABEL) {
            sharedPreferences.edit().putString(LANGUAGE, ENGLISH).apply()
        }
    }

    fun setTheme(theme: String) {
        if (theme == "أزرق" || theme == "Blue")
            sharedPreferences.edit().putString(THEME, BLUE).apply()
        else if (theme == "زهري" || theme == "Pink") {
            sharedPreferences.edit().putString(THEME, PINK).apply()
        }
    }

    fun getTheme(): String =
        sharedPreferences.getString(THEME, PINK) ?: PINK

}