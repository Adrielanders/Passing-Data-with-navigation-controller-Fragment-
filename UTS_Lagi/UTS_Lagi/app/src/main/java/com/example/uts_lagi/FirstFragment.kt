package com.example.uts_lagi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Untuk menyimpan value yang di return pada tombol
        val button_first =  view?.findViewById<Button>(R.id.button_first) as Button
        button_first.setOnClickListener {
            // Mengenali bagian view
            val username = view?.findViewById<EditText>(R.id.username) as EditText
            val password = view?.findViewById<EditText>(R.id.password) as EditText

            //berfungis untuk mengconvert dari ipuntan menjadi string
            val nama = username.text.toString()
            val pass = password.text.toString()

            //mendeklarasikan variable untuk menyimpan nama
            val nama1 = "adriel"
            val pass1 = "adriel050901"
            /// jika
            if (nama == nama1 && pass == pass1)
            {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
            else
            {
                Toast.makeText(activity,"Maaf inputan anda salah, mohon coba lagi", Toast.LENGTH_LONG).show()
            }

        }
    }


}