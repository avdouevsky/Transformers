package com.aequilibrium.transformers.transformer

import com.aequilibrium.transformers.model.transformers.local.Team
import com.aequilibrium.transformers.mvp.BasePresenter
import com.aequilibrium.transformers.mvp.BaseView

interface TransformerContract {
    interface View : BaseView<Presenter> {
        fun showTransformer(viewModel: TransformerViewModel)
        fun showParameterValue(parameter: TransformerParameter, value: String)
        fun closeView()
        fun setButtonText(text: String)
        fun showError(text: String)
    }

    interface Presenter : BasePresenter<View> {
        fun onTeamChanged(team: Team)
        fun onNameChanged(name: String)
        fun onSeekBarProgressChanged(parameter: TransformerParameter, value: Int)
        fun onBackPressed()
        fun onActionButtonPressed()
    }
}