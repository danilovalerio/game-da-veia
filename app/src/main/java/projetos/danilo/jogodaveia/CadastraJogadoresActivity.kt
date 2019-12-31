package com.example.gameplayveia

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastra_jogadores.*
import projetos.danilo.jogodaveia.R

class CadastraJogadoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastra_jogadores)

        bindView()
    }

    private fun parseIntentExtras() {
        val intent = Intent(this, MainActivity::class.java)

        intent.putExtra("nomeJogadorUm", jogadorUm.text.toString())
        intent.putExtra("nomeJogadorDois", jogadorDois.text.toString())
        startActivity(intent)
    }

    private fun bindView() {
        btnJogar.setOnClickListener {
            if (validaNomeJogadores()) {
                parseIntentExtras()
            }
        }
    }

    private fun validaNomeJogadores(): Boolean {
        if (verificaCampoNuloOuVazio(jogadorUm) && verificaCampoNuloOuVazio(jogadorDois)) {
            return true
        }
        return false
    }

    private fun verificaCampoNuloOuVazio(texto: TextView): Boolean {
        if (texto.text.isNullOrEmpty()) {
            texto.setError(getString(R.string.campo_obrigatorio))
            return false
        } else {
            texto.setError(null)
            return true
        }
    }
}
