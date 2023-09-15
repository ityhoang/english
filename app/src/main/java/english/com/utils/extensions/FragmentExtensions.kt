package english.com.utils.extensions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.DimenRes
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@RequiresApi(Build.VERSION_CODES.R)
fun Fragment.openAppSettingsForFileAccess() {
    val intent = Intent(
        Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION,
        Uri.fromParts("package", requireActivity().packageName, null)
    )
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

fun Fragment.checkStoragePermission(listener: () -> Unit) = when {
    ContextCompat.checkSelfPermission(
        this.requireActivity(),
        Manifest.permission.MANAGE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED -> {
        listener.invoke()
    }

    ActivityCompat.shouldShowRequestPermissionRationale(
        this.requireActivity(),
        Manifest.permission.MANAGE_EXTERNAL_STORAGE
    ) -> {

    }
    else -> {
        requestPermissions(
            arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE),
            1
        )
    }
}

fun baseGridLayoutManager(context: Context, spanCount: Int): GridLayoutManager {
    return GridLayoutManager(context, spanCount, RecyclerView.VERTICAL, false).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = 1
        }
    }
}

fun baseLinearLayoutManager(context: Context, orientation: Int): LinearLayoutManager {
    return LinearLayoutManager(context, orientation, false)
}

fun Fragment.toDp(@DimenRes dimen: Int) = resources.getDimensionPixelSize(dimen)


@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T> Fragment.getArguments() = arguments?.get(T::class.java.name) as T

//fun Fragment.navigate(directions: NavDirections) {
//    findNavController().navigate(directions, navOptions)
//}