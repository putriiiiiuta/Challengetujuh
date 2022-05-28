package putriiiiiuta.androidlima.challengelimaa.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import putriiiiiuta.androidlima.challengelimaa.utils.AppDataStore

class ViewModelPreference(private val pref: AppDataStore): ViewModel() {

    fun getEmail(): LiveData<String>{
        return pref.getEmail().asLiveData()
    }

    fun getId(): LiveData<Int>{
        return pref.getId().asLiveData()
    }

    fun getPassword(): LiveData<String>{
        return pref.getPassword().asLiveData()
    }

    fun setEmail(email: String){
        viewModelScope.launch {
            pref.setEmail(email)
        }
    }

    fun setPassword(password: String){
        viewModelScope.launch {
            pref.setPassword(password)
        }
    }

    fun setId(id: Int){
        viewModelScope.launch {
            pref.setId(id)
        }
    }
}