package putriiiiiuta.androidlima.challengelimaa.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import putriiiiiuta.androidlima.challengelimaa.model.remote.UpdateUserResponse
import putriiiiiuta.androidlima.challengelimaa.network.ApiClient
import putriiiiiuta.androidlima.challengelimaa.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelUpdate @Inject constructor(private val apiService: ApiService) : ViewModel() {

    var liveDataUpdateUserResponse: MutableLiveData<UpdateUserResponse?> = MutableLiveData()

    fun getLiveUpdateObserver(): MutableLiveData<UpdateUserResponse?>{
        return liveDataUpdateUserResponse
    }

    fun updateUser(id : String, coname : String, username: String, address: String, dob : String){
        viewModelScope.launch {
            liveDataUpdateUserResponse.postValue(apiService.updateUser(id, coname, username, address, dob))
        }
        /*ApiClient.instance.updateUser(id, coname, username, address, dob)
            .enqueue(object: Callback<UpdateUserResponse>{
                override fun onResponse(
                    call: Call<UpdateUserResponse>,
                    response: Response<UpdateUserResponse>
                ) {
                    if(response.isSuccessful){
                        liveDataUpdateUserResponse.postValue(response.body())
                    }else{
                        liveDataUpdateUserResponse.postValue(null)
                    }
                }

                override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                    liveDataUpdateUserResponse.postValue(null)
                }

            })*/
    }
}