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
import net.dandraghas.materialinfo.activities.LaunchAndroidActivity
import net.dandraghas.materialinfo.activities.LaunchCpuActivity
import net.dandraghas.materialinfo.activities.LaunchDisplayActivity
import net.dandraghas.materialinfo.ui.theme.MaterialInfoTheme
import net.dandraghas.materialinfo.utils.Translation.getString

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
				Text(getString(context, "title_activity_cpu"))
			}
			Button(onClick = { LaunchAndroidActivity(context) }, modifier = buttonModifier) {
				Text(getString(context, "title_activity_android"))
			}
			Button(onClick = { LaunchDisplayActivity(context) }, modifier = buttonModifier) {
				Text(getString(context, "title_activity_display"))
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
	MaterialInfoTheme { MainActivityComponent() }
}
