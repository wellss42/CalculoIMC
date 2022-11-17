package br.pucminas.calculoimc

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import br.pucminas.calculoimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var avaliacao = "Exemplo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enviarDadosDeCalculo()

    }

    private fun enviarDadosDeCalculo() {
        val botaoCalcular = binding.btnCacular
        botaoCalcular.setOnClickListener {
            val altura = binding.altura.text.toString()
            val peso = binding.pesoEmKg.text.toString()
            if (altura.isNotBlank() && peso.isNotBlank() && validarIdade().isNotBlank() && altura != "0" && peso != "0") {
                val calculo = calculaImc(altura = altura, peso = peso)
                avaliacao = avaliacaoImc(calculo).toString()
                val idade = validarIdade()
                val intent = Intent(this, ResultadoActivity::class.java).apply {
                    putExtra(TAG, avaliacao)
                    putExtra(VAL, calculo)
                    putExtra(IDA, idade)
                }
                startActivity(intent)
            }
        }
    }

    private fun calculaImc(altura: String, peso: String): Double {
        val alturaDouble = toDouble(altura)
        val pesoDouble = toDouble(peso)
        return pesoDouble.div(alturaDouble.times(alturaDouble))
    }

    private fun validarIdade(): String {
        val radio = binding.rbClassificacaoIdade
        val selectedId = radio.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(selectedId).text
        return radioButton.toString()

    }

    private fun avaliacaoImc(calculo: Double): String? {
        val nome = binding.nome.text.toString()
        if (validarIdade() == "Adulto") {
            return when (calculo) {
                in 0.0..18.499 -> getString(R.string.baixoPesoAdulto, nome, calculo.toString().substring(0,5))
                in 18.5..24.999 -> getString(R.string.pesoNormalAdulto, nome, calculo.toString().substring(0,5))
                in 25.0..29.999 -> getString(R.string.sobrePesoAdulto, nome, calculo.toString().substring(0,5))
                in 30.0..34.999 -> getString(R.string.classe1, nome, calculo.toString().substring(0,5))
                in 35.0..39.999 -> getString(R.string.classe2, nome, calculo.toString().substring(0,5))
                else -> getString(R.string.classe3, nome, calculo.toString().substring(0,5))
            }
        } else {
            return when (calculo) {
                in 0.0..21.999 -> getString(R.string.baixoPesoIdoso, nome, calculo.toString().substring(0,5))
                in 22.0..27.0 -> getString(R.string.pesoNormalIdoso, nome, calculo.toString().substring(0,5))
                else -> getString(R.string.sobrePesoIdoso, nome, calculo.toString().substring(0,5))
            }
        }
    }

    private fun toDouble(s: String): Double {
        return s.toDouble()
    }

    companion object {
        const val TAG = "PARAMETER1"
        const val VAL = "PARAMETER2"
        const val IDA = "PARAMETER3"
    }

}
