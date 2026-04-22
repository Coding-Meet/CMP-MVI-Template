package com.example.cmp_mvi_template.ui.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.compose_multiplatform
import com.example.cmp_mvi_template.core.platform.toast.ToastManagerFactory
import com.example.cmp_mvi_template.core.utility.IconResource
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import com.example.cmp_mvi_template.core.utility.StateController
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@Composable
fun ToastPopup() {
    // System Toast using ToastManager except/actual
    val toastManager = koinInject<ToastManagerFactory>()
    StateController.systemToastEventFlow.ObserveAsEvents { toast ->
        if (toast != null) {
            toast.first?.asStringForSuspend()?.let {
                toastManager.showToast(
                    message = it,
                    toastDuration = toast.second
                )
            }
        }
    }

    val toast by StateController.popUpToastEventFlow.collectAsState(null)
    LaunchedEffect(toast) {
        toast?.let { toast ->
            delay(toast.second.millis)
            StateController.sendPopUpToastMsgEvent(null)
        }
    }

    AnimatedVisibility(
        visible = toast != null,
//        Option 1
//        enter = slideInVertically { it } + fadeIn(),
//        exit = slideOutVertically { it } + fadeOut()

//        Option 2
//        enter = slideInVertically(
//            initialOffsetY = { it }
//        ) + fadeIn() + expandVertically(),
//        exit = slideOutVertically(
//            targetOffsetY = { it }
//        ) + fadeOut() + shrinkVertically()

//        Option 3
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(400)
        ) + fadeIn(animationSpec = tween(400)),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(300)
        ) + fadeOut(animationSpec = tween(300))

    ) {
        toast?.first?.let {
            Popup(
                alignment = Alignment.BottomCenter,
                properties = PopupProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            ) {
                AppToastContent(
                    message = it.asString(),
                    backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    appIcon = IconResource.DrawableResource(Res.drawable.compose_multiplatform),
                    modifier = Modifier,
                    bottomPadding = 50.dp
                )
            }
        }
    }
}

@Composable
private fun AppToastContent(
    message: String,
    backgroundColor: Color,
    textColor: Color,
    appIcon: IconResource,
    modifier: Modifier = Modifier,
    bottomPadding: Dp = 100.dp
) {
    Card(
        modifier = modifier
            .padding(
                horizontal = 24.dp,
                vertical = bottomPadding
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 10.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                appIcon.asPainterResource(),
                contentDescription = message,
                modifier = Modifier
                    .size(32.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Message
            Text(
                text = message,
                color = textColor,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                maxLines = 2
            )
        }
    }
}