package com.example.callbacksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CallbackLeakActivity : AppCompatActivity() {

    private val listener = Listener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_callback_leak)
    }

    override fun onStart() {
        super.onStart()
        GlobalSingleton.register(listener)
    }

    override fun onStop() {
        super.onStop()

        // we forget to unregister our listener so a reference
        // of the Singleton to the listener and to the Activity
        // still exists after the user navigates away fro that
        // activity
        // we also can use weakReference to avoid leak

        // uncomment to avoid memory leak
        //GlobalSingleton.unregister(listener)
    }

    // inner class has implicit reference to enclosing Activity
    private inner class Listener : GlobalSingletonListener {
        override fun onEvent() { }
    }
}

