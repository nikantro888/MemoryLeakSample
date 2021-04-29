package com.example.asynctasksample

import android.os.AsyncTask

class LeakAsyncTask(private val activity: AsyncTaskActivity) : AsyncTask<Void, Void, String>() {
    override fun doInBackground(vararg params: Void): String {
        Thread.sleep(5000)
        return "hello Space"
    }

    override fun onPostExecute(result: String) {
        activity.changeTxt(result)
    }
}