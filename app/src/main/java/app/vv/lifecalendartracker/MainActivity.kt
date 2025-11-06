package app.vv.lifecalendartracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.vv.lifecalendartracker.ui.theme.LCTAppTheme
import soup.compose.material.motion.circularReveal

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LCTAppTheme {
                var showAdd by remember { mutableStateOf(false) }

                BackHandler(enabled = showAdd) {
                    showAdd = false
                }

                Scaffold(
                    floatingActionButton = {
                        if (!showAdd) {
                            AddHabitFab { showAdd = true }
                        }
                    },
                    content = { padding: PaddingValues ->

                        Box(
                            modifier = Modifier
                                .padding(padding)
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            ) {
                                HeaderSection()
                            }

                            if (showAdd) {
                                AddHabitScreenAnimated(
                                    visible = showAdd,
                                    onClose = { showAdd = false }
                                )
                            }

                        }
                    }
                )
            }
        }
    }

    @Composable
    fun AddHabitScreenAnimated(
        visible: Boolean,
        onClose: () -> Unit
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding()
                .background(Color.White)
                .padding(18.dp)
                .circularReveal(
                    visible = visible,
                    center = { fullSize ->
                        Offset(
                            fullSize.width.toFloat(),
                            fullSize.height.toFloat()
                        )
                    }
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                IconButton(
                    onClick = onClose,
                    modifier = Modifier
                        .size(52.dp)
                        .semantics { contentDescription = "Back" }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        tint = Color.Black,
                        contentDescription = null,
                        modifier = Modifier.size(52.dp)
                    )
                }

                Spacer(Modifier.height(24.dp))

                Box(Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .background(Color.LightGray),
                    contentAlignment = Alignment.TopCenter,
                ){
                    Image(
                        painter = painterResource(R.drawable.ic_no_ad),
                        contentDescription = "",
                        modifier = Modifier
                            .size(256.dp)
                            .clip(CircleShape),
                    )
                }
                Column(Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(Color.Black),
                    verticalArrangement = Arrangement.Top

                    ) {
                    Text(
                        text = "Исключаю из жизни",
                        textAlign = TextAlign.Left,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(16.dp))

                    var textValue by remember { mutableStateOf("") }
                    TextField(
                        value = textValue,
                        onValueChange = {textValue = it},
                        placeholder = {
                            Text(text = "Что вы хотите исключить",
                            textAlign = TextAlign.Left,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold)},
                    )
                }

                Spacer(Modifier.height(12.dp))

                Column(Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(Color.Black),
                    verticalArrangement = Arrangement.Top

                ) {
                    Text(
                        text = "Начиная с",
                        textAlign = TextAlign.Left,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(16.dp))

                    var showDatePicker by remember { mutableStateOf(false) }
                    val datePickerState = rememberDatePickerState()

                    Text(
                        text = "textValue",
                        textAlign = TextAlign.Left,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            showDatePicker = true
                        }
                    )

                    if (showDatePicker) {
                        DatePickerDialog(
                            onDismissRequest = { showDatePicker = false },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        showDatePicker = false
                                    }
                                ) {
                                    Text("OK")
                                }
                            }
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }

                }
            }
        }
    }

    @Composable
    private fun AddHabitFab(onClick: () -> Unit) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(76.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add Habit",
                modifier = Modifier.size(38.dp)
            )
        }
    }

    @Composable
    private fun HeaderSection() {
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
                    .semantics { contentDescription = "Open Menu" }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = null
                )
            }

            Spacer(Modifier.width(12.dp))

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                singleLine = true,
                placeholder = { Text("Enter habit name") },
                label = { Text("Search") },
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(12.dp))

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(48.dp)
                    .semantics { contentDescription = "No AD" }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_no_ad),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}