package com.melscience.presentation.main.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import com.melscience.R
import kotlinx.android.synthetic.main.two_text_view.view.*

class TwoTextView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  init {
    val view = LayoutInflater.from(context)
      .inflate(R.layout.two_text_view, null, false)

    addView(view)

    context.withStyledAttributes(attrs, R.styleable.TwoTextView) {
      val title = getString(R.styleable.TwoTextView_ttv_title)
        .orEmpty()

      setTitle(title)
    }
  }

  private fun setTitle(title: String) {
    tvTitle?.text = title
  }

  fun setSelectedText(text: String) {
    tvSelectedText?.text = text
  }
}