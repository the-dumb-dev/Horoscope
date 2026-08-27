package com.example.horos

import android.os.Build
import android.os.Bundle
import java.io.File
import android.os.Environment
import android.util.Log
import android.content.Context


class FileManager(private val mycontext: Context){

	fun readfile(filename: String): String? {

		try {

			val pathr: File? = mycontext.getFilesDir()

			if (pathr != null) {

				val myfile: File = File(pathr, filename)

				val contentread: String? = myfile.readText() ?: ""

				return contentread

			} else {

				return null
			}

		} catch (e: Exception) {

			Log.e("FileManager", "Error while reading", e)

			return null
		}
	}


	fun writefile(filename: String, content: String): Boolean {

		try {

			val path: File? = mycontext.getFilesDir()

			if (path != null) {

				val file: File = File(path, filename)

				file.writeText(content)

				return true

			} else {

				return false

			}

		} catch (e: Exception) {

			Log.e("FileManager", "Error while writing", e)
			return false

		}
	}

}