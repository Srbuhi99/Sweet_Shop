package com.example.sweetshop.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.sweetshop.R

class AddToBasketAnimUtil() {

    lateinit var fabOpen : Animation
    lateinit var fabClose: Animation
    lateinit var rotateForward: Animation
    lateinit var rotateBackward: Animation

    fun initVariables(context: Context){
        fabOpen = AnimationUtils.loadAnimation(context, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(context, R.anim.fab_close)
        rotateForward = AnimationUtils.loadAnimation(context, R.anim.rotate_forward)
        rotateBackward = AnimationUtils.loadAnimation(context, R.anim.rotate_backward)
    }
}