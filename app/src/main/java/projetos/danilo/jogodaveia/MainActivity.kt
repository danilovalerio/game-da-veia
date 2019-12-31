package com.example.gameplayveia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import projetos.danilo.jogodaveia.R

class MainActivity : AppCompatActivity() {

    private val jogadorUm = arrayListOf<Int>()
    private val jogadorDois = arrayListOf<Int>()
    private var nomeJogadorUm: String? = "X"
    private var nomeJogadorDois: String? = "O"
    private var pontuacaoTotalJogadorUm: Int = 0
    private var pontuacaoTotalJogadorDois: Int = 0
    private var jogadorDaVez = 1
    private val FRAGMENT_TABULEIRO = "TABULEIRO"
    private val frTabuleiroFragment = TabuleiroFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parseExtras()

        //todo: Trocar a parte central dos botões por um fragment, paara reiniciar somente o fragment
        //implementando fragment
        managerFragment(frTabuleiroFragment, FRAGMENT_TABULEIRO)
    }

    private fun parseExtras(){
        val extras = intent.extras

        if (extras != null) {
            nomeJogadorUm = extras.getString("nomeJogadorUm")
            nomeJogadorDois = extras.getString("nomeJogadorDois")

            atualizaNomeDoJogadorAtual(1)
        } else {
            atualizaNomeDoJogadorAtual(1)
        }
        placarJogo.setText(resources.getString(R.string.placar_do_jogo, nomeJogadorUm, pontuacaoTotalJogadorUm.toString(), pontuacaoTotalJogadorDois.toString(), nomeJogadorDois))
    }

    fun jogar(posicao: Int, btnSelecionado: Button) {
        if (jogadorDaVez == 1) {
            btnSelecionado.text = "X"
            btnSelecionado.setBackgroundResource(R.color.colorJogadorUm)
            jogadorUm.add(posicao)
            jogadorDaVez = 2
        } else {
            btnSelecionado.text = "O"
            btnSelecionado.setBackgroundResource(R.color.colorJogadorDois)
            jogadorDois.add(posicao)
            jogadorDaVez = 1
        }

        atualizaNomeDoJogadorAtual(jogadorDaVez)

        btnSelecionado.isClickable = false
        verificaResultado()
    }

    //pega a posicão do botão pressionado e envia para jogar
    fun btnPosicao(view: View) = when(view.id){
        R.id.btn1 -> jogar(1, view as Button)
        R.id.btn2 -> jogar(2, view as Button)
        R.id.btn3 -> jogar(3, view as Button)
        R.id.btn4 -> jogar(4, view as Button)
        R.id.btn5 -> jogar(5, view as Button)
        R.id.btn6 -> jogar(6, view as Button)
        R.id.btn7 -> jogar(7, view as Button)
        R.id.btn8 -> jogar(8, view as Button)
        R.id.btn9 -> jogar(9, view as Button)
        else -> jogar(0, view as Button)
    }

    fun verificaResultado(){
        var vencedor = -1
        val lin1 = listOf(1,2,3)
        val lin2 = listOf(4,5,6)
        val lin3 = listOf(7,8,9)

        val col1 = listOf(1,4,7)
        val col2 = listOf(2,5,8)
        val col3 = listOf(3,6,9)

        val diag1 = listOf(1,5,9)
        val diag2 = listOf(3,5,7)

        if(jogadorUm.containsAll(lin1) || jogadorUm.containsAll(lin2) || jogadorUm.containsAll(lin3) ||
            jogadorUm.containsAll(col1) || jogadorUm.containsAll(col2) || jogadorUm.containsAll(col3) ||
            jogadorUm.containsAll(diag1) || jogadorUm.containsAll(diag2)){
            vencedor = 1
        }

        if(jogadorDois.containsAll(lin1) || jogadorDois.containsAll(lin2) || jogadorDois.containsAll(lin3) ||
            jogadorDois.containsAll(col1) || jogadorDois.containsAll(col2) || jogadorDois.containsAll(col3) ||
            jogadorDois.containsAll(diag1) || jogadorDois.containsAll(diag2)){
            vencedor = 2
        }

        when(vencedor){
            1 -> {
                pontuacaoTotalJogadorUm++
//                toastShort(this, "Parabéns: $nomeJogadorUm, VOCÊ VENCEU!")
//                val resp = alertDialogPersonal(this,"FIM DO JOGO",
//                    "Parabéns: $nomeJogadorUm, VOCÊ VENCEU!",
//                    "SIM",
//                    "NÃO"
//                    )
//
//                toastLong(this,resp.toString())
            }
            2 -> {
                pontuacaoTotalJogadorDois++
                toastShort(this, "Parabéns: $nomeJogadorDois, VOCÊ VENCEU!")
            }
        }

        val nomeVencedor = if(vencedor == 1) nomeJogadorUm else nomeJogadorDois

        if(vencedor != -1){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Parabéns: $nomeVencedor, VOCÊ VENCEU!")
            builder.setMessage("\n\nJOGAR OUTRA PARTIDA?")
            builder.setPositiveButton("Sim") { dialog, id ->
                //Reinicia o Fragment para nova partida
                reiniciarJogoNoCliquePositivo()
                //Toast.makeText(this, "Clicou no Sim", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Não") { dialog, id ->
                //Encerrar o app ao clicar em não
                //Toast.makeText(this, "Clicou no Não", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CadastraJogadoresActivity::class.java)

//                intent.putExtra("nomeJogadorUm", jogadorUm.text.toString())
//                intent.putExtra("nomeJogadorDois", jogadorDois.text.toString())
                startActivity(intent)
                finish()
            }
            builder.show()
        }

        Log.i("PONTUACAO", "jogador 1: "+pontuacaoTotalJogadorUm+" X "+ pontuacaoTotalJogadorDois+" :jogador 2")
        placarJogo.setText(resources.getString(R.string.placar_do_jogo, nomeJogadorUm, pontuacaoTotalJogadorUm.toString(), pontuacaoTotalJogadorDois.toString(), nomeJogadorDois))
    }

    fun reiniciarJogo(view: View) {
        Log.i("PONTUACAO", view.toString())
        jogadorUm.clear()
        jogadorDois.clear()
        parseExtras()
        val reloadTabuleiroFragment = TabuleiroFragment()
        managerFragment(reloadTabuleiroFragment, FRAGMENT_TABULEIRO)
    }

    fun reiniciarJogoNoCliquePositivo() {
        jogadorUm.clear()
        jogadorDois.clear()
        parseExtras()
        val reloadTabuleiroFragment = TabuleiroFragment()
        managerFragment(reloadTabuleiroFragment, FRAGMENT_TABULEIRO)
    }

    fun atualizaNomeDoJogadorAtual(jogadorDaVez: Int){

        when (jogadorDaVez) {
            1 -> {
                tv_JogadorDaVez.setText(resources.getString(R.string.jogador_atual, nomeJogadorUm))
            }

            2 -> {
                tv_JogadorDaVez.setText(resources.getString(R.string.jogador_atual, nomeJogadorDois))
            }
        }
    }

    //Gerenciador de fragments
    private fun managerFragment(fragment: Fragment, tag: String){
        val fragmentManager: FragmentManager = getSupportFragmentManager()
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerForFragment, fragment, tag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}

