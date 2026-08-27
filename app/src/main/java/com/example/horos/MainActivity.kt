package com.example.horos
import android.os.Bundle
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Context
import android.Manifest
import java.net.URI
import java.net.URL
import android.net.Uri


class MainActivity : AppCompatActivity() {
	
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val filemanager = FileManager(this)
        val networkmanager = NetworkManager(this)
        val toastmanager = ToastManager(this)
        val parser = Parser(this)

        val filenamesign: String = "sign.txt"
        val filenameresponse: String = "response.txt"
        
    	val btnreload = findViewById<Button>(R.id.ReloadHoros)
    		
    	val texthoros = findViewById<TextView>(R.id.TextViewHoros)

   		val ed_usersign = findViewById<EditText>(R.id.SignUser)
  		
   		val btnsavesign = findViewById<Button>(R.id.SaveSign)
    		
        val btnloadsign = findViewById<Button>(R.id.LoadSign)
		
		btnloadsign.setOnClickListener {
			val resultloading: String? = filemanager.readfile(filenamesign)

			if (resultloading != null) {

				ed_usersign.setText(resultloading)

			} else {

				toastmanager.toast("If you have already saved a sign before please contact the Developper...")
			}
		}
		
		btnsavesign.setOnClickListener {
			val resultwriting = filemanager.writefile(filenamesign, ed_usersign.text.toString())

			if (resultwriting) {

				toastmanager.toast("We successfully save your sign")

			} else {

				toastmanager.toast("We are facing a bug please retry...")
			}
			
		}

		btnreload.setOnClickListener{
			eveything_combined(filemanager, networkmanager, toastmanager, filenamesign, filenameresponse, texthoros, parser)
		}
		 
    }

    fun eveything_combined(filemanager: FileManager, networkmanager: NetworkManager, toastmanager: ToastManager, filenamesign: String, filenameresponse: String, mytexthoros: TextView, myparser: Parser){

		val signauto: String? = filemanager.readfile(filenamesign)

		var date: String? = null
		var period: String? = null
		var sign: String? = null
		var horoscope: String? = null
		var textshow: String? = null

		if (signauto != null) {

			networkmanager.getjsonhoroscope(signauto, filenameresponse) {success ->

				if (success) {

					val responseinstring = filemanager.readfile(filenameresponse)

					if (responseinstring != null) {

						val url: String = showurlstring(signauto)

						val complete_response = responseinstring
						val horoscope = myparser.findhoroscope(responseinstring)
						val horoscopeinstring = myparser.tostring(horoscope) // tostring() delete all the HTML encoded JUNCK

						mytexthoros.text = "site We used to get your horoscope: $url\n \n$horoscopeinstring"

					} else {

						toastmanager.toast("We are facing a bug with the html parsing please retry...")
					}

				} else {


					toastmanager.toast("We are facing a bug with the request please retry")
				}


			}

		}
    }


    fun showurlstring(sign: String): String{
		val text_url: String = "https://mon.astrocenter.fr/horoscope/quotidien/$sign"
		return text_url
	}
    
}
