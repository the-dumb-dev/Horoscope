package com.example.horos

import android.os.Build
import android.os.Bundle
import android.content.Context
import java.net.HttpURLConnection
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.URI
import java.net.URL
import kotlin.text.Charsets
import java.lang.StringBuilder
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.net.Uri


class NetworkManager(private val mycontext: Context){

	val filemanager = FileManager(mycontext)


	fun addargstourl(sign: String): URL{
		val text_url: String = "https://mon.astrocenter.fr/horoscope/quotidien/$sign"
		return URI.create(text_url).toURL()
	}


	fun getjsonhoroscope(usersign: String, filename: String, OnResult: (Boolean) -> Unit){

		val handler: Handler = Handler(Looper.getMainLooper())

		Thread {

			var connection: HttpURLConnection? = null

			try {

				val url: URL = addargstourl(usersign)

				connection = url.openConnection() as HttpURLConnection

				connection.requestMethod = "GET"

				val responseCode: Int = connection.responseCode

				if (responseCode in 200..299) {

					try {

						val reader: BufferedReader? = BufferedReader(InputStreamReader(connection.inputStream))

						var line: String? = null

						var response = StringBuilder()

						while (reader?.readLine().also {line = it} != null) {

							response.append(line)
						}
						
						val succeswriting = filemanager.writefile(filename, response.toString())

						if (succeswriting) {

							handler.post {
								OnResult(true)
							}

						} else {

							handler.post{
								OnResult(false)
							}

						}
						

					} catch (e: Exception) {


						Log.e("NetworkManager", "Error while reading response", e)
						
						handler.post{
							OnResult(false)
						}

					}

				} else {

					val reader: BufferedReader = BufferedReader(InputStreamReader(connection.errorStream))

					var response: String = ""

					response = reader.readText()

					reader.close()


					val succeswriting = filemanager.writefile(filename, response)

					if (succeswriting) {

						handler.post {
							OnResult(true)
						}

					} else {

						handler.post{
							OnResult(false)
						}

					}

				}



			} catch (e: Exception) {

				Log.e("NetworkManager", "Error while doing connection", e)

				handler.post{
					OnResult(false)
				}

			} finally {

				connection?.disconnect()

			}

		}.start()

		
	}




}

