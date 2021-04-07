package com.aequilibrium.transformers.combat

import com.aequilibrium.transformers.MainSchedulers
import com.aequilibrium.transformers.model.combat.CombatRepositoryContract
import com.aequilibrium.transformers.model.transformers.TransformersRepositoryContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class CombatPresenter(
    private val combatRepository: CombatRepositoryContract,
    private val transformerRepository: TransformersRepositoryContract,
) : CombatContract.Presenter {

    var view: CombatContract.View? = null
    private val compositeDisposable = CompositeDisposable()
    private var isFirstLaunch = true

    override fun onBackPressed() {
        view?.closeView()
    }

    override fun takeView(view: CombatContract.View) {
        this.view = view
        if (isFirstLaunch) {
            transformerRepository.getTransformers()
                .subscribeOn(MainSchedulers.networkScheduler)
                .flatMap { combatRepository.conductRobotWar(it) }
                .subscribeOn(MainSchedulers.backgroundScheduler)
                .observeOn(MainSchedulers.uiScheduler)
                .subscribe({
                    isFirstLaunch = false
                    this.view?.showCombat(CombatViewModel(it))
                }, {

                }).addTo(compositeDisposable)
        }
    }

    override fun dropView() {
        view = null
        compositeDisposable.clear()
    }

}