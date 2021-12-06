package com.demo.android.henrique.rickandmotyapp.utils

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController

interface OnItemClickListener {
    fun onClick(view: View)
}

//object AAAA {
//    fun bbb(view: View, resourceId: Int, bundle: Bundle) {
//        view.findNavController().navigate(resourceId, bundle)
//    }
//}
//
//private val onItemClick: (View, Bundle, Int) -