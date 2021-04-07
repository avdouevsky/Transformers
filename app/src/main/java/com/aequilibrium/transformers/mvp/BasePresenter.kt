package com.aequilibrium.transformers.mvp

interface BasePresenter<T> {
    fun takeView(view: T)
    fun dropView()
}