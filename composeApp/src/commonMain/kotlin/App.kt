import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        var text by remember { mutableStateOf("https://kmp.jetbrains.com") }
        var qrCodeContent by remember { mutableStateOf("") }
        
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            HeadSpacer()
            Text("QR Code Generator", fontSize = TextUnit(32f, TextUnitType.Sp) )
            HeadSpacer()
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Content", modifier = Modifier.align(Alignment.Start))
                ContentSpacer()
                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                        qrCodeContent = ""
                    },
                    keyboardActions = KeyboardActions { },
                    modifier = Modifier.fillMaxWidth()
                )
                ContentSpacer()
                Button(onClick = {
                    qrCodeContent = text
                }) {
                    Text("Generate QR Code")
                }
                QrCode(qrCodeContent)
            }
        }
    }
}

@Composable
fun QrCode(qrCodeContent: String) {
    val qrCodePainter = rememberQrCodePainter(qrCodeContent)
    ContentSpacer()
    AnimatedVisibility(qrCodePainter.data.isNotEmpty()) {
        Image(
            qrCodePainter,
            null,
            modifier = Modifier.size(128.dp)
        )
    }
}

@Composable
private fun HeadSpacer() = Spacer(Modifier.height(32.dp))

@Composable
private fun ContentSpacer() = Spacer(Modifier.height(16.dp))