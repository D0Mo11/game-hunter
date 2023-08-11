package com.dragic.gamehunter.view.uicomponents

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dragic.gamehunter.view.navigation.bottomBarScreens

@Composable
fun BottomBar(
    route: String,
    onRouteSelected: (targetRoute: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier.background(MaterialTheme.colorScheme.primary)
    ) {
        bottomBarScreens.forEach { destination ->
            val selected = route.contains(destination.route)
            NavigationBarItem(
                icon = {
                    BottomBarItem(
                        title = destination.text,
                        icon = destination.icon,
                        selected = selected,
                        modifier = Modifier.weight(1f)
                    )
                },
                selected = selected,
                onClick = { onRouteSelected(destination.route) })
        }
    }
}
