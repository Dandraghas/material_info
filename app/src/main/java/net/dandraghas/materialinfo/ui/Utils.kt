package net.dandraghas.materialinfo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap

object Utils {
	private fun <K, V> snapshotStateMapSaver() =
		Saver<SnapshotStateMap<K, V>, Any>(
			save = { state -> state.toList() },
			restore = { value ->
				@Suppress("UNCHECKED_CAST")
				(value as? List<Pair<K, V>>)?.toMutableStateMap() ?: mutableStateMapOf<K, V>()
			}
		)

	@Composable
	fun <K, V> rememberSavableSnapshotStateMap(
		init: () -> SnapshotStateMap<K, V>
	): SnapshotStateMap<K, V> = rememberSaveable(saver = snapshotStateMapSaver(), init = init)
}
