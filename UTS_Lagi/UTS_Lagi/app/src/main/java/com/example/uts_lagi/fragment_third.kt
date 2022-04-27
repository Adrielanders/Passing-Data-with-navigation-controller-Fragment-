package com.example.uts_lagi

import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs


class fragment_third : Fragment() {
    //memanggil variabel args

    val args: fragment_thirdArgs by navArgs()
    private var uri: Uri? = null //untuk menampung image
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_third, container, false)
        //ini yang di passing dari fragment second
        val data1 = args.nomor1
        val data2 = args.namalengkap
        val data3 = args.tanggallhari1
        val data4 = args.jenisKelamin1
        val data5 = args.alamat1
        //berfungsi untuk mencari object pada view
        val nomor2 = view?.findViewById<TextView>(R.id.tampilnomor) as TextView
        val nama2 = view?.findViewById<TextView>(R.id.tampilnama) as TextView
        val tanggallahir2 = view?.findViewById<TextView>(R.id.tampillahir) as TextView
        val gender2 = view?.findViewById<TextView>(R.id.tampiljenis) as TextView
        val Alamat2 = view?.findViewById<TextView>(R.id.tampilalamat) as TextView
        val foto2 = view?.findViewById<ImageView>(R.id.tampilfoto) as ImageView
        // nampilin data yang sudah atau terlah terpasing
        nomor2.text = data1
        nama2.text = data2
        tanggallahir2.text = data3
        gender2.text = data4
        Alamat2.text = data5
        //untuk mengambil valuenya di share prefernce
        val sharedPreferences : SharedPreferences = view.context.getSharedPreferences("pref",
            Context.MODE_PRIVATE)
        val foto = sharedPreferences.getString("imageUser","")
        uri = Uri.parse(foto)
        Log.d("hasil foto",uri.toString())
        foto2.setImageURI(uri)





        return view

    }

}