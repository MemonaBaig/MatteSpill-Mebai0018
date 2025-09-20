package com.example.mattespill_mebai0018

import android.content.Context
import android.os.Build
import java.util.*

fun setAppLocale(context: Context, language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)

    val config = context.resources.configuration

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        config.setLocale(locale)
        context.createConfigurationContext(config)
    } else {
        @Suppress("DEPRECATION")
        config.locale = locale
        @Suppress("DEPRECATION")
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        context
    }
}
