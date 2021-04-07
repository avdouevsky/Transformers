package com.aequilibrium.transformers

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.aequilibrium.transformers.model.session.SessionRepositoryContract
import com.aequilibrium.transformers.transformers.TransformersFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.transformer_fragment.container
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionRepository: SessionRepositoryContract
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (sessionRepository.session != null && savedInstanceState == null) {
            showFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        if (sessionRepository.session == null) {
            sessionRepository.obtainSession()
                .subscribeOn(MainSchedulers.networkScheduler)
                .observeOn(MainSchedulers.uiScheduler)
                .subscribe({
                    retryButton.visibility = GONE
                    showFragment()
                }, {
                    retryButton.visibility = VISIBLE
                    Snackbar.make(
                        container,
                        "Somethings went wrong. Try again later.",
                        BaseTransientBottomBar.LENGTH_LONG
                    ).show()
                }).addTo(compositeDisposable)
        }
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    private fun showFragment() {
        val tag = null
        val fragment = TransformersFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        if (tag != null) {
            transaction.addToBackStack(tag)
            transaction.commit()
        } else {
            transaction.commitNow()
        }
    }
}