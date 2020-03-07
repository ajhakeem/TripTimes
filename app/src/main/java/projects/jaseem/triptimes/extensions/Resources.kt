package projects.jaseem.triptimes.extensions

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import projects.jaseem.triptimes.appContext


fun drawable(@DrawableRes resId: Int) : Drawable? =
    if (resId == 0) {
        null
    } else {
        AppCompatResources.getDrawable(appContext, resId)
    }

fun string(@StringRes resId: Int, vararg args: Any): String =
    if (resId == 0) {
        ""
    } else {
        appContext.getString(resId, *args)
    }