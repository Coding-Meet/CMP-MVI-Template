package com.example.cmp_mvi_template.core.platform.toast

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGPointMake
import platform.CoreGraphics.CGRectMake
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UILabel
import platform.UIKit.UIScreen
import platform.UIKit.UIView
import platform.UIKit.UIViewAnimationOptionCurveEaseOut

internal class IosToastManager : ToastManagerFactory {
    @OptIn(ExperimentalForeignApi::class)
    override fun showToast(message: String, toastDuration: ToastDuration) {

        val duration = when (toastDuration) {
            ToastDuration.SHORT -> 2.0
            ToastDuration.LONG -> 5.0
        }

        val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
        val toastLabel = UILabel(
            frame = CGRectMake(
                0.0,
                0.0,
                UIScreen.mainScreen.bounds.useContents { size.width } - 40,
                35.0))
        toastLabel.center = CGPointMake(UIScreen.mainScreen.bounds.useContents { size.width } / 2,
            UIScreen.mainScreen.bounds.useContents { size.height } - 100.0)
        toastLabel.textAlignment = NSTextAlignmentCenter
        toastLabel.backgroundColor = UIColor.blackColor.colorWithAlphaComponent(0.6)
        toastLabel.textColor = UIColor.whiteColor
        toastLabel.text = message
        toastLabel.alpha = 1.0
        toastLabel.layer.cornerRadius = 15.0
        toastLabel.clipsToBounds = true
        rootViewController?.view?.addSubview(toastLabel)

        UIView.animateWithDuration(
            duration = duration,
            delay = 0.1,
            options = UIViewAnimationOptionCurveEaseOut,
            animations = {
                toastLabel.alpha = 0.0
            },
            completion = {
                if (it)
                    toastLabel.removeFromSuperview()
            })
    }
}