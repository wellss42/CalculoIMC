package br.pucminas.calculoimc

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.pucminas.calculoimc.MainActivity.Companion.IDA
import br.pucminas.calculoimc.MainActivity.Companion.TAG
import br.pucminas.calculoimc.MainActivity.Companion.VAL
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
        setCor()
    }

    private fun setCor(){
        val idade = intent.getStringExtra(IDA)
        if (idade.toString() == "Adulto") {
            recuperaCorImcAdulto()
        } else {
            recuperaCorImcIdoso()
        }
    }

    private fun recuperaCorImcIdoso() {
        val calculo = intent.getDoubleExtra(VAL, 0.0)
        var cor = when (calculo) {
            in 0.0..21.999 -> getColor(R.color.orange)
            in 22.0..27.0 -> getColor(R.color.green)
            else -> getColor(R.color.red)
        }
        binding.texto.setTextColor(cor)
    }

    private fun recuperaCorImcAdulto() {
        val calculo = intent.getDoubleExtra(VAL, 0.0)
            var cor = when (calculo) {
                in 0.0..18.499 -> getColor(R.color.orange)
                in 18.5..24.999 -> getColor(R.color.green)
                in 25.0..29.999 -> getColor(R.color.orange)
                in 30.0..34.999 -> getColor(R.color.red)
                in 35.0..39.999 -> getColor(R.color.red)
                else -> getColor(R.color.red)
        }
        binding.texto.setTextColor(cor)
    }

}