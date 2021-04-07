package com.aequilibrium.transformers.transformer

import com.aequilibrium.transformers.MainSchedulers
import com.aequilibrium.transformers.model.transformers.TransformersRepositoryContract
import com.aequilibrium.transformers.model.transformers.local.Team
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class TransformerPresenter(
    private val transformerId: String?,
    private val transformersRepository: TransformersRepositoryContract
) : TransformerContract.Presenter {

    private var view: TransformerContract.View? = null
    private val compositeDisposable = CompositeDisposable()
    private val mode = if (transformerId != null) Mode.EDIT else Mode.CREATE
    private lateinit var transformerParameters: TransformerParameters

    override fun takeView(view: TransformerContract.View) {
        this.view = view

        when (mode) {
            Mode.EDIT -> view.setButtonText("SAVE")
            Mode.CREATE -> view.setButtonText("CREATE")
        }

        transformersRepository.getTransformers()
            .subscribeOn(MainSchedulers.networkScheduler)
            .observeOn(MainSchedulers.uiScheduler)
            .subscribe({
                val transformer = it.find { it.id == transformerId }
                transformerParameters = TransformerParameters(transformer)
                view.showTransformer(TransformerViewModel(transformerParameters))
            }, {

            }).addTo(compositeDisposable)
    }

    override fun dropView() {
        view = null
        compositeDisposable.clear()
    }

    override fun onTeamChanged(team: Team) {
        transformerParameters = transformerParameters.copy(team = team)
    }

    override fun onNameChanged(name: String) {
        transformerParameters = transformerParameters.copy(name = name)
    }

    override fun onSeekBarProgressChanged(parameter: TransformerParameter, value: Int) {
        when (parameter) {
            TransformerParameter.STRENGTH -> {
                transformerParameters = transformerParameters.copy(strength = value)
            }
            TransformerParameter.INTELLIGENCE -> {
                transformerParameters = transformerParameters.copy(intelligence = value)
            }
            TransformerParameter.SPEED -> {
                transformerParameters = transformerParameters.copy(speed = value)
            }
            TransformerParameter.ENDURANCE -> {
                transformerParameters = transformerParameters.copy(endurance = value)
            }
            TransformerParameter.RANK -> {
                transformerParameters = transformerParameters.copy(rank = value)
            }
            TransformerParameter.COURAGE -> {
                transformerParameters = transformerParameters.copy(courage = value)
            }
            TransformerParameter.FIREPOWER -> {
                transformerParameters = transformerParameters.copy(firepower = value)
            }
            TransformerParameter.SKILL -> {
                transformerParameters = transformerParameters.copy(skill = value)
            }
        }
        view?.showParameterValue(parameter, value.toString())
    }

    override fun onBackPressed() {
        view?.closeView()
    }

    override fun onActionButtonPressed() {
        if (transformerParameters.name.isEmpty()) {
            view?.showError("Name can't be empty")
            return
        }
        when (mode) {
            Mode.EDIT -> {
                transformersRepository.editTransformer(transformerParameters)
                    .subscribeOn(MainSchedulers.networkScheduler)
                    .observeOn(MainSchedulers.uiScheduler)
                    .subscribe({
                        view?.closeView()
                    }, {

                    }).addTo(compositeDisposable)
            }
            Mode.CREATE -> {
                transformersRepository.createTransformer(transformerParameters)
                    .subscribeOn(MainSchedulers.networkScheduler)
                    .observeOn(MainSchedulers.uiScheduler)
                    .subscribe({
                        view?.closeView()
                    }, {

                    }).addTo(compositeDisposable)
            }
        }
    }

    enum class Mode {
        CREATE, EDIT
    }

}