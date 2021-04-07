package com.aequilibrium.transformers.transformers

import com.aequilibrium.transformers.MainSchedulers
import com.aequilibrium.transformers.model.transformers.TransformersRepositoryContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class TransformersPresenter(
    private val transformersRepository: TransformersRepositoryContract,
) : TransformersContract.Presenter {

    var view: TransformersContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun takeView(view: TransformersContract.View) {
        this.view = view
        refreshView(false)
    }

    override fun dropView() {
        view = null
        compositeDisposable.clear()
    }

    override fun onCreateClicked() {
        view?.openTransformerUi(null)
    }

    override fun onWarClicked() {

    }

    override fun onTransformerClicked(viewModel: TransformerViewModel) {
        view?.openTransformerUi(viewModel.id.text)
    }

    override fun onDeleteTransformerClicked(viewModel: TransformerViewModel) {
        transformersRepository.deleteTransformer(viewModel.id.text)
            .subscribeOn(MainSchedulers.networkScheduler)
            .observeOn(MainSchedulers.uiScheduler)
            .subscribe({
                refreshView(true)
            }, {

            }).addTo(compositeDisposable)
    }

    private fun refreshView(isForced: Boolean) {
        transformersRepository.getTransformers(isForced)
            .subscribeOn(MainSchedulers.networkScheduler)
            .observeOn(MainSchedulers.uiScheduler)
            .subscribe({
                val viewModel = it.map { TransformerViewModel(it) }
                this.view?.showTransformers(viewModel)
            }, {

            }).addTo(compositeDisposable)
    }

}