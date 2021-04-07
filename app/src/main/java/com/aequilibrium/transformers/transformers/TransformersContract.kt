package com.aequilibrium.transformers.transformers

import com.aequilibrium.transformers.mvp.BasePresenter
import com.aequilibrium.transformers.mvp.BaseView

interface TransformersContract {
    interface View : BaseView<Presenter> {
        fun showTransformers(transformers: List<TransformerViewModel>)
        fun openTransformerUi(transformerId: String?)
    }

    interface Presenter : BasePresenter<View> {
        fun onCreateClicked()
        fun onWarClicked()
        fun onTransformerClicked(viewModel: TransformerViewModel)
        fun onDeleteTransformerClicked(viewModel: TransformerViewModel)
    }
}