package net.dandraghas.materialinfo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import net.dandraghas.materialinfo.R
import net.dandraghas.materialinfo.ui.Utils.rememberSavableSnapshotStateMap

data class SectionData(val headerText: String, val items: List<String>)

@Composable
fun SectionItem(text: String) {
	Text(
		text = text,
		style = MaterialTheme.typography.bodyLarge,
		modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp)
	)
}

@Composable
fun SectionHeader(text: String, isExpanded: Boolean, onHeaderClicked: () -> Unit) {
	Row(modifier = Modifier.clickable { onHeaderClicked() }) {
		Text(text = text, modifier = Modifier.weight(1.0f))
		if (isExpanded) {
			Image(
				painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24),
				contentDescription = null,
				colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
			)
		} else {
			Image(
				painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
				contentDescription = null,
				colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
			)
		}
	}
}

@Composable
fun ExpandableList(sections: List<SectionData>) {
	val isExpandedMap = rememberSavableSnapshotStateMap {
		List(sections.size) { index: Int -> index to true }.toMutableStateMap()
	}

	LazyColumn(
		content = {
			sections.onEachIndexed { index, sectionData ->
				Section(
					sectionData = sectionData,
					isExpanded = isExpandedMap[index] ?: true,
					onHeaderClick = { isExpandedMap[index] = !(isExpandedMap[index] ?: true) }
				)
			}
		}
	)
}

fun LazyListScope.Section(
	sectionData: SectionData,
	isExpanded: Boolean,
	onHeaderClick: () -> Unit
) {

	item {
		SectionHeader(
			text = sectionData.headerText,
			isExpanded = isExpanded,
			onHeaderClicked = onHeaderClick
		)
	}

	if (isExpanded) {
		items(sectionData.items) { SectionItem(text = it) }
	}
}
