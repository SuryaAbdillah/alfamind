package com.example.alfamind

import android.graphics.BlurMaskFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfamind.ui.theme.AlfamindTheme
import kotlinx.coroutines.delay
import com.example.alfamind.data.Product
import com.example.alfamind.data.products

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlfamindTheme {
                // A surface container using the 'background' color from the theme
                MainApp()
            }
        }
    }
}

enum class Screen {
    Load, Login, Signup, Home
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf(Screen.Load) }

    LaunchedEffect(Unit) {
        delay(3000)
        currentScreen = Screen.Login
    }

    Crossfade(targetState = currentScreen) { screen ->
        when (screen) {
            Screen.Load -> LoadScreen()
            Screen.Login -> LoginScreen(
                onLoginSuccess = { currentScreen = Screen.Home },
                onSignupClick = { currentScreen = Screen.Signup }
            )
            Screen.Signup -> SignupScreen(
                onBackToLogin = { currentScreen = Screen.Login },
                onSignupSuccess = { currentScreen = Screen.Home }
            )
            Screen.Home -> HomeScreen()
        }
    }
}

@Composable
fun LoadScreen() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val padding = screenWidth * 0.2F

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {
        Image(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.image_size))
                .padding(padding),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}

@Preview
@Composable
fun LoadScreenPreview() {
    LoadScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onSignupClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val logoSize = screenWidth * 0.6F

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {
        Column(
            modifier = Modifier
                .width(logoSize)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.width(logoSize),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.app_name)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Email",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.abu_gelap),
                modifier = Modifier.width(logoSize)
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.merah_darah),
                    unfocusedBorderColor = colorResource(id = R.color.abu_terang)
                ),
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.abu_gelap)
                    )
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = colorResource(id = R.color.white))
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Password",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.abu_gelap),
                modifier = Modifier.width(logoSize)
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.merah_darah),
                    unfocusedBorderColor = colorResource(id = R.color.abu_terang)
                ),
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Text(
                        text = "******",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.abu_gelap)
                    )
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = colorResource(id = R.color.white))
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .clickable { onSignupClick() }, // Handle click event
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.belum_akun),
                color = colorResource(id = R.color.abu_gelap)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { onLoginSuccess() },
                modifier = Modifier.height(35.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.merah_darah)
                ),
                border = BorderStroke(width = 0.5.dp, color = colorResource(id = R.color.merah_terang))
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(onBackToLogin: () -> Unit, onSignupSuccess: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val logoSize = screenWidth * 0.6F

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password1 by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {
        Column(
            modifier = Modifier
                .width(logoSize)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.width(logoSize),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.app_name)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Username",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.abu_gelap),
                modifier = Modifier.width(logoSize)
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.merah_darah),
                    unfocusedBorderColor = colorResource(id = R.color.abu_terang)
                ),
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.abu_gelap)
                    )
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = colorResource(id = R.color.white))
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Email",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.abu_gelap),
                modifier = Modifier.width(logoSize)
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.merah_darah),
                    unfocusedBorderColor = colorResource(id = R.color.abu_terang)
                ),
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.abu_gelap)
                    )
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = colorResource(id = R.color.white))
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Password",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.abu_gelap),
                modifier = Modifier.width(logoSize)
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = password1,
                onValueChange = { password1 = it },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.merah_darah),
                    unfocusedBorderColor = colorResource(id = R.color.abu_terang)
                ),
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Text(
                        text = "******",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.abu_gelap)
                    )
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = colorResource(id = R.color.white))
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Konfirmasi Password",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.abu_gelap),
                modifier = Modifier.width(logoSize)
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = password2,
                onValueChange = { password2 = it },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.merah_darah),
                    unfocusedBorderColor = colorResource(id = R.color.abu_terang)
                ),
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Text(
                        text = "******",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.abu_gelap)
                    )
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = colorResource(id = R.color.white))
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .clickable { onBackToLogin() }, // Handle click event
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.sudah_akun),
                color = colorResource(id = R.color.abu_gelap)
            )

            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { onSignupSuccess() },
                modifier = Modifier.height(35.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.merah_darah)
                ),
                border = BorderStroke(width = 0.5.dp, color = colorResource(id = R.color.merah_terang))
            ) {
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Composable
fun AppTopBar() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white))
            .padding(horizontal = 20.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            modifier = Modifier
                .size(width=100.dp, height=50.dp),
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTopBar()
}

@Composable
fun HomeScreen() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val padding = screenWidth * 0.08F

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            AppTopBar()
            Row (
                modifier = Modifier
                    .padding(horizontal = padding)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column {
                        Row {
                            ProfilCard()
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            LazyVerticalStaggeredGrid(
                                columns = StaggeredGridCells.Fixed(2),
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentPadding = PaddingValues(16.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalItemSpacing = 0.dp
                            ) {
                                items(products) { product ->
                                    ProductItem(product = product)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth * 0.35F

    Card (
        modifier = Modifier
            .width(cardWidth)
            .height(200.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Column {
                Image(
                    painter = painterResource(id = product.imageProdukId),
                    contentDescription = stringResource(id = product.harga),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(cardWidth)
                )
                Spacer(modifier = Modifier.height(9.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.produk_price),
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Center,
                        lineHeight = 12.sp,
                        minLines = 1,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun ProfilCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = colorResource(id = R.color.merah_darah))
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profil),
                contentDescription = stringResource(id = R.string.foto_profil),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(10.dp)) // Clip image to have rounded corners
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.nama_owner),
                )
                Text(
                    text = stringResource(id = R.string.email_owner)
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfilPreview() {
    ProfilCard()
}

@Composable
fun CardProduk() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth * 0.35F

    Card (
        modifier = Modifier
            .width(cardWidth)
            .height(200.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.produk),
                    contentDescription = stringResource(id = R.string.produk_name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(cardWidth)
                )
                Spacer(modifier = Modifier.height(9.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.produk_price),
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Center,
                        lineHeight = 12.sp,
                        minLines = 1,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CardProdukPreview() {
    CardProduk()
}

fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 10.dp,
    blurRadius: Dp = 3.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + spreadPixel)
            val bottomPixel = (this.size.height + spreadPixel)

            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)