// app/src/main/java/com/example/frontstore/App.kt
package com.example.frontstore

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Clase Application principal, anotada con @HiltAndroidApp
 * para inicializar el grafo de Hilt.
 */
@HiltAndroidApp
class App : Application()
