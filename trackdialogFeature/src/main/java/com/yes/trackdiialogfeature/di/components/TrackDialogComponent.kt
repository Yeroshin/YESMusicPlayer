package com.yes.trackdiialogfeature.di.components

import com.yes.trackdiialogfeature.di.module.TrackDialogModule
import com.yes.trackdiialogfeature.presentation.TrackDialog
import dagger.Component

@Component(modules = [TrackDialogModule::class])
interface TrackDialogComponent {
    fun inject(dialog:TrackDialog)
}