package com.jhonny.texttospeach

import android.support.v4.app.Fragment
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), TextToSpeech.OnInitListener {

    var tts : TextToSpeech? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val vistaRaiz = inflater.inflate(R.layout.fragment_main, container, false)

        val edText = vistaRaiz.findViewById<EditText>(R.id.edText)
        val btLeer = vistaRaiz.findViewById<Button>(R.id.btLeer)

        tts = TextToSpeech(context,this)

        btLeer.setOnClickListener {
            var texto = edText.text.toString()
            if (texto.isEmpty()){
                texto = "maldito subnormal. ¿Dime como quieres que lea el texto si no escribes nada?"
            }
            //Toast.makeText(context,"clic",Toast.LENGTH_SHORT).show()
            tts!!.speak(texto,TextToSpeech.QUEUE_FLUSH,null,null)
        }

        return vistaRaiz
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale("es_MX"))
            if(result != TextToSpeech.LANG_NOT_SUPPORTED || result != TextToSpeech.LANG_MISSING_DATA){

            }else{
                Toast.makeText(context,"no soportado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}
