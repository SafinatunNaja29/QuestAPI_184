package com.example.p12_.dependenciesinjection

import com.example.p12_.repository.MahasiswaRepository


interface AppContainer{
    val mahasiswaRepository: MahasiswaRepository
}

