package putriiiiiuta.androidlima.challengelimaa

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_halaman_login.*
import putriiiiiuta.androidlima.challengelimaa.model.remote.UserResponse
import putriiiiiuta.androidlima.challengelimaa.utils.AppDataStore
import putriiiiiuta.androidlima.challengelimaa.utils.ViewModelFactory
import putriiiiiuta.androidlima.challengelimaa.vm.ViewModelLogin
import putriiiiiuta.androidlima.challengelimaa.vm.ViewModelPreference
import putriiiiiuta.androidlima.challengelimaa.vm.ViewModelUser

@AndroidEntryPoint
class HalamanLogin : AppCompatActivity() {
    private lateinit var userData: List<UserResponse>
    private lateinit var preferencevm: ViewModelPreference

    private val Context.dataStore by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_login)

        val pref = AppDataStore.getInstance(dataStore)

        preferencevm = ViewModelProvider(this, ViewModelFactory(pref))[ViewModelPreference::class.java]

        preferencevm.getEmail().observe(this) { email ->
            preferencevm.getPassword().observe(this){ password ->
                if (email != "" && password!="") {
                    startActivity(Intent(this, HalamanHome::class.java))
                }
            }
        }

        login_button_login.setOnClickListener{
            when {
                login_input_email.text.toString().isEmpty() -> login_input_email.error = "Isi Email"
                login_input_password.text.toString().isEmpty() -> login_input_password.error = "Isi Password"
                else -> {
                    pb_login.visibility = View.VISIBLE
                    sendLogin(login_input_email.text.toString(), login_input_password.text.toString())
                    getDataUser()
                }
            }
        }

        login_belum_punya_akun.setOnClickListener {
            startActivity(Intent(this, HalamanRegister::class.java))
        }
    }

    private fun loginAuth(listUserResponse: List<UserResponse>){
        val email = login_input_email.text.toString()
        val password = login_input_password.text.toString()

        for(i in listUserResponse.indices){
            if(email==listUserResponse[i].email && password==listUserResponse[i].password){
                Log.d(TAG, "loginAuth: $email, $password, ${listUserResponse[i].id}")
                preferencevm.setEmail(email)
                preferencevm.setPassword(password)
                preferencevm.setId(listUserResponse[i].id.toInt())
                Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HalamanHome::class.java))
                pb_login.visibility = View.INVISIBLE
                finish()
            }
        }
    }

    private fun getDataUser(){
        val userViewModel = ViewModelProvider(this)[ViewModelUser::class.java]
        userViewModel.getUserData()
        userViewModel.getLiveDataUserObserver().observe(this, Observer {
            if(it!=null){
                userData = it
                loginAuth(userData)
            }else{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun sendLogin(email: String, password: String){
        val loginViewModel = ViewModelProvider(this)[ViewModelLogin::class.java]
        loginViewModel.loginData(email, password)
    }

}