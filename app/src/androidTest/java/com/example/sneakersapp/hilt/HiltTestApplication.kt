package com.example.sneakersapp.hilt

import com.example.sneakersapp.BaseApplication
import dagger.hilt.android.testing.CustomTestApplication

@CustomTestApplication(BaseApplication::class)
interface HiltTestApplication