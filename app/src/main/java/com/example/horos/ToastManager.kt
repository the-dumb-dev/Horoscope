package com.example.horos

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.graphics.Color
import android.content.Context



class ToastManager(private val mycontext: Context){

	fun toast(text: String){
		val toast = Toast.makeText(mycontext, text, Toast.LENGTH_SHORT)
		
		return toast.show()
	}
}
