package gohleng.apps.finch_route_station.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import gohleng.apps.finch_route_station.R
import gohleng.apps.finch_route_station.api.RetrofitBuilder
import gohleng.apps.finch_route_station.api.Station
import gohleng.apps.finch_route_station.api.Stop
import gohleng.apps.finch_route_station.main.ui.RouteListAdapter
import gohleng.apps.finch_route_station.main.ui.StationStopAdapter
import gohleng.apps.finch_route_station.main.util.RouteOnItemClickListener
import gohleng.apps.finch_route_station.main.util.StationStopOnItemClickListener
import gohleng.apps.finch_route_station.main.util.ViewAnimator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.overlay_route_card.*

class MainActivity : AppCompatActivity() {

    private var stationStopAdapter: StationStopAdapter? = null
    private var routeListAdapter: RouteListAdapter? = null
    private var stationStopOnItemClickListener: StationStopOnItemClickListener? = null
    private var routeOnItemClickListener: RouteOnItemClickListener? = null
    private var selectedStationStop: View? = null

    private val animator = ViewAnimator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        initializeRetrofit()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initializeViews() {
        stationStopOnItemClickListener =
            StationStopOnItemClickListener(onItemClickListener = { stop, view, bitmap ->
                rvStops.setOnTouchListener { _, _ ->
                    return@setOnTouchListener true
                }
                selectedStationStop = view
                populateOverlayCard(stop, bitmap)
                animateCard(view)
            })

        routeOnItemClickListener =
            RouteOnItemClickListener(onItemClickListener = { _, view ->
                if (view.isExpanded) view.collapse() else view.expand()
            })

        iconClose.setOnClickListener {
            rvStops.setOnTouchListener(null)
            animator.resizeOverlayViewImage(
                imgStationOverlay,
                resources.getDimension(R.dimen.dimen_default_card_image_max_height).toInt(),
                animator.millisNormal
            )
            animator.resizeOverlayMargin(
                cardDuplicate,
                resources.getDimension(R.dimen.dimen_default_margin).toInt(),
                animator.millisNormal
            )
            animator.resizeCardRadius(
                cardDuplicate,
                resources.getDimension(R.dimen.dimen_default_margin).toInt(),
                animator.millisNormal
            )
            animator.resizeOverlayView(
                cardDuplicate,
                selectedStationStop!!.height,
                animator.millisNormal
            )
            animator.animateAlpha(contDuplicate, 1f, 0f, animator.millisNormal * 2)
            animator.animateViewTransY(
                selectedStationStop!!,
                -selectedStationStop!!.top.toFloat() + resources.getDimension(R.dimen.dimen_default_margin),
                0f,
                animator.millisNormal * 3,
                null
            )
        }

        imgStationOverlay.setOnClickListener {}
    }

    private fun initializeRetrofit() {
        val disposable = CompositeDisposable()
        disposable.add(
            RetrofitBuilder.buildRetrofit().getRouteDetail()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onError(t) })
        )
    }

    private fun populateOverlayCard(stop: Stop, bitmap: Bitmap) {
        imgStationOverlay.setImageBitmap(bitmap)
        txtNameOverlay.text = stop.name
        txtAgencyOverlay.text = stop.agency

        rvRoute.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            routeListAdapter = RouteListAdapter(stop.routes, routeOnItemClickListener!!)
            adapter = routeListAdapter
        }

        if (stop.routes.isEmpty()) {
            rvRoute.visibility = GONE
            txtEmptyState.visibility = VISIBLE
        } else {
            rvRoute.visibility = VISIBLE
            txtEmptyState.visibility = GONE
        }
    }

    private fun animateCard(view: View) {
        val listener = object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                animator.animateAlpha(contDuplicate, 0f, 1f, 0)
                animator.resizeOverlayView(
                    cardDuplicate,
                    contScreen.bottom,
                    animator.millisNormal
                )
                animator.resizeOverlayViewImage(
                    imgStationOverlay,
                    resources.getDimension(R.dimen.dimen_default_inflated_image_size).toInt(),
                    animator.millisNormal
                )
                animator.resizeOverlayMargin(cardDuplicate, 0, animator.millisNormal)
                animator.resizeCardRadius(cardDuplicate, 0, animator.millisNormal)
            }
        }

        val param = cardDuplicate.layoutParams
        param.height = view.height
        animator.animateViewTransY(
            view,
            0f, view.top.toFloat() - resources.getDimension(R.dimen.dimen_default_margin),
            0L, listener
        )
    }

    private fun onResponse(response: Station) {
        rvStops.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            stationStopAdapter =
                StationStopAdapter(response.stops, stationStopOnItemClickListener!!)
            adapter = stationStopAdapter
        }
    }

    private fun onError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }
}