package com.example.bmi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi.ui.theme.BMITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { }
                BmiApp()
            }
        }
    }


    @SuppressLint("DefaultLocale")
    @Composable
    fun BmiApp() {

        var heightInput: String by remember { mutableStateOf("") }
        var weightInput: String by remember { mutableStateOf("") }

        val weight = weightInput.toFloatOrNull() ?: 0.0f
        val height = heightInput.toIntOrNull() ?: 0

        val bmi =
            if (weight > 0 && height > 0) weight / ((height.toFloat() / 100) * (height.toFloat() / 100)) else 0.0f

        val bmiReference = when {
            bmi == 0f -> ""
            bmi < 18.5 -> stringResource(R.string.ref_under)
            bmi < 25 -> stringResource(R.string.ref_normal)
            bmi < 30 -> stringResource(R.string.ref_ob1)
            bmi < 35 -> stringResource(R.string.ref_ob2)
            else -> stringResource(R.string.ref_ob3)
        }

        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.body_mass_index),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            )
            OutlinedTextField(
                value = heightInput,
                onValueChange = { heightInput = it.replace(',', '.') },
                label = { Text(stringResource(R.string.height)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = weightInput,
                onValueChange = { weightInput = it.replace(',', '.') },
                label = { Text(stringResource(R.string.weight)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(
                    R.string.result,
                    String.format("%.2f", bmi).replace(',', '.')
                )
            )

            Text(text = bmiReference)
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        BMITheme {
            BmiApp()
        }
    }
}