package com.tarot.app
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.io.File

@HiltAndroidApp
class TarotApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            val stack = throwable.stackTraceToString()
            File(filesDir, "crash.log").writeText(stack)
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}
