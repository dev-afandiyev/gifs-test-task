/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.utils.downloader

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.core.net.toUri
import com.media.gifsapp.R

object GifDownloader {

    fun downloadGif(context: Context, url: String) {
        val filename = url.substringAfterLast("/").takeIf { it.endsWith(".gif") } ?: "download.gif"

        val request = DownloadManager.Request(url.toUri()).apply {
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setMimeType("image/gif")
            setAllowedOverMetered(true)
            setAllowedOverRoaming(true)
        }

        val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)

        Toast.makeText(context, context.getString(R.string.gif_download), Toast.LENGTH_SHORT).show()
    }

}