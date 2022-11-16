package br.pucminas.calculoimc

import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.pucminas.calculoimc.MainActivity.Companion.TAG
import br.pucminas.calculoimc.databinding.ActivityResultadoBinding

class ResultadoActivity: AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        consultaResultadoCalculo()

    }

    fun consultaResultadoCalculo(){
        val avaliacao = intent.getStringExtra(TAG)
        binding.texto.text = avaliacao

    }
}