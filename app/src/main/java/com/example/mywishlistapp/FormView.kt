package com.example.mywishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mywishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun FormView(id: Long, viewModel: WishViewModel, navController: NavController) {
    val createFlag = -1L
    val snackMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope() // create coroutine scope to call suspending function
    val scaffoldState = rememberScaffoldState()

    if (id == createFlag) {
        viewModel.setTitle("")
        viewModel.setDescription("")
    } else {
        val detail = viewModel.getWishById(id).collectAsState(initial = Wish())
        viewModel.setTitle(detail.value.title)
        viewModel.setDescription(detail.value.description)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBarView(
                title = stringResource(buildButtonLabel(id)),
                onBackNavClick = {
                    navController.navigateUp()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            BaseTextField(
                label = "Title",
                value = viewModel.title.value,
                onValueChange = { value -> viewModel.setTitle(value) })

            Spacer(modifier = Modifier.height(10.dp))
            BaseTextField(
                label = "Description",
                value = viewModel.description.value,
                onValueChange = { value -> viewModel.setDescription(value) })

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if (validate(viewModel)) {
                    // if new then create
                    if (id == -1L) {
                        viewModel.addWish(
                            Wish(
                                title = viewModel.title.value.trim(),
                                description = viewModel.description.value.trim()
                            )
                        )
                        snackMessage.value = "Success"
                    } else {
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.title.value.trim(),
                                description = viewModel.description.value.trim()
                            )
                        )
                    }

                    scope.launch {
                        // show snackbar
                        // scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)

                        // navigate back
                        navController.navigateUp()
                    }
                } else {
                    // display validation error
                    snackMessage.value = "Validation error"
                }
            }) {
                Text(
                    text = stringResource(id = buildButtonLabel(id)),
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}

@Composable
fun BaseTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label, color = Color.Black)
        },
        modifier = Modifier.fillMaxWidth(),
        // handle what keyboard type should appear
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        ),
    )
}

fun buildButtonLabel(id: Long = -1L): Int {
    if (id != -1L) {
        return (R.string.update_wish)
    }

    return R.string.add_wish
}

fun validate(viewModel: WishViewModel): Boolean {
    return viewModel.title.value.isNotEmpty() && viewModel.description.value.isNotEmpty()
}
