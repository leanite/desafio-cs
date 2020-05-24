package com.github.leanite.core.ui.headerview

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.view_header.view.*

class HeaderView : ConstraintLayout {
    var title: String? = ""
        set(value) {
            field = value
            tvTitle.text = value
        }

    var description: String? = ""
        set(value) {
            field = value
            tvDescription.text = value
        }

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_header, this)
    }

    fun loadImage(url: String) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.placeholder_download)
            .error(R.drawable.placeholder_error)
            .apply(RequestOptions().circleCrop())
            .into(ivUserProfile)
    }
}