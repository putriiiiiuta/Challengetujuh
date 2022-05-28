package putriiiiiuta.androidlima.challengelimaa

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_halaman_profile.*
import kotlinx.coroutines.launch
import putriiiiiuta.androidlima.challengelimaa.model.remote.DetailUserResponse
import putriiiiiuta.androidlima.challengelimaa.utils.AppDataStore
import putriiiiiuta.androidlima.challengelimaa.utils.ViewModelFactory
import putriiiiiuta.androidlima.challengelimaa.vm.ViewModelDetailUser
import putriiiiiuta.androidlima.challengelimaa.vm.ViewModelPreference
import putriiiiiuta.androidlima.challengelimaa.vm.ViewModelUpdate
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HalamanProfile : AppCompatActivity(), View.OnClickListener {

    lateinit var listDetailUserResponse: List<DetailUserResponse>
    private lateinit var preferencevm: ViewModelPreference

    private val Context.dataStore by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_profile)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        title = "Edit Profil"

        val pref = AppDataStore.getInstance(dataStore)

        preferencevm = ViewModelProvider(this, ViewModelFactory(pref))[ViewModelPreference::class.java]

        getDataUser()

        profile_button_logout.setOnClickListener(this)
        btn_botPick.setOnClickListener(this)
        profile_button_update.setOnClickListener(this)
        btn_editPhoto.setOnClickListener(this)
    }

    private fun logout(){
        AlertDialog.Builder(this)
            .setTitle("LOGOUT")
            .setMessage("Yakin Ingin Logout")
            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }.setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
                lifecycleScope.launch {
                    val mIntent = Intent(this@HalamanProfile, HalamanLogin::class.java)
                    val pref = AppDataStore.getInstance(dataStore)
                    startActivity(mIntent)
                    pref.logout()
                    finish()
                }
            }
            .create().show()

    }

    private fun showDatePicker(){
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            c.set(year, monthOfYear, dayOfMonth)
            et_date.setText(dateFormat.format(c.time))
        }, year, month, day)
        datePickerDialog.show()
    }

    private fun initData(listDataUserResponse: List<DetailUserResponse>){
        for(i in listDataUserResponse.indices){
            profile_input_username.setText(listDataUserResponse[i].username)
            profile_input_alamat.setText(listDataUserResponse[i].address)
            profile_input_namalengkap.setText(listDataUserResponse[i].completeName)
            et_date.setText(listDataUserResponse[i].dateofbirth)
        }
    }

    private fun getDataUser(){
        preferencevm.getId().observe(this) {id ->
            val viewModel = ViewModelProvider(this)[ViewModelDetailUser::class.java]
            viewModel.detailUser(id)
            viewModel.getDetailObserver().observe(this, androidx.lifecycle.Observer {
                if(it!=null){
                    listDetailUserResponse=it
                    initData(listDetailUserResponse)
                }else{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    private fun updateDataUser(){
        preferencevm.getId().observe(this) {id ->
            val cname = profile_input_namalengkap.text.toString().trim()
            val uname = profile_input_username.text.toString().trim()
            val address = profile_input_alamat.text.toString().trim()
            val dob = et_date.text.toString()

            val viewModel = ViewModelProvider(this)[ViewModelUpdate::class.java]
            viewModel.updateUser(id.toString(), cname, uname, address, dob)
            viewModel.getLiveUpdateObserver().observe(this, androidx.lifecycle.Observer {
                if(it!=null){
                    Toast.makeText(this, "Gagal Update", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Berhasil Update", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                startActivity(Intent(this, HalamanHome::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200 && resultCode == RESULT_OK) {
            image_profile.setImageURI(data?.data)
        }
    }

    private fun openGalleryForImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        startActivityForResult(intent, 200)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.profile_button_logout -> {
                logout()
            }
            R.id.btn_botPick ->{
                showDatePicker()
            }
            R.id.profile_button_update -> {
                updateDataUser()
            }
            R.id.btn_editPhoto -> {
                openGalleryForImage()
            }
        }
    }
}