package rosh.ivan.contries.feature.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable.merge
import kotlinx.android.synthetic.main.countries_fragment.*
import rosh.ivan.contries.R
import rosh.ivan.contries.base.BaseFragment
import rosh.ivan.contries.common.ui.ListDividerDecoration
import rosh.ivan.contries.feature.list.CountriesViewState.*
import rosh.ivan.contries.feature.list.adapter.CountriesAdapter


class CountriesFragment : BaseFragment() {

    override val layoutResourceId: Int get() = R.layout.countries_fragment

    private lateinit var viewModel: CountriesViewModel

    private var countriesAdapter: CountriesAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CountriesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        countriesAdapter = CountriesAdapter { viewModel.input.listItemClicks.accept(it) }
        recycler_view.adapter = countriesAdapter
        val dividerItemDecoration = ListDividerDecoration(context)
        recycler_view.addItemDecoration(dividerItemDecoration)
    }

    override fun onStart() {
        super.onStart()

        bindInput()

        merge(viewModel.commands, viewModel.viewStates)
            .subscribeUntilStopped(::render, ::printStackTrace)

        viewModel.init()
    }

    private fun bindInput() {
        swipe_refresh.setOnRefreshListener { viewModel.input.refreshRequests.accept(Unit) }
    }

    private fun render(viewState: CountriesViewState) {
        when (viewState) {

            is LOADING -> {
                showLoading(true)
            }

            is DATA -> {
                showLoading(false)
                countriesAdapter?.submitList(viewState.countries)
            }

            is ERROR -> {
                showLoading(false)
                showError(viewState.error)
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        swipe_refresh.isRefreshing = loading
    }

    companion object {
        fun newInstance() = CountriesFragment()
    }
}
