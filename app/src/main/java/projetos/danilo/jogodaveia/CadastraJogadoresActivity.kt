package projetos.danilo.jogodaveia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastra_jogadores.*

class CadastraJogadoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastra_jogadores)

        btnJogar.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Jogador1",jogadorUm.text.toString())
            intent.putExtra("Jogador2",jogadorUm.text.toString())
            startActivity(intent)
        }
    }
}
