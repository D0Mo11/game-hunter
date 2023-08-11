package com.dragic.gamehunter.view.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.theme.Typography

@Composable
fun GameDetailsTopBar(
    onArrowBackClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.top_bar_height))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.app_name),
            style = Typography.titleLarge
        )
        IconButton(
            onClick = { onArrowBackClicked() },
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.top_bar_icon_padding))
                .size(dimensionResource(id = R.dimen.top_bar_icon_size))
                .align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back_arrow_content_description),
                modifier = Modifier
                    .matchParentSize()
                    .align(Alignment.CenterStart)
            )
        }
    }
}
