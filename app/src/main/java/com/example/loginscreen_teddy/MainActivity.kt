package com.example.loginscreen_teddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import app.rive.runtime.kotlin.core.Rive
import com.example.loginscreen_teddy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private val stateMachineName ="State Machine 1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        Rive.init(this)

        binding.email.setOnFocusChangeListener{ view ,b->
        if(b){
            binding.loginCharacter.controller.setBooleanState(stateMachineName, "Check", true)
        }else{
            binding.loginCharacter.controller.setBooleanState(stateMachineName, "Check", false)
        }
         }

        binding.password.setOnFocusChangeListener{ view ,b->
            if(b){
                binding.loginCharacter.controller.setBooleanState(stateMachineName, "hands_up", true)
            }else{
                binding.loginCharacter.controller.setBooleanState(stateMachineName, "hands_up", false)
            }
        }


        binding.email.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                try {
                    binding.loginCharacter.controller.setNumberState(stateMachineName, "Look", p0!!.length.toFloat())
                } catch (e: Exception){

                }

            }

        })
        binding.button.setOnClickListener{

            binding.password.clearFocus()

             Handler(mainLooper).postDelayed({

                 if(binding.email.text!!.isNotEmpty()&& binding.password.text!!.isNotEmpty() &&
                     (  binding.email.text.toString()=="Teddy@gmail.com"  && binding.password.text.toString()=="Teddy#123" )){

                     binding.loginCharacter.controller.fireState(stateMachineName, "success");

                 }else{
                     binding.loginCharacter.controller.fireState(stateMachineName, "fail");
                 }
             },1500)



        }
    }
}