package com.juandou.mediapikcer.filter

interface IFilter<T> {
    fun doFilter(t: T)
}