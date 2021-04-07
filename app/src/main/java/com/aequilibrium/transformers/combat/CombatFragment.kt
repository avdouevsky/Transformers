package com.aequilibrium.transformers.combat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aequilibrium.transformers.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.combat_fragment.*
import javax.inject.Inject

class CombatFragment : DaggerFragment(), CombatContract.View {

    @Inject
    lateinit var presenter: CombatContract.Presenter

    companion object {

        fun newInstance(): CombatFragment {
            return CombatFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.combat_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.setOnClickListener {
            presenter.onBackPressed()
        }
    }


    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    override fun showCombat(viewModel: CombatViewModel) {
        winnerTextView.text = viewModel.winnerText
        logTextView.text = (viewModel.logText)
        autobotSurvivorsView.text = viewModel.autobotSurvivorsText
        decepticonSurvivorsView.text = viewModel.decepticonSurvivorsText
        battleCountView.text = viewModel.battleCount
    }

    override fun closeView() {
        activity!!.onBackPressed()
    }
}