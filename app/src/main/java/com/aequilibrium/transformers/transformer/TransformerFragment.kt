package com.aequilibrium.transformers.transformer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aequilibrium.transformers.R
import com.aequilibrium.transformers.model.transformers.local.Team
import com.aequilibrium.transformers.utils.SimpleOnSeekBarChangeListener
import com.aequilibrium.transformers.utils.SimpleTextWatcher
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.transformer_fragment.*
import javax.inject.Inject


class TransformerFragment : DaggerFragment(), TransformerContract.View {

    @Inject
    lateinit var presenter: TransformerContract.Presenter
    private var isProgrammaticChange = false

    companion object {

        private const val TRANSFORMER_ID_KEY = "TRANSFORMER_ID_KEY"

        fun newInstance(transformerId: String?): TransformerFragment {
            val fragment = TransformerFragment()
            transformerId?.let {
                fragment.arguments = Bundle().apply {
                    putString(TRANSFORMER_ID_KEY, it)
                }
            }
            return fragment
        }
    }

    val transformerId: String? by lazy {
        arguments?.getString(TRANSFORMER_ID_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.transformer_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameText.requestFocus()

        backButton.setOnClickListener {
            presenter.onBackPressed()
        }

        createButton.setOnClickListener {
            presenter.onActionButtonPressed()
        }

        nameText.addTextChangedListener(SimpleTextWatcher { text ->
            if (!isProgrammaticChange) {
                presenter.onNameChanged(text)
            }
        })

        teamRadioGroup.setOnCheckedChangeListener { _, id ->
            val radioButton: View = teamRadioGroup.findViewById(id)
            val index = teamRadioGroup.indexOfChild(radioButton)
            if (!isProgrammaticChange) {
                val team = if (index == 0) Team.AUTOBOT else Team.DECEPTICON
                presenter.onTeamChanged(team)
            }
        }

        strengthBar.setOnSeekBarChangeListener(SimpleOnSeekBarChangeListener {
            if (!isProgrammaticChange) {
                presenter.onSeekBarProgressChanged(TransformerParameter.STRENGTH, it + 1)
            }
        })

        intelligenceBar.setOnSeekBarChangeListener(SimpleOnSeekBarChangeListener {
            if (!isProgrammaticChange) {
                presenter.onSeekBarProgressChanged(TransformerParameter.INTELLIGENCE, it + 1)
            }
        })

        speedBar.setOnSeekBarChangeListener(SimpleOnSeekBarChangeListener {
            if (!isProgrammaticChange) {
                presenter.onSeekBarProgressChanged(TransformerParameter.SPEED, it + 1)
            }
        })

        enduranceBar.setOnSeekBarChangeListener(SimpleOnSeekBarChangeListener {
            if (!isProgrammaticChange) {
                presenter.onSeekBarProgressChanged(TransformerParameter.ENDURANCE, it + 1)
            }
        })

        rankBar.setOnSeekBarChangeListener(SimpleOnSeekBarChangeListener {
            if (!isProgrammaticChange) {
                presenter.onSeekBarProgressChanged(TransformerParameter.RANK, it + 1)
            }
        })

        courageBar.setOnSeekBarChangeListener(SimpleOnSeekBarChangeListener {
            if (!isProgrammaticChange) {
                presenter.onSeekBarProgressChanged(TransformerParameter.COURAGE, it + 1)
            }
        })

        firePowerBar.setOnSeekBarChangeListener(SimpleOnSeekBarChangeListener {
            if (!isProgrammaticChange) {
                presenter.onSeekBarProgressChanged(TransformerParameter.FIREPOWER, it + 1)
            }
        })

        skillBar.setOnSeekBarChangeListener(SimpleOnSeekBarChangeListener {
            if (!isProgrammaticChange) {
                presenter.onSeekBarProgressChanged(TransformerParameter.SKILL, it + 1)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    override fun showTransformer(viewModel: TransformerViewModel) {
        isProgrammaticChange = true
        nameText.setText(viewModel.transformerParameters.name)
        nameText.setSelection(nameText.text.length)
        when (viewModel.transformerParameters.team) {
            Team.DECEPTICON -> deception.isChecked = true
            Team.AUTOBOT -> autobot.isChecked = true
        }
        strengthValueTextView.text = viewModel.transformerParameters.strength.toString()
        intelligenceValueTextView.text = viewModel.transformerParameters.intelligence.toString()
        speedValueTextView.text = viewModel.transformerParameters.speed.toString()
        enduranceValueTextView.text = viewModel.transformerParameters.endurance.toString()
        rankValueTextView.text = viewModel.transformerParameters.rank.toString()
        courageValueTextView.text = viewModel.transformerParameters.courage.toString()
        firePowerValueTextView.text = viewModel.transformerParameters.firepower.toString()
        skillValueTextView.text = viewModel.transformerParameters.skill.toString()

        strengthBar.progress = viewModel.transformerParameters.strength - 1
        intelligenceBar.progress = viewModel.transformerParameters.intelligence - 1
        speedBar.progress = viewModel.transformerParameters.speed - 1
        enduranceBar.progress = viewModel.transformerParameters.endurance - 1
        rankBar.progress = viewModel.transformerParameters.rank - 1
        courageBar.progress = viewModel.transformerParameters.courage - 1
        firePowerBar.progress = viewModel.transformerParameters.firepower - 1
        skillBar.progress = viewModel.transformerParameters.skill - 1

        isProgrammaticChange = false
    }

    override fun showParameterValue(parameter: TransformerParameter, value: String) {
        when (parameter) {
            TransformerParameter.STRENGTH -> strengthValueTextView.text = value
            TransformerParameter.INTELLIGENCE -> intelligenceValueTextView.text = value
            TransformerParameter.SPEED -> speedValueTextView.text = value
            TransformerParameter.ENDURANCE -> enduranceValueTextView.text = value
            TransformerParameter.RANK -> rankValueTextView.text = value
            TransformerParameter.COURAGE -> courageValueTextView.text = value
            TransformerParameter.FIREPOWER -> firePowerValueTextView.text = value
            TransformerParameter.SKILL -> skillValueTextView.text = value
        }
    }

    override fun closeView() {
        activity?.onBackPressed()
    }

    override fun setButtonText(text: String) {
        createButton.text = text
    }

    override fun showError(text: String) {
        Snackbar.make(
            container,
            text,
            BaseTransientBottomBar.LENGTH_LONG
        ).show()
    }

}