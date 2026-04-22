package com.example.cmp_mvi_template.ui.component.badges

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview

@DefaultPreview
@Composable
fun BadgePreview() {
    var itemCount by remember { mutableIntStateOf(0) }

    ComposeThemePreview {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BadgedBox(
                    badge = {
                        Badge()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email",
                    )
                }
                BadgedBox(
                    badge = {
                        if (itemCount > 0) {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                            ) {
                                Text("$itemCount")
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Shopping cart",
                    )
                }
                BadgedBox(
                    modifier = Modifier.size(100.dp),
                    badge = {
                        Badge(
                            modifier = Modifier.size(20.dp),
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email",
                        modifier = Modifier.size(100.dp),
                    )
                }
                BadgedBox(
                    modifier = Modifier.size(100.dp),
                    badge = {
                        if (itemCount > 0) {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                                modifier = Modifier.size(30.dp),
                            ) {
                                Text("$itemCount", style = MaterialTheme.typography.labelLarge)
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Shopping cart",
                        modifier = Modifier.size(100.dp),
                    )
                }
                Button(onClick = { itemCount++ }) {
                    Text("Add item")
                }
            }
        }
    }
}