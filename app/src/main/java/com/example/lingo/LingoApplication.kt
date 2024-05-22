package com.example.lingo

import android.app.Application

class LingoApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}