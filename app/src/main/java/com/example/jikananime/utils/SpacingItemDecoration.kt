package com.example.jikananime.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)

    outRect.top = space
    outRect.left = space
    outRect.right = space
    outRect.bottom = space

  }
}

