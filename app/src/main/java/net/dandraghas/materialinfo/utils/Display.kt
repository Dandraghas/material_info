package net.dandraghas.materialinfo.utils

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.WindowInsetsCompat

object Display {
	fun getDisplayResolution(activity: ComponentActivity): Pair<Int, Int> {
		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			val windowMetrics = activity.windowManager.currentWindowMetrics
			val insets =
				windowMetrics.windowInsets.getInsetsIgnoringVisibility(
					WindowInsetsCompat.Type.systemBars()
				)
			Pair(
				windowMetrics.bounds.width() - insets.left - insets.right,
				windowMetrics.bounds.height() - insets.top - insets.bottom
			)
		} else {
			val displayMetrics = DisplayMetrics()
			@Suppress("DEPRECATION")
			activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
			Pair(displayMetrics.widthPixels, displayMetrics.heightPixels)
		}
	}

	fun getDisplayRefreshRate(context: Context): Float {
		return try {
			@Suppress("DEPRECATION")
			val display =
				(getSystemService(context, WindowManager::class.java) as WindowManager)
					.defaultDisplay
			val refreshRate = display.refreshRate

			Log.d("DisplayUtils", "getRefreshRate: refreshRate = $refreshRate")

			refreshRate
		} catch (e: Exception) {
			Log.e("DisplayUtils", "Error getting refresh rate", e)

			0f
		}
	}

	fun getDisplayDpi(context: Context): Pair<Float, Float> {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
			val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

			val windowMetrics = windowManager.currentWindowMetrics

			val xdpi: Float = windowMetrics.bounds.width() / windowMetrics.density
			val ydpi: Float = windowMetrics.bounds.height() / windowMetrics.density

			Log.d(
				"DisplayUtils",
				"getDisplayDpi: xdpi = $xdpi ydpi = $ydpi method = currentWindowMetrics"
			)
			return Pair(xdpi, ydpi)
		} else {
			val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

			@Suppress("DEPRECATION") val display = windowManager.defaultDisplay
			val metrics = DisplayMetrics()

			@Suppress("DEPRECATION") display.getRealMetrics(metrics)

			val xdpi = metrics.xdpi
			val ydpi = metrics.ydpi

			Log.d(
				"DisplayUtils",
				"getDisplayDpi: xdpi = $xdpi ydpi = $ydpi method = getRealMetrics"
			)
			return Pair(xdpi, ydpi)
		}
	}
}
