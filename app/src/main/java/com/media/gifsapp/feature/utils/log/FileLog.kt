/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.utils.log

import android.util.Log

object FileLog {

    private const val TAG = "GifsApp"

    fun d(message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            Log.d(TAG, message, throwable)
        } else {
            Log.d(TAG, message)
        }
    }

    fun i(message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            Log.i(TAG, message, throwable)
        } else {
            Log.i(TAG, message)
        }
    }

    fun w(message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            Log.w(TAG, message, throwable)
        } else {
            Log.w(TAG, message)
        }
    }

    fun e(message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            Log.e(TAG, message, throwable)
        } else {
            Log.e(TAG, message)
        }
    }

    fun v(message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            Log.v(TAG, message, throwable)
        } else {
            Log.v(TAG, message)
        }
    }

}