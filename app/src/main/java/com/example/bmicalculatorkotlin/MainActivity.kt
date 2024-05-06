package com.example.bmicalculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtResult = findViewById<TextView>(R.id.txtResult)
        val txtMsg=findViewById<TextView>(R.id.txtMsg)
        val edtWeight = findViewById<EditText>(R.id.edtWeight)
        val edtHeightFt = findViewById<EditText>(R.id.edtHeightFt)
        val edtHeightIn = findViewById<EditText>(R.id.edtHeightIn)
        val btnCalculate  = findViewById<Button>(R.id.btnCalculate)
        val background=findViewById<LinearLayout>(R.id.background)
        val txtTitle=findViewById<TextView>(R.id.txtTitle)

        btnCalculate.setOnClickListener{

            if(!edtWeight.text.toString().equals("") && !edtHeightFt.text.toString().equals("")
                && !edtHeightIn.text.toString().equals("")){

                val wt = (edtWeight.text.toString()).toDouble();
                val htFeet = (edtHeightFt.text.toString()).toDouble()
                val htInch = (edtHeightIn.text.toString()).toDouble()

                val totalInch = (htFeet*12) + htInch
                val totalCm = totalInch * 2.54
                val totalMt = totalCm / 100

                val bmi = wt/(totalMt*totalMt)

                txtTitle.text = "Your BMI"
                txtResult.text = "${bmi.toInt()}"

                if(bmi < 18.5){
                    Toast.makeText(this@MainActivity,R.string.strUnderWt,Toast.LENGTH_LONG).show()
                    txtMsg.text = resources.getString(R.string.strUnderWt)   //path se string ko layega

                    background.setBackgroundColor(resources.getColor(R.color.colorYellow))
                }
                else if(bmi in 18.5..24.9){
                    Toast.makeText(this@MainActivity,R.string.strHealthy,Toast.LENGTH_LONG).show()
                    txtMsg.text = resources.getString(R.string.strHealthy)

                    background.setBackgroundColor(resources.getColor(R.color.colorGreen))
                }
                else{
                    Toast.makeText(this@MainActivity,R.string.strOverWt,Toast.LENGTH_LONG).show()
                    txtMsg.text = resources.getString(R.string.strOverWt)

                    background.setBackgroundColor(resources.getColor(R.color.colorRed))

                }
            }
            else{
                Toast.makeText(this@MainActivity,"Please fill all the required fields.",Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onBackPressed() {

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage("Are you sure you want exit?")
        alertDialog.setTitle("Alert!")
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton("Yes") {
            dialog,which-> finish()
        }

        alertDialog.setNegativeButton("No"){
            dialog,which-> dialog.cancel()
        }
        alertDialog.show()
    }
}