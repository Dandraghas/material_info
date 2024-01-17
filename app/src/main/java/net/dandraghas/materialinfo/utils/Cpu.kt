package net.dandraghas.materialinfo.utils

import android.os.Build
import android.util.Log
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

object Cpu {
	fun getSupportedCpuAbis32(): Array<String> = Build.SUPPORTED_32_BIT_ABIS

	fun getSupportedCpuAbis64(): Array<String> = Build.SUPPORTED_64_BIT_ABIS

	fun getCpuArch(): String {
		return Build.SUPPORTED_ABIS.firstOrNull() ?: "Unknown"
	}

	fun getCpuName(): String {
		val br = BufferedReader(FileReader("/proc/cpuinfo"))

		var hardwareInfo: String? = null

		br.useLines { lines ->
			lines.forEach { line ->
				val data = line.split(":")
				if (data.size > 1) {
					var key = data[0].trim().replace(" ", "_")
					if (key == "model_name") key = "cpu_model"

					if (key == "Hardware") {
						hardwareInfo = data[1].trim().substringAfter("=").trim()
					}
				}
			}
		}

		return hardwareInfo ?: ""
	}

	fun getCPUFrequencies(): IntArray {
		return try {
			val numCores = Runtime.getRuntime().availableProcessors()
			val cpuFrequencies = IntArray(numCores)

			for (coreNumber in 0 until numCores) {
				val cpuFreqFile = "/sys/devices/system/cpu/cpu$coreNumber/cpufreq/scaling_cur_freq"

				BufferedReader(FileReader(cpuFreqFile))
					.use { reader ->
						reader.readLine()?.toIntOrNull()?.div(1000) ?: 0 // Convert from kHz to MHz
					}
					.also { frequency -> cpuFrequencies[coreNumber] = frequency }
			}

			Log.d("CPU", "getCPUFrequencies: " + cpuFrequencies.contentToString())
			cpuFrequencies
		} catch (e: IOException) {
			e.printStackTrace()
			IntArray(0)
		}
	}

	fun getAverageCpuFrequency(): Int {
		return getCPUFrequencies().average().toInt()
	}

	fun getMaxRangeCpuFrequency(): Int {
		return getCpuFrequencyRanges().maxOfOrNull { it.maxFrequency }?.toInt() ?: 0
	}

	fun getMinRangeCpuFrequency(): Int {
		return getCpuFrequencyRanges().minOfOrNull { it.minFrequency }?.toInt() ?: 0
	}

	data class CpuFrequencyRange(val minFrequency: Long, val maxFrequency: Long)

	fun getCpuFrequencyRanges(): List<CpuFrequencyRange> {
		return try {
			val numCores = Runtime.getRuntime().availableProcessors()
			val cpuFrequencyRanges = mutableListOf<CpuFrequencyRange>()

			for (coreNumber in 0 until numCores) {
				val minFreqFile = "/sys/devices/system/cpu/cpu$coreNumber/cpufreq/cpuinfo_min_freq"
				val maxFreqFile = "/sys/devices/system/cpu/cpu$coreNumber/cpufreq/cpuinfo_max_freq"

				val minFrequency =
					BufferedReader(FileReader(minFreqFile)).use { reader ->
						reader.readLine()?.toLongOrNull()?.div(1000) ?: 0
					}

				val maxFrequency =
					BufferedReader(FileReader(maxFreqFile)).use { reader ->
						reader.readLine()?.toLongOrNull()?.div(1000) ?: 0
					}

				cpuFrequencyRanges.add(CpuFrequencyRange(minFrequency, maxFrequency))
			}

			cpuFrequencyRanges
		} catch (e: IOException) {
			e.printStackTrace()
			emptyList()
		}
	}
}
