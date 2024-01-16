package net.dandraghas.materialinfo.utils

import android.os.Build
import android.util.Log
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

object Cpu {
	fun readCpuInfo(): String {
		val cpuInfoFile = "/proc/cpuinfo"
		val stringBuilder = StringBuilder()

		try {
			BufferedReader(FileReader(cpuInfoFile)).use { reader ->
				reader.forEachLine { line -> stringBuilder.append(line).append("\n") }
			}
		} catch (e: Exception) {
			stringBuilder.append("Error reading CPU info: ${e.message}")
		}

		return stringBuilder.toString()
	}

	fun getSupportedCpuAbis32(): Array<String> = Build.SUPPORTED_32_BIT_ABIS

	fun getSupportedCpuAbis64(): Array<String> = Build.SUPPORTED_64_BIT_ABIS

	fun getCpuArch(): String {
		val supportedAbis = Build.SUPPORTED_ABIS

		if (supportedAbis.isNotEmpty()) {
			return supportedAbis[0]
		}

		return "Unknown"
	}

	fun getCpuName(): String {
		val br = BufferedReader(FileReader("/proc/cpuinfo"))

		var hardwareInfo: String? = null
		var inHardwareSection = false

		br.useLines { lines ->
			lines.forEach { line ->
				val data = line.split(":")
				if (data.size > 1) {
					var key = data[0].trim().replace(" ", "_")
					if (key == "model_name") key = "cpu_model"

					if (key == "Hardware") {
						inHardwareSection = true
						hardwareInfo = data[1].trim()
					}
				}
			}
		}

		return hardwareInfo?.substringAfter("=")?.trim() ?: ""
	}

	fun getCPUFrequencies(): IntArray {
		try {
			val numCores = Runtime.getRuntime().availableProcessors()

			val cpuFrequencies = IntArray(numCores)

			for (coreNumber in 0 until numCores) {
				val cpuFreqFile = "/sys/devices/system/cpu/cpu$coreNumber/cpufreq/scaling_cur_freq"

				BufferedReader(FileReader(cpuFreqFile)).use { reader ->
					val frequencyStr = reader.readLine()

					cpuFrequencies[coreNumber] =
						frequencyStr?.toIntOrNull()?.div(1000) ?: 0 // Convert from kHz to MHz
				}
			}

			Log.d("CPU", "getCPUFrequencies: " + cpuFrequencies.contentToString())
			return cpuFrequencies
		} catch (e: IOException) {
			e.printStackTrace()
			return IntArray(0)
		}
	}

	data class CpuFrequencyRange(val minFrequency: Long, val maxFrequency: Long)

	fun getCpuFrequencyRanges(): List<CpuFrequencyRange> {
		val numCores = Runtime.getRuntime().availableProcessors()
		val cpuFrequencyRanges = mutableListOf<CpuFrequencyRange>()

		for (coreNumber in 0 until numCores) {
			val minFreqFile = "/sys/devices/system/cpu/cpu$coreNumber/cpufreq/cpuinfo_min_freq"
			val maxFreqFile = "/sys/devices/system/cpu/cpu$coreNumber/cpufreq/cpuinfo_max_freq"

			try {
				val minFrequency =
					BufferedReader(FileReader(minFreqFile)).use { reader ->
						reader.readLine()?.toLongOrNull()?.div(1000) ?: 0
					}

				val maxFrequency =
					BufferedReader(FileReader(maxFreqFile)).use { reader ->
						reader.readLine()?.toLongOrNull()?.div(1000) ?: 0
					}

				cpuFrequencyRanges.add(CpuFrequencyRange(minFrequency, maxFrequency))
			} catch (e: IOException) {
				e.printStackTrace()
			}
		}

		return cpuFrequencyRanges
	}
}
