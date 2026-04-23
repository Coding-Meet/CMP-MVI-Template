package com.example.cmp_mvi_template.feature.onboarding.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.RocketLaunch
import androidx.compose.material.icons.outlined.TravelExplore
import androidx.compose.ui.graphics.vector.ImageVector
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.onboarding_page1_desc
import cmp_mvi_template.composeapp.generated.resources.onboarding_page1_title
import cmp_mvi_template.composeapp.generated.resources.onboarding_page2_desc
import cmp_mvi_template.composeapp.generated.resources.onboarding_page2_title
import cmp_mvi_template.composeapp.generated.resources.onboarding_page3_desc
import cmp_mvi_template.composeapp.generated.resources.onboarding_page3_title
import cmp_mvi_template.composeapp.generated.resources.onboarding_page4_desc
import cmp_mvi_template.composeapp.generated.resources.onboarding_page4_title
import org.jetbrains.compose.resources.StringResource

data class OnboardingPage(
    val icon: ImageVector,
    val titleRes: StringResource,
    val descRes: StringResource,
)

fun onboardingPages() = listOf(
    OnboardingPage(
        icon = Icons.Outlined.AutoAwesome,
        titleRes = Res.string.onboarding_page1_title,
        descRes = Res.string.onboarding_page1_desc,
    ),
    OnboardingPage(
        icon = Icons.Outlined.TravelExplore,
        titleRes = Res.string.onboarding_page2_title,
        descRes = Res.string.onboarding_page2_desc,
    ),
    OnboardingPage(
        icon = Icons.Outlined.FavoriteBorder,
        titleRes = Res.string.onboarding_page3_title,
        descRes = Res.string.onboarding_page3_desc,
    ),
    OnboardingPage(
        icon = Icons.Outlined.RocketLaunch,
        titleRes = Res.string.onboarding_page4_title,
        descRes = Res.string.onboarding_page4_desc,
    ),
)
