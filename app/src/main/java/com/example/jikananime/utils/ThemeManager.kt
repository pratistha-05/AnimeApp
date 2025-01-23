package com.example.jikananime.utils

import android.content.Context

object ThemeManager {

  private const val PREFS_NAME = "ThemePrefs"
  private const val THEME_KEY = "current_theme"

  fun getSavedTheme(context: Context): Boolean {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    return prefs.getBoolean(THEME_KEY, false)
  }

  fun saveTheme(context: Context, isDarkMode: Boolean) {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    prefs.edit().putBoolean(THEME_KEY, isDarkMode).apply()
  }
}
