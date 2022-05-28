package putriiiiiuta.androidlima.challengelimaa.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import putriiiiiuta.androidlima.challengelimaa.model.remote.UserResponse
import putriiiiiuta.androidlima.challengelimaa.network.ApiClient
import putriiiiiuta.androidlima.challengelimaa.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class ViewModelUser @Inject constructor(private val apiService: ApiService) : ViewModel() {

    var liveDataUser : MutableLiveData<List<UserResponse>?> = MutableLiveData()


    fun getLiveDataUserObserver() : MutableLiveData<List<UserResponse>?> {
        return liveDataUser
    }

    fun getUserData(){
        viewModelScope.launch {
            liveDataUser.postValue(apiService.listUser())
            /*ApiClient.instance.listUser()
                .enqueue(object : Callback<List<UserResponse>>{
                    override fun onResponse(
                        call: Call<List<UserResponse>>,
                        response: Response<List<UserResponse>>
                    ) {
                        if(response.isSuccessful){
                            liveDataUser.postValue(response.body())
                        }else{
                            liveDataUser.postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                        liveDataUser.postValue(null)
                    }

                })*/
        }
    }

}