package net.dandraghas.materialinfo.activities.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import net.dandraghas.materialinfo.ui.theme.MaterialInfoTheme
import net.dandraghas.materialinfo.utils.Android.getAndroidVersion
import net.dandraghas.materialinfo.utils.Android.getAndroidVersionName
import net.dandraghas.materialinfo.utils.Android.getKernelVersion
import net.dandraghas.materialinfo.utils.Translation.getString

class AndroidActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialInfoTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					AndroidActivityComponent()
				}
			}
		}
	}
}

@Composable
fun AndroidActivityComponent(modifier: Modifier = Modifier) {
	val context = LocalContext.current

	Box(modifier = Modifier.fillMaxSize()) {
		Column {
			Text(
				text = "${getString(context, "activity_android_version")}: ${getAndroidVersion()}",
				modifier = modifier
			)

			Divider()

			Text(
				text =
					"${getString(context, "activity_android_version_name")}: ${getAndroidVersionName()}",
				modifier = modifier
			)

			Divider()

			Text(
				text =
					"${getString(context, "activity_android_kernel_version")}: ${getKernelVersion()}",
				modifier = modifier
			)

			Divider()
		}
	}
}

@Preview(showBackground = true)
@Composable
fun AndroidActivityPreview() {
	MaterialInfoTheme { AndroidActivityComponent() }
}
