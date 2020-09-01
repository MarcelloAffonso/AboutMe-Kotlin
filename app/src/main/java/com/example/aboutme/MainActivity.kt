package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Utilizando Data binding
    // Tem como vantagem com relação ao modo "find view by id" que não precisa atravessar a hierarquia de views toda vez que a view é criada ou recriada (onCreate)
    private lateinit var binding: ActivityMainBinding

    // Nome que antes era puxado do arquivo de strings, agora é inicializado na própria classe
    private val myName: MyName = MyName("Portgas D. Ace")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instancia o objeto de binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Utiliza
        binding.myName = myName

        // Utiliza o binding para
        binding.doneButton.setOnClickListener{
            addNickName(it)
        }
    }

    private fun addNickName(view: View){

        // Apply cria um escopo temporário. Não muda nada no funcionamento, mas deixa o código mais fácil de entender
        binding.apply {
            nicknameText.text = binding.nicknameEdit.text

            // Passa para o objeto de data que
            myName?.nickname = nicknameEdit.text.toString()

            // Basicamente vai dar um refresh nos dados e garantir que vai recriar todas as expressões do binding
            invalidateAll()

            // Chaveia a visibilidade dos elementos
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // Esconde o teclado após a operação
        val inm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inm.hideSoftInputFromWindow(view.windowToken,0)
    }
}