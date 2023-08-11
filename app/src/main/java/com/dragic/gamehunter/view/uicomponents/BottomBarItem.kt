package com.dragic.gamehunter.view.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.theme.Typography

@Composable
fun BottomBarItem(
    title: String,
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.bottom_bar_icon_padding))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
        )
        Text(
            text = title,
            style = Typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
