package com.yechaoa.wanandroid_jetpack.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yechaoa.wanandroid_jetpack.common.MyConfig
import com.yechaoa.wanandroid_jetpack.ui.login.LoginActivity
import com.yechaoa.yutilskt.SpUtil

abstract class BaseVmFragment<VB : ViewBinding,VM : BaseViewModel> : BaseFragment<VB>() {

    protected lateinit var mViewModel: VM
    private var lazyLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        observe()
        initView()
        initData()
        setListener()
    }

    override fun onResume() {
        super.onResume()
        // 实现懒加载
        if (!lazyLoaded) {
            lazyLoadData()
            lazyLoaded = true
        }
    }

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    /**
     * 获取ViewModel的class
     */
    abstract fun viewModelClass(): Class<VM>

    /**
     * 订阅，有逻辑的话，复写的时候super不要去掉
     */
    open fun observe() {
        // 需要登录，跳转登录页
        mViewModel.needLogin.observe(viewLifecycleOwner, {
            if (it) {
                SpUtil.setBoolean(MyConfig.IS_LOGIN, false)
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
            }
        })
    }

    /**
     * 初始化view相关
     */
    abstract fun initView()

    /**
     * 初始化data相关
     */
    open fun initData() {}

    /**
     * 懒加载数据
     */
    open fun lazyLoadData() {}

    /**
     * 设置监听
     */
    open fun setListener() {}

}