package putriiiiiuta.androidlima.challengelimaa.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import putriiiiiuta.androidlima.challengelimaa.vm.ViewModelPreference

class ViewModelFactory(private val pref: AppDataStore): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelPreference::class.java)) {
            return ViewModelPreference(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}