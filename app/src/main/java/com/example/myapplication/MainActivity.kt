package com.example.myapplication

import android.os.Bundle
import androidx.compose.material.icons.Icons

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info

import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon

import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.CapteurInfo
import com.example.myapplication.data.ComposantInfo
import com.example.myapplication.data.Source
import com.example.myapplication.model.Capteur
import com.example.myapplication.model.Composant
import com.example.myapplication.model.Outil
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutDesign(navController: NavController, s:Int) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val db = Firebase.firestore

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
                ) {
                    Spacer(modifier = Modifier.height(50.dp)) // Space at the top for alignment
                    Image(
                        painter = painterResource(R.drawable.admin),
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider() // Divider to separate image from menu items
                    Spacer(modifier = Modifier.height(10.dp)) // Additional space
                    NavigationDrawerItem(
                        label = { Text("acceuil ") },
                        selected = true,
                        onClick = {
                            navController.navigate("fourth")
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Les cartes ") },
                        selected = false,
                        onClick = {
                            navController.navigate("app")

                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Les capteurs ") },
                        selected = false,
                        onClick = {
                            navController.navigate("second")
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Les composants électroniques ") },
                        selected = false,
                        onClick = {
                            navController.navigate("third")
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Your Feedback ") },
                        selected = false,
                        onClick = {
                            navController.navigate("feedback")
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Pepole Feedbacks ") },
                        selected = false,
                        onClick = {
                            navController.navigate("affichefeedback")
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Log Out") },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.ExitToApp, // Use the appropriate icon
                                contentDescription = "Log Out"
                            )
                        },
                        selected = false,
                        onClick = {
                            Firebase.auth.signOut() // Sign out from Firebase
                            navController.navigate("Connexion") {
                                // Ensure the back stack is cleared
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        }
                    )

                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = "Menu",
                                modifier = Modifier.clickable {
                                    scope.launch {
                                        if (drawerState.isClosed) drawerState.open()
                                    }
                                }
                            )
                            SearchBar(Modifier) // SearchBar beside the menu icon
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    actions = {
                        IconButton(onClick = {  navController.navigate("fourth") }) {
                            Icon(Icons.Filled.Home, contentDescription = "Localized description")
                        }
                        Spacer(modifier = Modifier.width(55.dp)) // Space between icons
                        IconButton(onClick = {                             navController.navigate("feedback")
                        }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Localized description")
                        }
                        Spacer(modifier = Modifier.width(55.dp)) // Space between icons
                        IconButton(onClick = {                            navController.navigate("affichefeedback")
                        }) {
                            Icon(Icons.Filled.Info, contentDescription = "Localized description")
                        }
                        Spacer(modifier = Modifier.width(55.dp)) // Space between icons
                        IconButton(onClick = {
                            Firebase.auth.signOut() // Sign out from Firebase
                            navController.navigate("Connexion") {
                                // Ensure the back stack is cleared
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }

                        }) {
                            Icon(Icons.Filled.ExitToApp, contentDescription = "Localized description")
                        }
                        Spacer(modifier = Modifier.width(55.dp)) // Space between icons
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                if (s==1) {
                    Acceuil()
                }
                if (s==2){
                    capteurApp()
                }
                if (s==3){
                    ComposantApp()
                }
                if (s==4){
                    outilApp()
                }
                if (s==5){
                    Feedback(db)
                }
                if (s==6){
                    FeedbackList(db)
                }
            }
        }
    }
}

@Composable
fun outilApp() {
    val outilList = Source().loadAoutils()
    OutilList(outilList = outilList)
}

@Composable
fun OutilList(outilList: List<Outil>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(outilList) { outil ->
            OutilCard(
                outil = outil,
                modifier = Modifier.padding(8.dp) // Padding around the card
            )
        }
    }
}

@Composable
fun OutilCard(outil: Outil, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(false) }
    var isInShoppingCart by remember { mutableStateOf(false) }
    var showAlertFavorite by remember { mutableStateOf(false) }
    var showAlertShoppingCart by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                        contentDescription = if (expanded) "Show less" else "Show more"
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(outil.stringResourceId),
                    style = MaterialTheme.typography.displayMedium
                )
            }

            if (expanded) {
                Image(
                    painter = painterResource(outil.imageResourceId),
                    contentDescription = stringResource(outil.stringResourceId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(194.dp)
                        .scale(0.9f),
                    contentScale = ContentScale.Crop
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(outil.stringResourceText),
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.labelSmall
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Shopping Cart",
                                tint = if (isInShoppingCart)MaterialTheme.colorScheme.secondary else Color.Gray,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        isInShoppingCart = !isInShoppingCart
                                        showAlertShoppingCart = true
                                    }
                            )
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Favorite",
                                tint = if (isFavorite) MaterialTheme.colorScheme.secondary else Color.Gray,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        isFavorite = !isFavorite
                                        showAlertFavorite = true
                                    }
                            )
                        }
                        Text(
                            text = stringResource(outil.stringResourcePrix),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }

    if (showAlertFavorite) {
        AlertDialog(
            onDismissRequest = { showAlertFavorite = false },
            title = { Text(text = "Favorite") },
            text = { Text(text = "Object added to favorites!") },
            confirmButton = {
                Button(onClick = { showAlertFavorite = false }) {
                    Text("OK")
                }
            }
        )
    }
    if (showAlertShoppingCart) {
        AlertDialog(
            onDismissRequest = { showAlertShoppingCart = false },
            title = { Text(text = "Shopping Cart") },
            text = { Text(text = "Object added to your bag!") },
            confirmButton = {
                Button(onClick = { showAlertShoppingCart = false }) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
fun capteurApp() {
    val capteurapp = CapteurInfo().loadAoutils()
    Capteurlist(capteurList = capteurapp)
}



@Composable
fun Capteurlist(capteurList: List<Capteur>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        val pairs = capteurList.chunked(2)
        items(pairs) { pair ->
            CapteurCard(
                capteur1 = pair[0],
                capteur2 = pair.getOrNull(1), // Safely get the second item if it exists
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
@Composable
fun CapteurCard(
    capteur1: Capteur,
    capteur2: Capteur?,
    modifier: Modifier = Modifier
) {
    // Remember the state of favorite and shopping cart clicks
    val (isFavorite1, setFavorite1) = remember { mutableStateOf(false) }
    val (isFavorite2, setFavorite2) = remember { mutableStateOf(false) }
    val (isShoppingCart1, setShoppingCart1) = remember { mutableStateOf(false) }
    val (isShoppingCart2, setShoppingCart2) = remember { mutableStateOf(false) }

    // Show alert dialog when favorite icon is clicked
    val showAlertFavorite1 = remember { mutableStateOf(false) }
    val showAlertFavorite2 = remember { mutableStateOf(false) }

    // Show alert dialog when shopping cart icon is clicked
    val showAlertShoppingCart1 = remember { mutableStateOf(false) }
    val showAlertShoppingCart2 = remember { mutableStateOf(false) }

    Row(modifier = modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .weight(1f)
                .height(200.dp)
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(capteur1.imageResourceId),
                    contentDescription = stringResource(capteur1.stringResourceId),
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(0.7f), // Adjust the scale factor as needed
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = stringResource(capteur1.stringResourceId),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Change tint color based on state
                    val favoriteTint1 = if (isFavorite1) MaterialTheme.colorScheme.secondary else Color.Gray
                    val shoppingCartTint1 = if (isShoppingCart1) MaterialTheme.colorScheme.secondary else Color.Gray

                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = favoriteTint1,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                setFavorite1(!isFavorite1)
                                showAlertFavorite1.value = true
                            }
                    )
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Shopping Cart",
                        tint = shoppingCartTint1,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                setShoppingCart1(!isShoppingCart1)
                                showAlertShoppingCart1.value = true
                            }
                    )
                }
                Text(
                    text = stringResource(capteur1.stringResourcePrix),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        capteur2?.let {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(capteur2.imageResourceId),
                        contentDescription = stringResource(capteur2.stringResourceId),
                        modifier = Modifier
                            .fillMaxSize()
                            .scale(0.7f), // Adjust the scale factor as needed
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = stringResource(capteur2.stringResourceId),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Change tint color based on state
                        val favoriteTint2 = if (isFavorite2) MaterialTheme.colorScheme.secondary else Color.Gray
                        val shoppingCartTint2 = if (isShoppingCart2) MaterialTheme.colorScheme.secondary else Color.Gray

                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = favoriteTint2,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    setFavorite2(!isFavorite2)
                                    showAlertFavorite2.value = true
                                }
                        )
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Shopping Cart",
                            tint = shoppingCartTint2,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    setShoppingCart2(!isShoppingCart2)
                                    showAlertShoppingCart2.value = true
                                }
                        )
                    }
                    Text(
                        text = stringResource(capteur2.stringResourcePrix),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }

    // Alert dialogs
    if (showAlertFavorite1.value) {
        AlertDialog(
            onDismissRequest = { showAlertFavorite1.value = false },
            title = { Text(text = "Favorite") },
            text = { Text(text = "Object added to favorites!") },
            confirmButton = {
                Button(
                    onClick = { showAlertFavorite1.value = false },
                ) {
                    Text("OK")
                }
            }
        )
    }
    if (showAlertFavorite2.value) {
        AlertDialog(
            onDismissRequest = { showAlertFavorite2.value = false },
            title = { Text(text = "Favorite") },
            text = { Text(text = "Object added to favorites!") },
            confirmButton = {
                Button(
                    onClick = { showAlertFavorite2.value = false },
                ) {
                    Text("OK")
                }
            }
        )
    }
    if (showAlertShoppingCart1.value) {
        AlertDialog(
            onDismissRequest = { showAlertShoppingCart1.value = false },
            title = { Text(text = "Shopping Cart") },
            text = { Text(text = "Object added to your bag!") },
            confirmButton = {
                Button(
                    onClick = { showAlertShoppingCart1.value = false },
                ) {
                    Text("OK")
                }
            }
        )
    }
    if (showAlertShoppingCart2.value) {
        AlertDialog(
            onDismissRequest = { showAlertShoppingCart2.value = false },
            title = { Text(text = "Shopping Cart") },
            text = { Text(text = "Object added to your bag!") },
            confirmButton = {
                Button(
                    onClick = { showAlertShoppingCart2.value = false },
                ) {
                    Text("OK")
                }
            }
        )
    }
}
@Composable
fun ComposantApp() {
    val composantapp = ComposantInfo().loadAoutils()
    Composantlist(composantList = composantapp)
}



@Composable
fun Composantlist(composantList: List<Composant>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        val pairs = composantList.chunked(2)
        items(pairs) { pair ->
            ComposantCard(
                composant1 = pair[0],
                composant2 = pair.getOrNull(1), // Safely get the second item if it exists
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
@Composable
fun ComposantCard(
    composant1: Composant,
    composant2: Composant?,
    modifier: Modifier = Modifier
) {
    // Remember the state of favorite and shopping cart clicks
    val (isFavorite1, setFavorite1) = remember { mutableStateOf(false) }
    val (isFavorite2, setFavorite2) = remember { mutableStateOf(false) }
    val (isShoppingCart1, setShoppingCart1) = remember { mutableStateOf(false) }
    val (isShoppingCart2, setShoppingCart2) = remember { mutableStateOf(false) }

    // Show alert dialog when favorite icon is clicked
    val showAlertFavorite1 = remember { mutableStateOf(false) }
    val showAlertFavorite2 = remember { mutableStateOf(false) }

    // Show alert dialog when shopping cart icon is clicked
    val showAlertShoppingCart1 = remember { mutableStateOf(false) }
    val showAlertShoppingCart2 = remember { mutableStateOf(false) }

    Row(modifier = modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .weight(1f)
                .height(200.dp)
                .padding(4.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(composant1.imageResourceId),
                    contentDescription = stringResource(composant1.stringResourceId),
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(0.7f), // Adjust the scale factor as needed
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = stringResource(composant1.stringResourceId),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Change tint color based on state
                    val favoriteTint1 = if (isFavorite1) MaterialTheme.colorScheme.secondary else Color.Gray
                    val shoppingCartTint1 = if (isShoppingCart1) MaterialTheme.colorScheme.secondary else Color.Gray

                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = favoriteTint1,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                setFavorite1(!isFavorite1)
                                showAlertFavorite1.value = true
                            }
                    )
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Shopping Cart",
                        tint = shoppingCartTint1,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                setShoppingCart1(!isShoppingCart1)
                                showAlertShoppingCart1.value = true
                            }
                    )
                }
                Text(
                    text = stringResource(composant1.stringResourcePrix),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        composant2?.let {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
                    .padding(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(composant2.imageResourceId),
                        contentDescription = stringResource(composant2.stringResourceId),
                        modifier = Modifier
                            .fillMaxSize()
                            .scale(0.7f), // Adjust the scale factor as needed
                        contentScale = ContentScale.Crop

                    )
                    Text(
                        text = stringResource(composant2.stringResourceId),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Change tint color based on state
                        val favoriteTint2 = if (isFavorite2) MaterialTheme.colorScheme.secondary else Color.Gray
                        val shoppingCartTint2 = if (isShoppingCart2) MaterialTheme.colorScheme.secondary else Color.Gray

                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = favoriteTint2,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    setFavorite2(!isFavorite2)
                                    showAlertFavorite2.value = true
                                }
                        )
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Shopping Cart",
                            tint = shoppingCartTint2,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    setShoppingCart2(!isShoppingCart2)
                                    showAlertShoppingCart2.value = true
                                }
                        )
                    }
                    Text(
                        text = stringResource(composant2.stringResourcePrix),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }

    // Alert dialogs
    if (showAlertFavorite1.value) {
        AlertDialog(
            onDismissRequest = { showAlertFavorite1.value = false },
            title = { Text(text = "Favorite") },
            text = { Text(text = "Item added to favorites!") },
            confirmButton = {
                Button(
                    onClick = { showAlertFavorite1.value = false },
                ) {
                    Text("OK")
                }
            }
        )
    }
    if (showAlertFavorite2.value) {
        AlertDialog(
            onDismissRequest = { showAlertFavorite2.value = false },
            title = { Text(text = "Favorite") },
            text = { Text(text = "Item added to favorites!") },
            confirmButton = {
                Button(
                    onClick = { showAlertFavorite2.value = false },
                ) {
                    Text("OK")
                }
            }
        )
    }
    if (showAlertShoppingCart1.value) {
        AlertDialog(
            onDismissRequest = { showAlertShoppingCart1.value = false },
            title = { Text(text = "Shopping Cart") },
            text = { Text(text = "Item added to your shopping cart!") },
            confirmButton = {
                Button(
                    onClick = { showAlertShoppingCart1.value = false },
                ) {
                    Text("OK")
                }
            }
        )
    }
    if (showAlertShoppingCart2.value) {
        AlertDialog(
            onDismissRequest = { showAlertShoppingCart2.value = false },
            title = { Text(text = "Shopping Cart") },
            text = { Text(text = "Item added to your shopping cart!") },
            confirmButton = {
                Button(
                    onClick = { showAlertShoppingCart2.value = false },
                ) {
                    Text("OK")
                }
            }
        )
    }
}
@Composable
fun Feedback(

    firestore: FirebaseFirestore // Pass the instance of FirebaseFirestore
) {
    var feedbackText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var feedbackSent by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Provide Your Feedback",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = feedbackText,
                onValueChange = { feedbackText = it },
                placeholder = { Text("Enter your feedback") },
                label = { Text("Feedback") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (feedbackText.isNotEmpty()) {
                        // Add feedback to Firestore
                        firestore.collection("feedback")
                            .add(mapOf("text" to feedbackText))
                            .addOnSuccessListener {
                                // Set feedback sent flag to true
                                feedbackSent = true
                            }
                            .addOnFailureListener { exception ->
                                // Error occurred while saving feedback
                                errorMessage = "Failed to save feedback: ${exception.message}"
                            }
                    } else {
                        errorMessage = "Please enter your feedback"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Submit Feedback",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        // Show a Snackbar when feedback is successfully sent
        if (feedbackSent) {
            Snackbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                action = {
                    Button(onClick = { feedbackSent = false }) {
                        Text("OK")
                    }
                }
            ) {
                Text("Feedback submitted successfully!")
            }
        }
    }
}

@Composable
fun FeedbackList(firestore: FirebaseFirestore) {
    // State to hold feedback list
    var feedbackList by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Load feedback data from Firestore
    LaunchedEffect(Unit) {
        firestore.collection("feedback")
            .get()
            .addOnSuccessListener { result ->
                val feedbacks = result.mapNotNull { document ->
                    document.data
                }
                feedbackList = feedbacks
            }
            .addOnFailureListener { exception ->
                errorMessage = "Error loading feedback: ${exception.message}"
            }
    }

    // UI to display feedback
    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.feedback), // Ensure you have a drawable named feedback
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Semi-transparent overlay for better readability
        Box(
            modifier = Modifier
                .fillMaxSize()
        )

        if (feedbackList.isEmpty()) {
            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "Unknown error",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(feedbackList) { feedback ->
                    FeedbackItem(feedback)
                }
            }
        }
    }
}

@Composable
fun FeedbackItem(feedback: Map<String, Any>) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = feedback["text"] as? String ?: "No Feedback",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    var isSearchBarVisible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    if (isSearchBarVisible) {
        TextField(
            value = searchQuery,
            onValueChange = { newQuery ->
                searchQuery = newQuery
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp), // For smoother edges
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp) // Consistent height
                .padding(start = 8.dp) // Optional padding
        )
    }

    else {
        Spacer(modifier = Modifier.width(280.dp))

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            modifier = modifier
                .size(40.dp)
                .clickable { isSearchBarVisible = true }
                 )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Connexion(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Foreground content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "LOGIN AND START BUYING",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Enter your email", color = Color.Black) },
                label = { Text("Email", color = Color.Black) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email",
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.12f),
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Enter your password", color = Color.Black) },
                label = { Text("Password", color = Color.Black) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "password",
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor =MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.12f),
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val auth = Firebase.auth
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Login successful, navigate to the main screen
                                navController.navigate("fourth")
                            } else {
                                // If login fails, display a message to the user.
                                errorMessage = task.exception?.message
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "LOGIN",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                Text("You don't have an account ?",
                    color = Color.Black,)
                Spacer(modifier = Modifier.width(5.dp))
                ClickableText(
                    text = AnnotatedString("SignUp"),
                    onClick = {
                        navController.navigate("Signup")
                    },
                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Signup(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Foreground content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "SIGN UP AND JOIN THE IOT SHOP",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Enter your email", color = Color.Black) },
                label = { Text("Email", color = Color.Black) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email",
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f),
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Enter your password", color = Color.Black) },
                label = { Text("Password", color = Color.Black) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "password",
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f),
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = { Text("Confirm your password", color = Color.Black) },
                label = { Text("Confirm Password", color = Color.Black) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "confirm password",
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f),
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (password == confirmPassword) {
                        val auth = Firebase.auth
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Sign up successful, navigate to the main screen
                                    navController.navigate("Connexion")
                                } else {
                                    // If sign-up fails, display a message to the user.
                                    errorMessage = task.exception?.message
                                }
                            }
                    } else {
                        errorMessage = "Passwords do not match"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "SIGN UP",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                Text("Already have an account? ", color = Color.Black,)
                Spacer(modifier = Modifier.width(5.dp))
                ClickableText(
                    text = AnnotatedString("Login"),

                    onClick = {
                        navController.navigate("Connexion")
                    },
                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun Acceuil() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.earth),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to IoT Shop",
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                color = Color.White, // Set text color to black
                modifier = Modifier.padding(bottom = 16.dp)

            )
            Text(
                text = "Discover a wide range of IoT products, including:",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Black, // Set text color to black
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "- Cards",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Black, // Set text color to black
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "- Sensors",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Black, // Set text color to black
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "- Electronic components",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Black, // Set text color to black
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Start building your IoT projects today!",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Black // Set text color to black
            )
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "connexion")
    {
        composable(route="fourth") { LayoutDesign(navController,1) }

        composable(route="connexion") { Connexion(navController )}
        composable(route="Signup") { Signup(navController) }
        composable(route="app") { LayoutDesign(navController,4) }
        composable(route="second") { LayoutDesign(navController,2) }

        composable(route="third") { LayoutDesign(navController,3) }
        composable(route="feedback") { LayoutDesign(navController,5) }
        composable(route="affichefeedback") { LayoutDesign(navController,6) }

    }
}

