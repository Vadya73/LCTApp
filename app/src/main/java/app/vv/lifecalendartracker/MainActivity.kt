package app.vv.lifecalendartracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import app.vv.lifecalendartracker.ui.theme.LCTAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LCTAppTheme {
                Scaffold(
                    floatingActionButton = {
                        AddHabitButton()
                    },
                    content = { padding: PaddingValues ->
                        Column(
                            modifier = Modifier
                                .padding(padding)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            HeaderSection()
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun HeaderSection(){
        var search by remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(52.dp)
                    .semantics{ contentDescription = "Open Menu"}
            ) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = null
                )
            }

            Spacer(Modifier.width(12.dp))

            OutlinedTextField(
                value = search,
                onValueChange = {},
                singleLine = true,
                placeholder = { Text("Enter habit name") },
                label = { Text("Search") },
                modifier = Modifier
                    .weight(1f)
            )

            Spacer(Modifier.width(12.dp))

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(48.dp)
                    .semantics{ contentDescription = "No AD"}
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_no_ad),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }

    @Composable
    private fun HabitItem(){

    }

    @Composable
    private fun AddHabitButton(){
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(64.dp)
                .semantics{ contentDescription = "Add Habit"}

        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
        }
    }
}