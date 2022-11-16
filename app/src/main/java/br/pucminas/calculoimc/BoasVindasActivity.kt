package br.pucminas.calculoimc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.pucminas.calculoimc.databinding.ActivityBoasVindasBinding

class BoasVindasActivity : AppCompatActivity(){

    private lateinit var binding: ActivityBoasVindasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoasVindasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        solicitarCalculo()

    }

    fun solicitarCalculo(){

       val btnSolicitar = binding.btnSolicitarCalculo
        btnSolicitar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}