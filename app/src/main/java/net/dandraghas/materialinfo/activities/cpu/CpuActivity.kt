package net.dandraghas.materialinfo.activities.cpu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import net.dandraghas.materialinfo.ui.theme.MaterialInfoTheme
import net.dandraghas.materialinfo.utils.Cpu.getCPUFrequencies
import net.dandraghas.materialinfo.utils.Cpu.getCpuArch
import net.dandraghas.materialinfo.utils.Cpu.getCpuName
import net.dandraghas.materialinfo.utils.Cpu.getSupportedCpuAbis32
import net.dandraghas.materialinfo.utils.Cpu.getSupportedCpuAbis64
import net.dandraghas.materialinfo.utils.Translation.getString

class CpuActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialInfoTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					CpuActivityComponent()
				}
			}
		}
	}
}

@Composable
fun CpuActivityComponent(modifier: Modifier = Modifier) {
	val context = LocalContext.current
	val cpuName = getCpuName()
	val cpuArch = getCpuArch()
	var cpuFreqs by remember { mutableStateOf(getCPUFrequencies()) }
	val abis32 = getSupportedCpuAbis32()
	val abis64 = getSupportedCpuAbis64()

	LaunchedEffect(Unit) {
		while (true) {
			cpuFreqs = getCPUFrequencies()

			delay(1000)
		}
	}

	Column(modifier = Modifier.fillMaxSize()) {
		Text(text = "${getString(context, "activity_cpu_cpu_name")}: $cpuName", modifier = modifier)
		Divider()
		Text(
			text = "${getString(context, "activity_cpu_cpu_arch")}: $cpuArch",
		)
		Divider()
		for (coreNumber in cpuFreqs.indices) {
			val frequencyText = "${getString(context, "activity_cpu_cpu_freq_core")} $coreNumber ${getString(context, "activity_cpu_cpu_freq_clock")}: ${cpuFreqs[coreNumber]} MHz"
			Text(text = frequencyText)
			Divider()
		}

		Text(
			text = "${getString(context, "activity_cpu_abis64")}: ${abis64.joinToString()}",
		)

		Divider()

		Text(
			text = "${getString(context, "activity_cpu_abis32")}: ${abis32.joinToString()}",
		)

		Divider()
	}
}

@Preview(showBackground = true)
@Composable
fun CpuActivityPreview() {
	MaterialInfoTheme { CpuActivityComponent() }
}
