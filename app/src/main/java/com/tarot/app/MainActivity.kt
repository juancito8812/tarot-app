package com.tarot.app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarot.app.ui.navigation.AppNavigation
import com.tarot.app.ui.theme.TarotTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val crashFile = File(filesDir, "crash.log")
        val crashLog = if (crashFile.exists()) crashFile.readText() else null
        if (crashLog != null) crashFile.delete()
        setContent {
            TarotTheme {
                val showCrash = remember { mutableStateOf(crashLog != null) }
                if (showCrash.value && crashLog != null) {
                    AlertDialog(
                        onDismissRequest = { showCrash.value = false },
                        title = { Text("Error anterior") },
                        text = { Text(crashLog.take(5000), fontSize = 10.sp) },
                        confirmButton = {
                            TextButton(onClick = { showCrash.value = false }) {
                                Text("Cerrar")
                            }
                        }
                    )
                }
                AppNavigation()
            }
        }
    }
}
