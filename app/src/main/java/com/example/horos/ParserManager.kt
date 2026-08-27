package com.example.horos

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.content.Context


class Parser(private val mycontext: Context) {

	fun findhoroscope(input: String): String{
		val matchs = Regex("""<p\b[^>]*>(.*?)</p>""", RegexOption.DOT_MATCHES_ALL)
			.findAll(input.trimIndent())
			.map {it.value}
			.toList()

		return matchs.getOrElse(1){
			"error: We faced a bug while parsing..."
		}


	}

	fun tostring(input: String): String {
		val text = Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY).toString()
		return text
	}
}


