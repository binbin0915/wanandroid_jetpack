package com.yechaoa.wanandroid_jetpack

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.yechaoa.wanandroid_jetpack.common.CrashHandler
import com.yechaoa.yutilskt.LogUtil
import com.yechaoa.yutilskt.YUtils

/**
 * Created by yechao on 2021/2/5.
 * Describe :
 */
class App : Application() {

    companion object {
        //控制三方库的编译模式
        private const val isDebugMode = true
    }

    override fun onCreate() {
        super.onCreate()
        initUiMode()
        initYUtils()
        CrashHandler.instance.init(this)
    }

    private fun initUiMode() {
        //脱离系统设置，强制让当前应用程序使用浅色主题
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun initYUtils() {
        YUtils.init(this)
        LogUtil.setIsLog(isDebugMode)
    }

}