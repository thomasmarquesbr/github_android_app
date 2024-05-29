package com.thomas.presentation.di

import com.thomas.data.di.dataModule
import com.thomas.domain.di.domainModule

val appModules = dataModule + domainModule + presentationModule
