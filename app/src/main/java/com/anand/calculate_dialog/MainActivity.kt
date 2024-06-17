package com.anand.calculate_dialog

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anand.calculate_dialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding?.calculate?.setOnClickListener {
            if (binding?.et1?.text.toString().trim().isNullOrEmpty()){
                binding?.et1?.error="Enter a Number"
            }
            else {
                var num1 = binding?.et1?.text.toString().toInt()
                var alertDialog = AlertDialog.Builder(this)
                alertDialog.setCancelable(false)
                alertDialog.setTitle("Perform calculations on ${binding?.et1?.text}")
                alertDialog.setMessage("ADD ${binding?.et1?.text}in number/n SUB ${binding?.et1?.text} in number/n Press reset to set 0")
                alertDialog.setPositiveButton("ADD") { _, _ ->
                    var result = binding?.et1?.text.toString().toInt().plus(num1)
                    binding?.et1?.setText(result.toString())
                }
                alertDialog.setNegativeButton("SUB",{ _, _ ->
                    var result = binding?.et1?.text.toString().toInt().minus(num1)
                    binding?.et1?.setText(result.toString())
                })
                alertDialog.setNeutralButton("Reset", { _, _ ->
                    binding?.et1?.setText("0")
                })
                alertDialog.show()
            }

        }
    }
}