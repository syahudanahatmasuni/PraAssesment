package com.example.pra_assesment


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.pra_assesment.R
import com.example.pra_assesment.databinding.FragmentSegitigaBinding
import kotlinx.android.synthetic.main.fragment_segitiga.*

/**
 * A simple [Fragment] subclass.
 */
class SegtigaFragment : Fragment() {
    private lateinit var binding: FragmentSegitigaBinding
    private var nilaiAlas: Double = 0.00
    private var nilaiTinggi: Double = 0.00
    private var nilaiPythagoras: Double = 0.00
    private var nilaiLuas: Double = 0.00
    private var nilaiKeliling: Double = 0.00

    companion object {
        const val KEY_NILAILUAS = "key_nilaiLuas"
        const val KEY_NILAIKELILING = "key_nilaiKeliling"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_segitiga, container, false)
        if (savedInstanceState != null) {
            nilaiLuas = savedInstanceState.getDouble(KEY_NILAILUAS)
            nilaiKeliling = savedInstanceState.getDouble(KEY_NILAIKELILING)
            updateNilai(nilaiPythagoras, nilaiLuas, nilaiKeliling)
        }
        binding.Hitung.setOnClickListener {
            if (binding.Alas.text.toString().isNotEmpty() && binding.Tinggi.text.toString().isNotEmpty()) {
                nilaiAlas = binding.Alas.text.toString().toDouble()
                nilaiTinggi = binding.Tinggi.text.toString().toDouble()
                nilaiLuas = 0.5 * nilaiAlas * nilaiTinggi
                nilaiKeliling = nilaiAlas + nilaiTinggi + nilaiPythagoras
                binding.LuasSegitiga.text = "Luas = $nilaiLuas"
                binding.KelilingSegitiga.text = "Keliling = $nilaiKeliling"
            } else {
                Toast.makeText(this.activity, "Field tidak boleh kosong!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.Share.setOnClickListener {
            val textLuasSegitiga = LuasSegitiga.text.toString()
            val textKelilingSegitiga = KelilingSegitiga.text.toString()
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                textLuasSegitiga + "\n" + textKelilingSegitiga
            )
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Hasil hitung segitiga")
            startActivity(Intent.createChooser(shareIntent, "Share text via..."))
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(KEY_NILAIKELILING, nilaiKeliling)
        outState.putDouble(KEY_NILAILUAS, nilaiLuas)
    }

    @SuppressLint("SetTextI18n")
    private fun updateNilai(s1: Double, s2: Double, s3: Double) {
        binding.LuasSegitiga.setText("Luas = " + s2.toString())
        binding.KelilingSegitiga.setText("Keliling = " + s3.toString())
    }
}
