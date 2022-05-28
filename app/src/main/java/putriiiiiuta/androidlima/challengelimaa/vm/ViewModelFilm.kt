package putriiiiiuta.androidlima.challengelimaa.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import putriiiiiuta.androidlima.challengelimaa.model.FilmRepository
import putriiiiiuta.androidlima.challengelimaa.model.remote.FilmResponse
import putriiiiiuta.androidlima.challengelimaa.network.ApiClient
import putriiiiiuta.androidlima.challengelimaa.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelFilm @Inject constructor(private val apiService: ApiService) : ViewModel()  {

    var liveDataFilm : MutableLiveData<List<FilmResponse>?> = MutableLiveData()

    fun getLiveFilmObserver() : MutableLiveData<List<FilmResponse>?> {
        return liveDataFilm
    }

    fun makeApiFilm(){
        viewModelScope.launch {
            liveDataFilm.postValue(apiService.GetDatafilm())
        }
        /*ApiClient.instance.GetDatafilm()
            .enqueue(object : Callback<List<FilmResponse>> {
                override fun onResponse(
                    call: Call<List<FilmResponse>>,
                    response: Response<List<FilmResponse>>
                ) {
                    if (response.isSuccessful){
                        liveDataFilm.postValue(response.body())
                    }else{
                        liveDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<FilmResponse>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }

            })*/

    }

}