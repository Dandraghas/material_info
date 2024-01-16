package net.dandraghas.materialinfo.activities

import android.content.Intent
import net.dandraghas.materialinfo.activities.android.AndroidActivity
import net.dandraghas.materialinfo.activities.cpu.CpuActivity

fun LaunchCpuActivity(context: android.content.Context) {
	val cpuActivity = Intent(context, CpuActivity::class.java)
	context.startActivity(cpuActivity)
}

fun LaunchAndroidActivity(context: android.content.Context) {
	val androidActivity = Intent(context, AndroidActivity::class.java)
	context.startActivity(androidActivity)
}
