package english.com.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView

class EnglishBottomNavigationView(context: Context, attrs: AttributeSet) :
    BottomNavigationView(context, attrs) {

    override fun getMaxItemCount(): Int {
        return 6
    }
}