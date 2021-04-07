package com.aequilibrium.transformers.transformers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.aequilibrium.transformers.R
import com.aequilibrium.transformers.combat.CombatFragment
import com.aequilibrium.transformers.model.combat.CombatRepositoryContract
import com.aequilibrium.transformers.model.transformers.TransformersRepositoryContract
import com.aequilibrium.transformers.transformer.TransformerFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.transformers_fragment.*
import javax.inject.Inject

class TransformersFragment : DaggerFragment(), TransformersContract.View {

    @Inject
    lateinit var combatRepository: CombatRepositoryContract

    @Inject
    lateinit var transformersRepository: TransformersRepositoryContract

    @Inject
    lateinit var presenter: TransformersContract.Presenter
    private var adapter: TransformersAdapter? = null

    companion object {
        fun newInstance(): TransformersFragment {
            return TransformersFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.transformers_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createButton.setOnClickListener { presenter.onCreateClicked() }
        warButton.setOnClickListener {
            val tag = "CombatFragment"
            val fragment = CombatFragment.newInstance()
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            if (tag != null) {
                transaction.addToBackStack(tag)
                transaction.commit()
            } else {
                transaction.commitNow()
            }
        }
        adapter = TransformersAdapter(onTransformerClicked = { viewModel ->
            presenter.onTransformerClicked(viewModel)
        }, onDeleteTransformerClicked = { viewModel ->
            presenter.onDeleteTransformerClicked(viewModel)
        })

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

    override fun showTransformers(transformers: List<TransformerViewModel>) {
        adapter!!.viewItems = transformers
    }

    override fun openTransformerUi(transformerId: String?) {
        val tag = "TransformerFragment"
        val fragment = TransformerFragment.newInstance(transformerId)
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

}