package putriiiiiuta.androidlima.challengelimaa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_halaman_register.*
import putriiiiiuta.androidlima.challengelimaa.vm.ViewModelRegister

@AndroidEntryPoint
class HalamanRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_register)

        button_toLogin.setOnClickListener{
            startActivity(Intent(this, HalamanLogin::class.java))
        }

        register_button_daftar.setOnClickListener {
            if (register_input_username.text!!.isNotEmpty() &&
                register_input_email.text!!.isNotEmpty() &&
                register_input_password.text!!.isNotEmpty() &&
                register_input_konfirmasi_password.text!!.isNotEmpty()
            ) {
                if (register_input_password.text.toString() != register_input_konfirmasi_password.text.toString()) {
                    Toast.makeText(this, "Password dan konfirmasi harus sama", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    postRegister()
                    finish()
                }

            } else {
                Toast.makeText(this, "Semua data belum terisi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun postRegister(){
        val username = register_input_username.text.toString()
        val email = register_input_email.text.toString()
        val password = register_input_password.text.toString()
        val viewModel = ViewModelProvider(this).get(ViewModelRegister::class.java)
        viewModel.getliveRegisterObserver().observe(this, Observer {
            if (it != null){
                Toast.makeText(this, "Register Sukses", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.RegisterApi(username, email, password)
    }
}