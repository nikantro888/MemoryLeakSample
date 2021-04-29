package com.example.asynctasksample

import android.os.AsyncTask
import java.lang.ref.WeakReference


class WeakReferenceAsyncTask(private val activity: WeakReference<AsyncTaskActivity>) :
    AsyncTask<Void, Void, String>() {
    override fun doInBackground(vararg params: Void): String {
        Thread.sleep(5000)
        return "hello Space"
    }

    override fun onPostExecute(result: String) {
        activity.get()?.changeTxt(result)
        activity.clear()
    }
}