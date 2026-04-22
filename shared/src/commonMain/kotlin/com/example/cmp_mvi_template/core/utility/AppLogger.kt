package com.example.cmp_mvi_template.core.utility

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.example.cmp_mvi_template.BuildKonfig

object AppLogger {
    
    val isDebugBuild = BuildKonfig.Is_Debug_Build
    
    val logger = Logger(
        config = StaticConfig(
            logWriterList = if (isDebugBuild) {
                listOf(platformLogWriter())
            } else {
                emptyList() // No logging in release
            }
        ),
        tag = "CMP-MVI-Template"
    )

    inline fun d(tag: String = "", throwable: Throwable? = null, message: () -> String) {
        if (isDebugBuild) {
            logger.d(tag = tag, throwable = throwable, message = message)
        }
    }
    
    inline fun i(tag: String = "", throwable: Throwable? = null, message: () -> String) {
        if (isDebugBuild) {
            logger.i(tag = tag, throwable = throwable, message = message)
        }
    }
    
    inline fun e(tag: String = "", throwable: Throwable? = null, message: () -> String) {
        if (isDebugBuild) {
            logger.e(tag = tag, throwable = throwable, message = message)
        }
    }
    
    inline fun w(tag: String = "", throwable: Throwable? = null, message: () -> String) {
        if (isDebugBuild) {
            logger.w(tag = tag, throwable = throwable, message = message)
        }
    }
}