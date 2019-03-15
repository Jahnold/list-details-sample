package com.jahnold.listdetailssample.base.viewmodel

import io.reactivex.Observable

interface UseCase0<O> {
    fun getAction(): Observable<O>
}

interface UseCase1<in I, O> {
    fun getAction(input: I): Observable<O>
}