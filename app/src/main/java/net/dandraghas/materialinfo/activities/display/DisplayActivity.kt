package net.dandraghas.materialinfo.activities.display

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import net.dandraghas.materialinfo.utils.Display.getDisplayDpi
import net.dandraghas.materialinfo.utils.Display.getDisplayRefreshRate
import net.dandraghas.materialinfo.utils.Display.getDisplayResolution
import net.dandraghas.materialinfo.utils.Translation.getString

class DisplayActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialInfoTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					DisplayActivityComponent()
				}
			}
		}
	}
}

@Composable
fun DisplayActivityComponent(modifier: Modifier = Modifier) {
	val context = LocalContext.current
	val screenWidth = getDisplayResolution(context as ComponentActivity)
	val refreshRate = getDisplayRefreshRate(context)

	val dpi = getDisplayDpi(context)
	val xdpi = dpi.first
	val ydpi = dpi.second

	Column {
		Text(
			text =
				"${getString(context, "activity_display_resolution")}: ${screenWidth.first} x ${screenWidth.second}",
			modifier = modifier
		)

		Divider()

		Text(
			text = "${getString(context, "activity_display_refresh_rate")}: $refreshRate",
			modifier = modifier
		)

		Divider()

		Text(text = "xdpi: $xdpi", modifier = modifier)

		Divider()

		Text(text = "ydpi: $ydpi", modifier = modifier)

		Divider()
	}
}

@Preview(showBackground = true)
@Composable
fun DisplayActivityPreview() {
	MaterialInfoTheme { DisplayActivityComponent() }
}
