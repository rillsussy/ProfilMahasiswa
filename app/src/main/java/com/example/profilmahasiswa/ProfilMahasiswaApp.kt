package com.example.profilmahasiswa

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.profilmahasiswa.ui.theme.BorderColor
import com.example.profilmahasiswa.ui.theme.DisabledGray
import com.example.profilmahasiswa.ui.theme.ErrorRed
import com.example.profilmahasiswa.ui.theme.PrimaryBlue
import com.example.profilmahasiswa.ui.theme.TextHint
import com.example.profilmahasiswa.ui.theme.TextPrimary
import com.example.profilmahasiswa.ui.theme.TextSecondary
import com.example.profilmahasiswa.ui.theme.UniverMuliaGold
import com.example.profilmahasiswa.ui.theme.UniverMuliaGreen

// ─────────────────────────────────────────────────────────────────────────────
// Root composable with full background
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ProfilMahasiswaApp(viewModel: ProfilViewModel = viewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.showSnackbar) {
        if (viewModel.showSnackbar) {
            val result = snackbarHostState.showSnackbar(
                message = "Data profil berhasil disimpan!",
                actionLabel = "OK",
                duration = SnackbarDuration.Short
            )
            if (result == SnackbarResult.ActionPerformed || result == SnackbarResult.Dismissed) {
                viewModel.onSnackbarDismissed()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // ── Custom Background ──
        Image(
            painter = painterResource(id = R.drawable.bg_profil_mahasiswa),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // ── Scaffold overlay ──
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) { data ->
                    Snackbar(
                        containerColor = Color(0xFF1E293B),
                        contentColor = Color.White,
                        shape = RoundedCornerShape(12.dp),
                        action = {
                            data.visuals.actionLabel?.let { actionLabel ->
                                TextButton(onClick = { data.performAction() }) {
                                    Text(
                                        text = actionLabel,
                                        color = Color(0xFF60A5FA),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = null,
                                tint = Color(0xFF22C55E),
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = data.visuals.message,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState())
            ) {
                // ── Top Header with University Logo and Building ──
                UniversityHeader()

                Spacer(modifier = Modifier.height(10.dp))

                // ── Main Form Card ──
                FormCard(viewModel = viewModel)

                Spacer(modifier = Modifier.height(16.dp))

                // ── Digital Student Card ──
                DigitalStudentCard(profile = viewModel.savedProfile)

                Spacer(modifier = Modifier.height(10.dp))

                // ── Footer with Motto Slogan ──
                FooterSection()

                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// University Header: Logo & Title on Left, Campus Building on Right
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun UniversityHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .padding(top = 4.dp)
    ) {
        // Building photo on top-right
        Image(
            painter = painterResource(id = R.drawable.header_building),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .height(108.dp),
            contentScale = ContentScale.FillHeight
        )

        // Logo + Branding on left
        Row(
            modifier = Modifier
                .fillMaxWidth(0.70f)
                .align(Alignment.CenterStart)
                .padding(start = 14.dp, top = 6.dp, bottom = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "Logo Aplikasi",
                    modifier = Modifier.size(52.dp)
                )
            }

            Box(
                modifier = Modifier
                    .height(44.dp)
                    .width(1.dp)
                    .background(Color(0xFFCBD5E1))
            )

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "SISTEM AKADEMIK TERPADU",
                    color = Color(0xFF1E5BB8),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "UNIVERSITAS MULIA",
                    color = Color(0xFF0C2556),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-0.2).sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "Inovatif",
                        color = Color(0xFF4A5568),
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFF9800))
                    )
                    Text(
                        text = "Mandiri",
                        color = Color(0xFF4A5568),
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFF9800))
                    )
                    Text(
                        text = "Humanis",
                        color = Color(0xFF4A5568),
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Form Card (White Rounded Container)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun FormCard(viewModel: ProfilViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // Title row
            FormTitleRow()

            Spacer(modifier = Modifier.height(16.dp))

            // Status preview card
            StatusPreviewCard(profile = viewModel.savedProfile)

            Spacer(modifier = Modifier.height(18.dp))

            // Input: Nama Lengkap
            FormFieldWithIcon(
                icon = Icons.Default.PersonOutline,
                iconTint = PrimaryBlue,
                label = "Nama Lengkap",
                value = viewModel.namaInput,
                onValueChange = viewModel::onNamaChange,
                placeholder = "Masukkan nama lengkap",
                errorMessage = viewModel.namaError,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                onFocusLost = viewModel::onNamaFocusLost
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input: NIM
            FormFieldWithIcon(
                icon = Icons.Default.Badge,
                iconTint = PrimaryBlue,
                label = "NIM (Nomor Induk Mahasiswa)",
                value = viewModel.nimInput,
                onValueChange = viewModel::onNimChange,
                placeholder = "Contoh: 2411068",
                errorMessage = viewModel.nimError,
                isNimField = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                onFocusLost = viewModel::onNimFocusLost
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input: Program Studi
            ProgramStudiDropdownField(
                value = viewModel.prodiInput,
                onValueChange = viewModel::onProdiChange,
                errorMessage = viewModel.prodiError,
                onFocusLost = viewModel::onProdiFocusLost
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Simpan Perubahan Button
            SaveButton(
                enabled = viewModel.isFormValid,
                onClick = viewModel::simpanData
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Form Title Row: Icon + Title on Left, Motto on Right
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun FormTitleRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(Color(0xFF1E5BB8)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }
            Column {
                Text(
                    text = "Form Profil Mahasiswa",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0C2556)
                )
                Text(
                    text = "Lengkapi data diri Anda untuk melanjutkan proses akademik.",
                    fontSize = 11.5.sp,
                    color = Color(0xFF64748B),
                    lineHeight = 15.sp
                )
            }
        }

        // Right motto text with yellow underline
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(start = 6.dp)
        ) {
            Text(
                text = "Bersama",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E5BB8)
            )
            Text(
                text = "Membangun",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E5BB8)
            )
            Text(
                text = "Masa Depan",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E5BB8)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .width(36.dp)
                    .height(2.5.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color(0xFFFFB300))
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Status Preview Card (Light Blue Gradient Container)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun StatusPreviewCard(profile: MahasiswaProfile?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFE9F2FD),
                        Color(0xFFF3F8FF)
                    )
                )
            )
            .border(1.dp, Color(0xFFD4E4FC), RoundedCornerShape(16.dp))
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // 3D Graduation Cap Icon
            Image(
                painter = painterResource(id = R.drawable.ic_grad_cap),
                contentDescription = null,
                modifier = Modifier.size(46.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (profile != null) profile.nama else "Belum ada data",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF0C2556)
                )
                Text(
                    text = if (profile != null)
                        "NIM: ${profile.nim}  •  ${profile.programStudi}"
                    else
                        "Silakan lengkapi data profil mahasiswa Anda.",
                    fontSize = 12.sp,
                    color = Color(0xFF5B6E8C),
                    lineHeight = 16.sp
                )
            }

            // Arrow button
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD4E4FC)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color(0xFF1E5BB8),
                    modifier = Modifier.size(17.dp)
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Form Field with Label, Leading Icon, and Green Validation Checkmark
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun FormFieldWithIcon(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    errorMessage: String?,
    isNimField: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onFocusLost: () -> Unit = {}
) {
    val isError = errorMessage != null
    val isValid = value.isNotEmpty() && !isError

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        // Label row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = label,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E293B)
            )
            Text(
                text = " *",
                color = Color(0xFFE53E3E),
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Text field
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) onFocusLost()
                },
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF94A3B8),
                    fontSize = 14.sp
                )
            },
            leadingIcon = if (isNimField) {
                {
                    Box(
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .size(28.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFEDF2F7)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color(0xFF64748B),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            } else null,
            trailingIcon = {
                AnimatedVisibility(
                    visible = isValid,
                    enter = fadeIn(tween(200)),
                    exit = fadeOut(tween(200))
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Valid",
                        tint = Color(0xFF22C55E),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(20.dp)
                    )
                }
            },
            isError = isError,
            supportingText = if (isError) {
                {
                    Text(
                        text = errorMessage!!,
                        color = ErrorRed,
                        fontSize = 12.sp
                    )
                }
            } else null,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryBlue,
                unfocusedBorderColor = Color(0xFFCBD5E1),
                errorBorderColor = ErrorRed,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                errorContainerColor = Color(0xFFFFF5F5),
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                cursorColor = PrimaryBlue
            )
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Program Studi Input Field with Dropdown Options
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun ProgramStudiDropdownField(
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String?,
    onFocusLost: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    val prodiOptions = remember {
        listOf(
            "S1 Informatika",
            "S1 Sistem Informasi",
            "S1 Teknologi Informasi",
            "D3 Manajemen Informatika",
            "S1 Manajemen",
            "S1 Akuntansi",
            "S1 Hukum",
            "S1 Farmasi",
            "S1 Pendidikan Guru Sekolah Dasar",
            "S1 Pendidikan Guru PAUD"
        )
    }

    val isError = errorMessage != null
    val isValid = value.trim().length >= 3 && !isError

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        // Label row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = Icons.Default.School,
                contentDescription = null,
                tint = PrimaryBlue,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "Program Studi",
                fontSize = 13.5.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E293B)
            )
            Text(
                text = " *",
                color = Color(0xFFE53E3E),
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) onFocusLost()
                    },
                placeholder = {
                    Text(
                        text = "Contoh: S1 Informatika",
                        color = Color(0xFF94A3B8),
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier.padding(end = 6.dp)
                    ) {
                        AnimatedVisibility(
                            visible = isValid,
                            enter = fadeIn(tween(200)),
                            exit = fadeOut(tween(200))
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Valid",
                                tint = Color(0xFF22C55E),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Pilih Program Studi",
                                tint = Color(0xFF64748B),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                isError = isError,
                supportingText = if (isError) {
                    {
                        Text(
                            text = errorMessage!!,
                            color = ErrorRed,
                            fontSize = 12.sp
                        )
                    }
                } else null,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    unfocusedBorderColor = Color(0xFFCBD5E1),
                    errorBorderColor = ErrorRed,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    errorContainerColor = Color(0xFFFFF5F5),
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = PrimaryBlue
                )
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                prodiOptions.forEach { prodi ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = prodi,
                                fontSize = 14.sp,
                                fontWeight = if (prodi == value) FontWeight.Bold else FontWeight.Normal,
                                color = if (prodi == value) PrimaryBlue else Color(0xFF1E293B)
                            )
                        },
                        onClick = {
                            onValueChange(prodi)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Save Button with White Circle Checkmark and Arrow
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun SaveButton(enabled: Boolean, onClick: () -> Unit) {
    val scale by animateFloatAsState(
        targetValue = if (enabled) 1f else 0.98f,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "buttonScale"
    )

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .scale(scale),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1E5BB8),
            disabledContainerColor = Color(0xFF94A3B8),
            contentColor = Color.White,
            disabledContentColor = Color.White.copy(alpha = 0.75f)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (enabled) 4.dp else 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // White circular badge with blue checkmark
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = if (enabled) Color(0xFF1E5BB8) else Color(0xFF94A3B8),
                    modifier = Modifier.size(15.dp)
                )
            }

            Text(
                text = "Simpan Perubahan",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.3.sp
            )

            // Right arrow
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Digital Student Card (Navy Blue Card with Watermark & 4 Columns)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun DigitalStudentCard(profile: MahasiswaProfile?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF092252),
                            Color(0xFF0A2862),
                            Color(0xFF061A42)
                        )
                    )
                )
        ) {
            // Building watermark in top-right of card
            Image(
                painter = painterResource(id = R.drawable.card_building_bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .height(130.dp)
                    .align(Alignment.TopEnd),
                contentScale = ContentScale.Crop,
                alpha = 0.50f
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 16.dp)
            ) {
                // Header row: Gold Cap + KARTU DIGITAL • SEMESTER GENAP + Profil Mahasiswa
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_gold_grad_cap),
                        contentDescription = null,
                        modifier = Modifier.size(38.dp)
                    )

                    Box(
                        modifier = Modifier
                            .height(38.dp)
                            .width(1.dp)
                            .background(Color(0xFF60A5FA).copy(alpha = 0.4f))
                    )

                    Column {
                        Text(
                            text = "KARTU DIGITAL  •  SEMESTER GENAP",
                            color = Color(0xFF93C5FD),
                            fontSize = 9.5.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Profil Mahasiswa",
                            color = Color.White,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                HorizontalDivider(
                    color = Color.White.copy(alpha = 0.12f),
                    thickness = 1.dp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 4 column data section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DigitalCardColumn(
                        icon = Icons.Default.Person,
                        label = "Nama",
                        value = profile?.nama,
                        modifier = Modifier.weight(1.1f)
                    )
                    VerticalDivider(
                        color = Color.White.copy(alpha = 0.15f),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                    DigitalCardColumn(
                        icon = Icons.Default.Badge,
                        label = "NIM",
                        value = profile?.nim,
                        modifier = Modifier.weight(0.9f)
                    )
                    VerticalDivider(
                        color = Color.White.copy(alpha = 0.15f),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                    DigitalCardColumn(
                        icon = Icons.AutoMirrored.Filled.MenuBook,
                        label = "Program Studi",
                        value = profile?.programStudi,
                        modifier = Modifier.weight(1.1f)
                    )
                    VerticalDivider(
                        color = Color.White.copy(alpha = 0.15f),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                    DigitalCardColumn(
                        icon = Icons.Default.AccountBalance,
                        label = "Perguruan Tinggi",
                        value = "Universitas Mulia",
                        valueColor = Color(0xFFFFC107),
                        modifier = Modifier.weight(1.2f)
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Digital Card Single Data Column
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun DigitalCardColumn(
    icon: ImageVector,
    label: String,
    value: String?,
    valueColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.70f),
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.65f),
            fontSize = 9.5.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = value ?: "-",
            color = if (value != null) valueColor else Color.White.copy(alpha = 0.5f),
            fontSize = 11.sp,
            fontWeight = if (value != null) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Footer with Motto Slogan
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun FooterSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 18.dp, top = 6.dp, bottom = 32.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Image(
            painter = painterResource(id = R.drawable.langkah_kecil),
            contentDescription = "Langkah Kecil Menuju Masa Depan Besar",
            modifier = Modifier.width(165.dp)
        )
    }
}
