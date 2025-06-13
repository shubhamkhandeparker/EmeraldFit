package com.example.emeraldfit

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emeraldfit.ui.theme.EmeraldFitTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Slider
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Constraints
import java.nio.file.WatchEvent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Person


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculatorTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Color(0xFF1A1A1A),
            surface = Color(0xFF2D2D2D),
            primary = Color(0xFF7ED321)
        )
    ) {
        content()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmeraldFitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BMICalculatorScreen(

                    )
                }
            }
        }
    }
}

@Composable
fun BMICalculatorScreen() {
    val MALE = "Male"
    val FEMALE = "Female"
    var selectedGender by remember { mutableStateOf("Male") }
    var bmiResult by remember { mutableStateOf<Double?>(null) }
    BMICalculatorTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1A1A1A))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Heart",
                    tint = Color(0xFF7ED321),
                    modifier = Modifier.size(36.dp)

                )
                Text(
                    text = "BMI Calculator ",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )


            }
            Text(
                text = "Health Tracker",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 2.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            //Cards Wrapper Start Here

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF2D2D2D)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(24.dp)
                )
                {

                    Text(
                        text = "Gender",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,

                        )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = { selectedGender = MALE },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedGender == MALE)
                                    Color(0xFF7ED321)
                                else
                                    Color(0xFF3A3A3A)
                            )
                        ) {

                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Person",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(end = 4.dp)
                            )

                            Text("Male", color = Color.White)
                        }

                        Button(
                            onClick = { selectedGender = FEMALE },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedGender == FEMALE)
                                    Color(0xFF7ED321)
                                else
                                    Color(0xFF3A3A3A)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Person",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(end = 4.dp)
                            )

                            Text("Female", color = Color.White)
                        }
                    }


                    //Height Section

                    Text(
                        text = "Height",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                    var height by remember { mutableStateOf(175f) }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Slider(
                            value = height,
                            onValueChange = { height = it },
                            valueRange = 100f..250f,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = "${height.toInt()} cm",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFF7ED321),
                            modifier = Modifier.padding(start = 16.dp)
                        )

                    }


                    //Weight Section

                    Text(
                        text = "Weight",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier.padding(top = 32.dp)
                    )


                    var weight by remember { mutableStateOf(70f) }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Slider(
                            value = weight,
                            onValueChange = { weight = it },
                            valueRange = 30f..150f,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = "${weight.toInt()} kg",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFF7ED321),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }


                    //Age Button
                    Text(
                        text = "Age",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier.padding(top = 32.dp)
                    )

                    var age by remember { mutableStateOf(25f) }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Slider(
                            value = age,
                            onValueChange = { age = it },
                            valueRange = 1f..100f,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "${age.toInt()} years",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFF7ED321),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }


                    //Calculate Button

                    Button(
                        onClick = {
                            //BMI Formula : weight(kg) / (height(m))²
                            val heightInMeters = height / 100.0  //Convert cm to Meters
                            val calculatedBMI = weight / (heightInMeters * heightInMeters)
                            bmiResult = calculatedBMI
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp)
                            .padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Calculate BMI",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    //Output Result
                    bmiResult?.let{bmi ->
                        Card (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top=24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF3A3A3A)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ){
                            Column (
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Text(
                                    text="Your BMI",
                                    style=MaterialTheme.typography.titleMedium,
                                    color=Color.Gray
                                )

                                Text(
                                    text = String.format("%.1f",bmi),
                                    style = MaterialTheme.typography.headlineLarge,
                                    color=Color.White,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )

                                val category= when {
                                    bmi<18.5->"Underweight"
                                    bmi<25.0->"Normal"
                                    bmi<30->"Overweight"
                                    else->"Obese"
                                }
                                Text(
                                    text = category,
                                    style=MaterialTheme.typography.titleLarge,
                                    color=when(category){
                                        "Normal"->Color(0xFF7ED321)
                                            "Underweight"->Color(0xFF2196F3)
                                        "Overweight"->Color(0xFFFF9800)
                                        else -> Color(0xFFE91E63)
                                    }
                                )
                            }
                        }

                    }




                }


            }
        }
    }
}
