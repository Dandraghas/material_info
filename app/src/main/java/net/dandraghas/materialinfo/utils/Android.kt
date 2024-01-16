package net.dandraghas.materialinfo.utils

import android.os.Build
import android.util.Log
import java.lang.reflect.Field

object Android {
	fun getAndroidVersion(): String = "Android ${Build.VERSION.SDK_INT}"

	fun getAndroidVersionName(): String {
		val fields: Array<Field> = Build.VERSION_CODES::class.java.fields
		val codename: String = fields[Build.VERSION.SDK_INT + 1].name

		Log.d(
			"AndroidUtils",
			"getAndroidVersionName: $codename fields = ${fields.joinToString { it.name }}"
		)
		return codename
	}

	fun getKernelVersion(): String? = System.getProperty("os.version", "Unknown")
}
