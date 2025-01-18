package com.example.notes

import android.app.Application
import com.example.notes.data.AppContainer
import com.example.notes.data.AppDataContainer

class NotesApplication: Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(context = this)
    }
}