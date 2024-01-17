package net.dandraghas.materialinfo.activities

import android.content.Intent
import net.dandraghas.materialinfo.activities.android.AndroidActivity
import net.dandraghas.materialinfo.activities.cpu.CpuActivity
import net.dandraghas.materialinfo.activities.display.DisplayActivity

fun LaunchCpuActivity(context: android.content.Context) {
	val cpuActivity = Intent(context, CpuActivity::class.java)
	context.startActivity(cpuActivity)
}

fun LaunchAndroidActivity(context: android.content.Context) {
	val androidActivity = Intent(context, AndroidActivity::class.java)
	context.startActivity(androidActivity)
}

fun LaunchDisplayActivity(context: android.content.Context) {
	val displayActivity = Intent(context, DisplayActivity::class.java)
	context.startActivity(displayActivity)
}
