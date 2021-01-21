package com.esys.mvvmboilerplate.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Environment
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.CompositePermissionListener
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener
import com.karumi.dexter.listener.single.PermissionListener
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class AppUtils {
    enum class LogType {
        INFO, DEBUG, WARNING, ERROR
    }

    companion object {
        private const val EMAIL_PATTERN = (
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
                )

        fun isValidEmail(email: String) = Pattern.compile(EMAIL_PATTERN).matcher(email).matches()

        fun getHoursDifferent(startTime: Long, currentTime: Long): Long {
            val hours: Long
            val diff = currentTime - startTime
            hours = diff / (60 * 60 * 1000)
            return hours
        }

        private fun getTimeByFormat(format: String): String? {
            val c = Calendar.getInstance()
            val df = SimpleDateFormat(format)
            df.timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
            return df.format(c.time)
        }

        @SuppressLint("SimpleDateFormat")
        fun convertTime(time: String): String {
            var convertedTime = time
            try {
                val sdf = SimpleDateFormat("H:mm")
                val dateObj = sdf.parse(time)
                convertedTime = SimpleDateFormat("hh:mm aa").format(dateObj)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return convertedTime
        }

        fun getCurrentDateTime(format: String): String? {
            return getTimeByFormat(format)
        }

        fun getCurrentDateTime(): String? {
            return getTimeByFormat("dd-MMM-yyyy hh:mm:ss")
        }

        fun getCurrentTime(format: String): String? {
            return getTimeByFormat(format)
        }

        fun getCurrentTime(): String? {
            return getTimeByFormat("hh:mm")
        }

        fun getCurrentTimeInMillis(): Long {
            val currentTimeMillis: Long
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            currentTimeMillis = calendar.timeInMillis
            return currentTimeMillis
        }

        @Throws(ParseException::class)
        fun getDateFromString(date: String, format: String): Date? {
            @SuppressLint("SimpleDateFormat") val formatter = SimpleDateFormat(format)
            return formatter.parse(date)
        }

        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            // Find the currently focused view, so we can grab the correct window token from it.
            var view = activity.currentFocus
            // If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        fun appendLog(context: Context, text: String?, logType: LogType) {
            val dialogPermissionListener: PermissionListener =
                DialogOnDeniedPermissionListener.Builder
                    .withContext(context)
                    .withTitle("Write Storage Permission")
                    .withMessage("Write Storage Permission is needed insert app logs")
                    .withButtonText("Ok")
                    .build()

            Dexter.withContext(context)
                .withPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(CompositePermissionListener(dialogPermissionListener))
                .check()

            val pathDir = Environment.getExternalStorageDirectory()
            val applicationInfo = context.applicationInfo
            val stringId = applicationInfo.labelRes
            val applicationName =
                if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(
                    stringId
                )
            val logFile = File(
                pathDir.path,
                "/$applicationName.log"
            )
            if (!logFile.exists()) {
                try {
                    logFile.createNewFile()
                } catch (e: IOException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                }
            }
            try {
                // BufferedWriter for performance, true to set append to file flag
                val buf = BufferedWriter(FileWriter(logFile, true))

                buf.append("[${getCurrentDateTime("yyyy-MM-dd HH:mm:ss")}][$logType] $text")
                buf.newLine()
                buf.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


        fun getMonthName(value: String): String {
            if (value == "01" || value == "1") {
                return "Jan"
            }
            if (value == "02" || value == "2") {
                return "Feb"
            }
            if (value == "03" || value == "3") {
                return "Mar"
            }
            if (value == "04" || value == "4") {
                return "Apr"
            }
            if (value == "05" || value == "5") {
                return "May"
            }
            if (value == "06" || value == "6") {
                return "Jun"
            }
            if (value == "07" || value == "7") {
                return "Jul"
            }
            if (value == "08" || value == "8") {
                return "Aug"
            }
            if (value == "09" || value == "9") {
                return "Sep"
            }
            if (value == "10" || value == "10") {
                return "Oct"
            }
            if (value == "11") {
                return "Nov"
            }
            return if (value == "12") {
                "Dec"
            } else "Not Found"
        }
    }
}
