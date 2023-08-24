import android.content.Context

object PreferenceHelper {
    private const val PREFERENCE_NAME = "MyAppPreference"
    private const val KEY_IS_LOGGED_IN = "isLoggedIn"

    fun setLoggedInStatus(context: Context, isLoggedIn: Boolean) {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }
}
