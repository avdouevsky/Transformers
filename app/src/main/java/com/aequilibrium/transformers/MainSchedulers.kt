package com.aequilibrium.transformers

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

object MainSchedulers {

    var databaseScheduler = Schedulers.io()
        private set

    var backgroundScheduler = Schedulers.io()
        private set

    var networkScheduler = Schedulers.from(Executors.newFixedThreadPool(3))
        private set

    var uiScheduler = AndroidSchedulers.mainThread()
        private set

}