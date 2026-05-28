package com.example.karunada_koteguide

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                MainHomeScreen()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHomeScreen() {

    val context = LocalContext.current

    var username by remember {
        mutableStateOf("")
    }

    var loggedIn by remember {
        mutableStateOf(false)
    }

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    val photoLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { bitmap ->

            if (bitmap != null) {

                Toast.makeText(
                    context,
                    "Photo Captured Successfully 📸",
                    Toast.LENGTH_LONG
                ).show()

            } else {

                Toast.makeText(
                    context,
                    "Camera Cancelled",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    if (!loggedIn) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5E6C8))
        ) {

            Image(
                painter = painterResource(id = R.drawable.fortbanner),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),

                    shape = RoundedCornerShape(28.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF8E7)
                    )

                ) {

                    Column(

                        modifier = Modifier
                            .padding(24.dp),

                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Text(
                            text = "Karunada-Kote Guide 🏰",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF5D4037)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Explore Karnataka’s Historic Forts",
                            color = Color.Gray,
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(22.dp))

                        OutlinedTextField(

                            value = username,

                            onValueChange = {
                                username = it
                            },

                            label = {
                                Text("Enter Username")
                            },

                            modifier = Modifier.fillMaxWidth(),

                            shape = RoundedCornerShape(16.dp)

                        )

                        Spacer(modifier = Modifier.height(22.dp))

                        Button(

                            onClick = {

                                if (username.isNotEmpty()) {

                                    loggedIn = true

                                } else {

                                    Toast.makeText(
                                        context,
                                        "Enter Username",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),

                            shape = RoundedCornerShape(18.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF6D4C41)
                            )

                        ) {

                            Text(
                                text = "Enter App",
                                fontSize = 18.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text =
                                "Audio Tours • Fort Navigation • Photo Challenges",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }

    } else {

        ModalNavigationDrawer(

            drawerState = drawerState,

            drawerContent = {

                ModalDrawerSheet {

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Welcome $username 👋",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )

                    HorizontalDivider()

                    Text(
                        text = "🏰 Heritage Tourism",
                        modifier = Modifier.padding(16.dp)
                    )

                    Text(
                        text = "📍 Guided Fort Maps",
                        modifier = Modifier.padding(16.dp)
                    )

                    Text(
                        text = "🎧 Historical Audio",
                        modifier = Modifier.padding(16.dp)
                    )

                    Text(
                        text = "📸 Photo Challenges",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

        ) {

            Scaffold(

                topBar = {

                    TopAppBar(

                        title = {

                            Text(
                                "Karunada-Kote Guide"
                            )

                        },

                        navigationIcon = {

                            IconButton(
                                onClick = {

                                }
                            ) {

                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }

            ) { padding ->

                LazyColumn(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(Color(0xFFF5E6C8)),

                    contentPadding = PaddingValues(16.dp),

                    verticalArrangement = Arrangement.spacedBy(20.dp)

                ) {

                    item {

                        Box {

                            Image(
                                painter = painterResource(
                                    id = R.drawable.fortbanner
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(260.dp)
                                    .clip(RoundedCornerShape(24.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(20.dp)
                            ) {

                                Text(
                                    text = "Explore Karnataka Forts 🏰",
                                    color = Color.White,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = "Historic Stories • Audio Tours • Heritage Maps",
                                    color = Color.White,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }

                    item {

                        FortCard(
                            title = "Chitradurga Fort",
                            image = R.drawable.chitradurga,
                            history =
                                "Chitradurga Fort is one of Karnataka’s greatest forts known for massive stone walls and the legendary Onake Obavva story.",
                            mapLat = 14.2266,
                            mapLng = 76.4000,
                            audioRes = R.raw.chitradurga_story,
                            challengeImage = R.drawable.onakeobavva,
                            photoLauncher = {
                                photoLauncher.launch(null)
                            }
                        )
                    }

                    item {

                        FortCard(
                            title = "Bidar Fort",
                            image = R.drawable.bidar,
                            history =
                                "Bidar Fort reflects Indo-Islamic architecture and historical military defense systems of Karnataka.",
                            mapLat = 17.9104,
                            mapLng = 77.5199,
                            audioRes = R.raw.bidar_story,
                            challengeImage = R.drawable.bidar,
                            photoLauncher = {
                                photoLauncher.launch(null)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FortCard(
    title: String,
    image: Int,
    history: String,
    mapLat: Double,
    mapLng: Double,
    audioRes: Int,
    challengeImage: Int,
    photoLauncher: () -> Unit
) {

    val context = LocalContext.current

    var unlocked by remember {
        mutableStateOf(false)
    }

    var isPlaying by remember {
        mutableStateOf(false)
    }

    var currentPlayer by remember {
        mutableStateOf<MediaPlayer?>(null)
    }

    Card(

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF8E7)
        )

    ) {

        Column {

            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    color = Color(0xFF4E342E)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = history,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = {

                        val uri = Uri.parse(
                            "google.navigation:q=$mapLat,$mapLng"
                        )

                        val intent =
                            Intent(Intent.ACTION_VIEW, uri)

                        intent.setPackage(
                            "com.google.android.apps.maps"
                        )

                        context.startActivity(intent)

                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6D4C41)
                    )
                ) {

                    Text("Navigate To Fort 🗺️")

                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Button(

                        onClick = {

                            if (!isPlaying) {

                                currentPlayer?.release()

                                currentPlayer =
                                    MediaPlayer.create(
                                        context,
                                        audioRes
                                    )

                                currentPlayer?.start()

                                isPlaying = true

                                Toast.makeText(
                                    context,
                                    "Playing Heritage Story 🔊",
                                    Toast.LENGTH_LONG
                                ).show()

                                currentPlayer?.setOnCompletionListener {

                                    isPlaying = false

                                }
                            }

                        },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8D6E63)
                        )
                    ) {

                        Text("Play Audio")

                    }

                    Button(

                        onClick = {

                            currentPlayer?.pause()

                            isPlaying = false

                            Toast.makeText(
                                context,
                                "Audio Paused ⏸️",
                                Toast.LENGTH_SHORT
                            ).show()

                        },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5D4037)
                        )
                    ) {

                        Text("Pause")

                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {

                        unlocked = true

                        Toast.makeText(
                            context,
                            "Landmark Unlocked ✅",
                            Toast.LENGTH_LONG
                        ).show()

                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5D4037)
                    )
                ) {

                    Text("Unlock Landmark")

                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text =
                        if (unlocked)
                            "Visited Successfully ✅"
                        else
                            "Not Visited Yet",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4E342E)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Photo Challenge 📸",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color(0xFF5D4037)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = painterResource(id = challengeImage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(18.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {

                        try {

                            photoLauncher()

                        } catch (e: Exception) {

                            Toast.makeText(
                                context,
                                "Camera Not Opening",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4E342E)
                    )
                ) {

                    Text("Capture Challenge Photo")

                }

                Spacer(modifier = Modifier.height(14.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8DCC3)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            text = "Tourism Impact 🌍",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text =
                                "• Heritage Tourism\n" +
                                        "• Educational Storytelling\n" +
                                        "• Karnataka Engineering Pride\n" +
                                        "• Historical Awareness"
                        )
                    }
                }
            }
        }
    }
}