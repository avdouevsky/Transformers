package com.aequilibrium.transformers.model.session

import io.reactivex.Completable

interface SessionRepositoryContract {

    var session: String?

    fun obtainSession(): Completable

}