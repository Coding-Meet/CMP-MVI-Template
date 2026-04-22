package com.example.cmp_mvi_template.feature.sample_example.di

import com.example.cmp_mvi_template.feature.sample_example.presentation.screen.SampleExampleViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sampleExampleModule = module {
    viewModelOf(::SampleExampleViewModel)
}
