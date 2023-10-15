package com.gmulbat1301.calculadora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calc = Calculo()
        var num = ""
        val numPanel = findViewById<TextView>(R.id.numberPanel)


        /**
         * Creo dos lista de Botones, una de operadores y otra de numeros
         * para que sea mas facil acceder a estos depues y no tener que crear varios findViewById
         */
        val buttonIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        )
        val operators = listOf(
            R.id.buttonAdd, R.id.buttonSubs, R.id.buttonMult, R.id.buttonDiv
        )


        /**
         * Recorro la lista buttonIds y creeo un findViewById para cada uno
         * todos los botones hacen lo mismo menos cuando guarda el numero pulsado en num
         */
        buttonIds.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                num += findViewById<Button>(buttonId).text
                numPanel.text = num
            }
        }


        /**
         * Recorro la lista operators y creeo un findViewById para cada uno
         * todos los botones hacen lo mismo menos cuando guarda que tipo de operacion realiza
         */
        operators.forEach { operatorId ->
            findViewById<Button>(operatorId).setOnClickListener {
                calc.num1 = num.toDouble()
                num = ""
                numPanel.text = num
                calc.operation = findViewById<Button>(operatorId).text.toString()
            }
        }


        /**
         *  El Boton CE vacia las dos variables @param num1, num2
         */
        findViewById<Button>(R.id.buttonCE).setOnClickListener {
            num = ""
            numPanel.text = num
        }

        /**
         * El Botton igual comprueba que los dos numeros no esten vacios, si alguno lo esta muestra un toast avisando
         * luego llama a la clase Calculo, a la funcion result, para mostrar el resultado de la operacion
         */
        findViewById<Button>(R.id.buttonEq).setOnClickListener {
            calc.num2 = num.toDouble()
            if (calc.num1 != 0.0 && calc.num2 != 0.0) {
                val result = calc.equal()
                numPanel.text = result.toString()
                num = result.toString()
            } else {
                Toast.makeText(this, "Debe introducir 2 numeros", Toast.LENGTH_LONG).show()
            }
        }

    }
}


/**
 * La clase calculo simplemente reecibe dos numeros y una operacion y realiza la operacion con esos dos numeros
 */
class Calculo {

    var num1: Double = 0.0
    var num2: Double = 0.0
    var operation: String = ""
    private var result: Double = 0.0

    fun equal(): Double {
        when (operation) {
            "+" -> sumar()
            "-" -> restar()
            "*" -> multiplicar()
            "/" -> dividir()
        }
        return result
    }

    fun sumar(): Double{
        result = num1 + num2
        return result
    }
    fun restar(): Double{
        result = num1 - num2
        return result
    }
    fun multiplicar(): Double{
        result = num1 * num2
        return result
    }
    fun dividir(): Double{
        result = num1 / num2
        return result
    }

}
