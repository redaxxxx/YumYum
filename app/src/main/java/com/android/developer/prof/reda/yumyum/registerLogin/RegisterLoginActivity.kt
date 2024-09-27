package com.android.developer.prof.reda.yumyum.registerLogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.developer.prof.reda.yumyum.R
import com.android.developer.prof.reda.yumyum.databinding.ActivityRegisterLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class RegisterLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_login)


    }
}