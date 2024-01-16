package net.dandraghas.materialinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import net.dandraghas.materialinfo.activities.LaunchCpuActivity
import net.dandraghas.materialinfo.ui.theme.MaterialInfoTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialInfoTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					MainActivityComponent()
				}
			}
		}
	}
}

@Composable
fun MainActivityComponent() {
	val context = LocalContext.current

	val buttonModifier = Modifier.fillMaxWidth()

	Box(modifier = Modifier.fillMaxSize()) {
		Column(modifier = Modifier.fillMaxSize()) {
			Button(onClick = { LaunchCpuActivity(context) }, modifier = buttonModifier) {
				Text("CPU")
			}
			Button(onClick = { /*TODO*/}, modifier = buttonModifier) { Text("Button 2") }
			Button(onClick = { /*TODO*/}, modifier = buttonModifier) { Text("Button 3") }
		}
	}
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
	MaterialInfoTheme { MainActivityComponent() }
}
