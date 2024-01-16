package net.dandraghas.materialinfo.utils

import android.util.Log

object Translation {
	fun getString(context: android.content.Context, name: String): String {
		val resourceId = context.resources.getIdentifier(name, "string", context.packageName)
		val string =
			if (resourceId != 0) {
				context.getString(resourceId)
			} else {
				Log.e("Translation", "Resource not found for name: $name")
				return "Unknown Res"
			}
		Log.d("Translation", "getString: string = $string resourceId = $resourceId name = $name")
		return string
	}
}
