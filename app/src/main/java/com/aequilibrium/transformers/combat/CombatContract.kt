package com.aequilibrium.transformers.combat

import com.aequilibrium.transformers.mvp.BasePresenter
import com.aequilibrium.transformers.mvp.BaseView

interface CombatContract {
    interface View : BaseView<Presenter> {
        fun showCombat(viewModel: CombatViewModel)
        fun closeView()
    }

    interface Presenter : BasePresenter<View> {
        fun onBackPressed()
    }
}