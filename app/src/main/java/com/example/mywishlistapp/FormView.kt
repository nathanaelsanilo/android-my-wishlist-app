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
import androidx.compose.runtime.Composable
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

@Composable
fun FormView(id: Long, viewModel: WishViewModel, navController: NavController) {

    Scaffold(
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
                if (shouldEdit(viewModel)) {
                    // TODO: handle edit wish
                } else {
                    // TODO: handle add new wish
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

fun buildButtonLabel(id: Long = 0L) : Int {
    if (id == 0L) {
        return (R.string.update_wish)
    }

    return R.string.add_wish
}

fun shouldEdit(viewModel: WishViewModel): Boolean {
    return viewModel.title.value.isNotEmpty() && viewModel.description.value.isNotEmpty()
}