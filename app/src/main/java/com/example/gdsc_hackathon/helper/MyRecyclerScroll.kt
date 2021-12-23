package com.example.gdsc_hackathon.helper

import androidx.recyclerview.widget.RecyclerView

abstract class MyRecyclerScroll : RecyclerView.OnScrollListener() {
    var scrollDist = 0
    private var isVisible = true

    //    We dont use this method because its action is called per pixel value change
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        //  Check scrolled distance against the minimum
        if (isVisible && scrollDist > HIDE_THRESHOLD) {
            //  Hide fab & reset scrollDist
            hide()
            scrollDist = 0
            isVisible = false
        } else if (!isVisible && scrollDist < -SHOW_THRESHOLD) {
            //  Show fab & reset scrollDist
            show()
            scrollDist = 0
            isVisible = true
        }

        //  Whether we scroll up or down, calculate scroll distance
        if (isVisible && dy > 0 || !isVisible && dy < 0) {
            scrollDist += dy
        }
    }

    abstract fun show(

    )
    abstract fun hide()

    companion object {
        private const val HIDE_THRESHOLD = 100f
        private const val SHOW_THRESHOLD = 50f
    }
}