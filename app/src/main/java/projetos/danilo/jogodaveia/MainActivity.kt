package projetos.danilo.jogodaveia

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gameplayveia.alertDialogPersonal
import com.example.gameplayveia.toastLong
import com.example.gameplayveia.toastShort
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val jogadorUm = arrayListOf<Int>()
    private val jogadorDois = arrayListOf<Int>()
    private var nomeJogadorUm: String? = "X"
    private var nomeJogadorDois: String? = "O"
    private var pontuacaoTotalJogadorUm: Int = 0
    private var pontuacaoTotalJogadorDois: Int = 0
    private var jogadorDaVez = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val extras = intent.extras

        if (extras != null) {
            nomeJogadorUm = extras!!.getString("nomeJogadorUm")
            nomeJogadorDois = extras!!.getString("nomeJogadorDois")

            atualizaNomeDoJogadorAtual(1)

            val msg = nomeJogadorUm+" X "+nomeJogadorDois

            alertDialogPersonal(this, "PLAYERS", msg,null, null)

            toastLong(this, nomeJogadorUm+" X "+nomeJogadorDois)

        } else {

            alertDialogPersonal(this, "NOMES NÃO DEFINIDOS",
                getString(R.string.texto_players_sem_nomes), "Sim", "Não")

            tv_JogadorDavez.setText(getString(R.string.jogador_atual, nomeJogadorUm))
        }

        placarJogador.setText(resources.getString(R.string.placar_do_jogo, nomeJogadorUm, pontuacaoTotalJogadorUm.toString(), pontuacaoTotalJogadorDois.toString(), nomeJogadorDois))
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

    //pega a posicao do botão
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
        val lin1 = listOf(1,2,3)
        val lin2 = listOf(4,5,6)
        val lin3 = listOf(7,8,9)

        val col1 = listOf(1,4,7)
        val col2 = listOf(2,5,8)
        val col3 = listOf(3,6,9)

        val diag1 = listOf(1,5,9)
        val diag2 = listOf(3,5,7)

        var vencedor = -1

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
                toastShort(this, "Parabéns: $nomeJogadorUm, VOCÊ VENCEU!")
            }
            2 -> {
                pontuacaoTotalJogadorDois++
                toastShort(this, "Parabéns: $nomeJogadorDois, VOCÊ VENCEU!")
            }
        }
    }

    fun reiniciarJogo(view: View) {
        setContentView(R.layout.activity_main)

    }

    fun atualizaNomeDoJogadorAtual(jogadorDaVez: Int){

        when (jogadorDaVez) {
            1 -> {
                tv_JogadorDavez.setText(resources.getString(R.string.jogador_atual, nomeJogadorUm))
            }

            2 -> {
                tv_JogadorDavez.setText(resources.getString(R.string.jogador_atual, nomeJogadorDois))
            }
        }
    }
}
