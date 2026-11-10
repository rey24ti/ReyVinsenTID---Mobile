package com.example.rey3tie_mobiel.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rey3tie_mobiel.R

class TabBFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menghubungkan ke layout fragment_tab_b
        return inflater.inflate(R.layout.fragment_tab_b, container, false)
    }
}