package com.example.uts_lagi

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.uts_lagi.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private var uri: Uri? = null
    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ini adalah bawaan atau default dari android studio
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Untuk menyimpan value yang di return pada tombol
        val submit = view?.findViewById<Button>(R.id.button2) as Button
        // menyimpan data yang diinputkan oleh user
        var foto1 = view.findViewById<ImageView>(R.id.foto) as ImageView
        val nomor = view.findViewById<EditText>(R.id.Nomor1) as EditText
        val nama = view.findViewById<EditText>(R.id.Namalengkap) as EditText
        val tanggallhari = view.findViewById<EditText>(R.id.tanggallhari1) as EditText
        val gender = view.findViewById<EditText>(R.id.JenisKelamin1) as EditText
        val Alamat = view.findViewById<EditText>(R.id.Alamat1) as EditText
        val sharedPreferences: SharedPreferences =
            view.context.getSharedPreferences("pref", Context.MODE_PRIVATE)

        foto1.setOnClickListener {
            checkPermissionForImage()

            Toast.makeText(context, "udah di pencet", Toast.LENGTH_SHORT).show()
        }
        submit.setOnClickListener {

            val data1 = nomor.text.toString()
            val data2 = nama.text.toString()
            val data3 = tanggallhari.text.toString()
            val data4 = gender.text.toString()
            val data5 = Alamat.text.toString()





            if (data1.isNotEmpty() && data2.isNotEmpty() && data3.isNotEmpty() && data4.isNotEmpty() && data5.isNotEmpty()) {
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("imageUser", uri.toString())
                editor.apply()


                // untuk memindah fragment dan pindah data
                val aksi = SecondFragmentDirections.actionSecondFragmentToFragmentThird(
                    data1,
                    data2,
                    data3,
                    data4,
                    data5
                )
                Navigation.findNavController(view).navigate(aksi)
            } else {
                Toast.makeText(
                    activity,
                    "Maaf inputan anda salah, mohon coba lagi",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
//untuk open gallery
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }
//untuk permision
    private fun checkPermissionForImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((context?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                && (context?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ) {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionCoarse = arrayListOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                requestPermissions(permission, 100)
                requestPermissions(permission, 100)
            } else {
                openGalleryForImage()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            var foto1 = view?.findViewById<ImageView>(R.id.foto) as ImageView
            foto1.setImageURI(data?.data)
            uri = data?.data!!
        }
    }


}