package br.edu.ifsp.scl.sc304887x.trucoscoreboardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.sc304887x.trucoscoreboardcompose.ui.theme.TrucoScoreBoardComposeTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita a tela inteira
        setContent {
            TrucoScoreBoardComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TrucoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun TrucoScreen(modifier: Modifier = Modifier) {
        var pontosA by remember { mutableIntStateOf(0) }
        var pontosB by remember { mutableIntStateOf(0) }

        var valorRodada by remember { mutableIntStateOf(1) }

        val textoBotaoTruco = when (valorRodada) {
            1 -> "TRUCO"
            3 -> "PEDIR 6"
            6 -> "PEDIR 9"
            9 -> "PEDIR 12"
            else -> "VALE 12"
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (pontosA >= 12) {
                    Text(text = "Equipe A Venceu!", fontSize = 28.sp)
                } else if (pontosB >= 12) {
                    Text(text = "Equipe B Venceu!", fontSize = 28.sp)
                } else if (pontosA == 11 && pontosB == 11) {
                    Text(text = "Mão de 11: Ambas as equipes!", fontSize = 22.sp)
                } else if (pontosA == 11) {
                    Text(text = "Mão de 11: Equipe A!", fontSize = 22.sp)
                } else if (pontosB == 11) {
                    Text(text = "Mão de 11: Equipe B!", fontSize = 22.sp)
                } else {
                    Text(text = "Placar do Jogo", fontSize = 22.sp)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Equipe A", fontSize = 24.sp)
                    Text(
                        text = pontosA.toString(),
                        fontSize = 68.sp,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = {
                            if (pontosA < 12 && pontosB < 12) {
                                pontosA = (pontosA + valorRodada).coerceAtMost(12)
                                valorRodada = 1
                            }
                        },
                        modifier = Modifier.padding(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "+ $valorRodada")
                    }
                }

                Button(
                    onClick = {
                        if (pontosA < 12 && pontosB < 12) {
                            valorRodada = when (valorRodada) {
                                1 -> 3
                                3 -> 6
                                6 -> 9
                                9 -> 12
                                else -> 12
                            }
                        }
                    },
                    enabled = valorRodada < 12 && pontosA < 12 && pontosB < 12,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = textoBotaoTruco)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Equipe B", fontSize = 24.sp)
                    Text(
                        text = pontosB.toString(),
                        fontSize = 68.sp,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = {
                            if (pontosA < 12 && pontosB < 12) {
                                pontosB = (pontosB + valorRodada).coerceAtMost(12)
                                valorRodada = 1
                            }
                        },
                        modifier = Modifier.padding(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "+ $valorRodada")
                    }
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    pontosA = 0
                    pontosB = 0
                    valorRodada = 1
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Reiniciar Partida")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TrucoScoreBoardComposeTheme {
            TrucoScreen(Modifier)
        }
    }
}