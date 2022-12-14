package com.yes.playlistfeature.di.module
import android.app.Dialog
import androidx.fragment.app.DialogFragment
import com.yes.trackdiialogfeature.presentation.TrackDialog
import dagger.Module
import dagger.Provides
@Module
class PlayListModule {
    @Provides
    fun provideTrackDialog(): DialogFragment {
        return TrackDialog()
    }
}