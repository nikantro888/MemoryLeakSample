package com.example.asynctasksample

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_async_task.*
import java.lang.ref.WeakReference

class AsyncTaskActivity : AppCompatActivity(),EventListener {
    private var nestedClass:NestedTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)

        //memory leak
         LeakAsyncTask(this).execute()

        //no leak
        //WeakReferenceAsyncTask(WeakReference(this)).execute()

        //inner class leak
        //InnerTask().execute()

        //with listener nested static class
        /* nestedClass = NestedTask().apply {
            setListener(this@AsyncTaskActivity)
            execute()
        }*/
    }

    override fun onDestroy() {
        ///nestedClass?.setListener(null)
        super.onDestroy()
    }

    fun changeTxt(msg: String) {
        textView.text = msg
    }

    override fun onFinished(msg: String) {
        changeTxt(msg)
    }



    // nested Class example
    class NestedTask : AsyncTask<Void, Void, String>() {

        private var eventListener: EventListener? = null

        override fun doInBackground(vararg params: Void): String {
            Thread.sleep(5000)
            return "hello Space"
        }

        override fun onPostExecute(result: String) {
            eventListener?.onFinished(result)
        }

        fun setListener(eventListener: EventListener?){
            this.eventListener = eventListener
        }
    }


    // Inner Class example
    inner class InnerTask : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void): String {
            Thread.sleep(5000)
            return "hello Space"
        }

        override fun onPostExecute(result: String) {
            changeTxt(result)
        }

    }




}

