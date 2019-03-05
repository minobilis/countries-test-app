package rosh.ivan.contries.feature.list

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.lifecycle.ViewModelProviders
import io.reactivex.Observable.merge
import rosh.ivan.contries.R
import rosh.ivan.contries.base.BaseFragment


class CountriesFragment : BaseFragment() {

    override val layoutResourceId: Int get() = R.layout.countries_fragment

    private lateinit var viewModel: MessagesGameViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MessagesGameViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        card_stack_view.layoutManager = manager
        card_stack_view.adapter = adapter
        manager.setVisibleCount(2)
        manager.setScaleInterval(1.0f)
        manager.setMaxDegree(90f)
    }

    override fun onStart() {
        super.onStart()

        bindInput()

        merge(viewModel.viewStates, viewModel.replayableViewStates)
            .subscribeUntilStopped(::render, ::printStackTrace)

        viewModel.init()
    }

    private fun bindInput() {
        close_button.setOnClickListener {
            menuNavController()?.navigateUp()
        }

        dislike_button.setOnClickListener {
            val correctDirection =
                if (manager.topPosition < adapter.data.size && adapter.data[manager.topPosition].correctAnswerIsPositive) {
                    Direction.Right
                } else {
                    Direction.Left
                }
            if (correctDirection == Direction.Left) {
                manager.setSwipeAnimationSetting(
                    SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Left)
                        .build()
                )
                card_stack_view.swipe()
            }
        }

        like_button.setOnClickListener {
            val correctDirection =
                if (manager.topPosition < adapter.data.size && adapter.data[manager.topPosition].correctAnswerIsPositive) {
                    Direction.Right
                } else {
                    Direction.Left
                }
            if (correctDirection == Direction.Right) {
                manager.setSwipeAnimationSetting(
                    SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Right)
                        .build()
                )
                card_stack_view.swipe()
            }
        }

        next_level_button.bindTo(viewModel.input.nextLevelClicks)

        done_button.setOnClickListener { menuNavController()?.navigateUp() }
    }

    private fun render(viewState: MessagesGameViewState?) {
        when (viewState) {

            is LOADING -> {
                setGameElementsVisibility(false)
                setLevelCompleteElementsVisibility(false)
                showLoading(true)
            }

            is DATA -> {
                showLoading(false)
                setGameElementsVisibility(true)
                animateDeckAppearance()
                adapter.updateData(viewState.cards)
                game_level_label.text = viewState.gameLevel.toString()
                progress_bar.progress = 0
            }

            is COMPLETE -> {
                showLoading(false)
                showLevelCompleteScreen(viewState.gameLevel)
            }

            is NEXT -> {
                showLoading(false)
                startNewLevel(viewState.gameLevel)
            }

            is ERROR -> {
                showLoading(false)
                showError(viewState.error)
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        val visibility = if (loading) View.INVISIBLE else View.VISIBLE
        level_frame.visibility = visibility
        game_level_label.visibility = visibility
        dislike_button.visibility = visibility
        like_button.visibility = visibility
        progress_bar.visibility = visibility
        progress_bar.visibility = visibility
        showLoadingSpinner(loading)
    }

    private fun showLoadingSpinner(enabled: Boolean) {
        loading_spinner.visibility = if (enabled) View.VISIBLE else View.INVISIBLE
        loading_shade.visibility = if (enabled) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        fun newInstance() = CountriesFragment()
    }
}
