package projects.jaseem.triptimes.domain.extensions

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import projects.jaseem.triptimes.appContext


fun drawable(@DrawableRes resId: Int) : Drawable? =
    if (resId == 0) {
        null
    } else {
        AppCompatResources.getDrawable(appContext, resId)
    }