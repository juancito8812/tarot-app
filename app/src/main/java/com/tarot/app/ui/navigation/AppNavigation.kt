package com.tarot.app.ui.navigation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Deck
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tarot.app.ui.daily.DailyScreen
import com.tarot.app.ui.deck.DeckScreen
import com.tarot.app.ui.spreads.SpreadsScreen
import com.tarot.app.ui.theme.BackgroundGradient
import com.tarot.app.ui.theme.ClassicBurgundy
import com.tarot.app.ui.theme.ClassicGold

data class BottomNavItem(val route: String, val label: String, val icon: ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem("daily", "Diaria", Icons.Default.AutoAwesome),
        BottomNavItem("spreads", "Tiradas", Icons.Default.Shuffle),
        BottomNavItem("deck", "Mazo", Icons.Default.Deck)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Tarot",
                        fontFamily = FontFamily.Serif,
                        fontSize = 22.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ClassicBurgundy,
                    titleContentColor = ClassicGold
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = ClassicBurgundy,
                contentColor = ClassicGold
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label, fontFamily = FontFamily.Serif) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = ClassicGold,
                            selectedTextColor = ClassicGold,
                            unselectedIconColor = ClassicGold.copy(alpha = 0.5f),
                            unselectedTextColor = ClassicGold.copy(alpha = 0.5f),
                            indicatorColor = ClassicBurgundy.copy(alpha = 0.3f)
                        ),
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .background(BackgroundGradient)
        ) {
            NavHost(
                navController = navController,
                startDestination = "daily",
                modifier = Modifier.fillMaxSize()
            ) {
                composable("daily") { DailyScreen() }
                composable("spreads") { SpreadsScreen() }
                composable("deck") { DeckScreen() }
            }
        }
    }
}