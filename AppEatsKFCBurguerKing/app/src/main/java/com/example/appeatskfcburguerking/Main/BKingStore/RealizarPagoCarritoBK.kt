package com.example.appeatskfcburguerking.Main.BKingStore

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Environment
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.platform.LocalContext
import com.example.appeatskfcburguerking.Main.BKingStore.Logic.CarritoViewModelBK
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import java.io.File
import java.io.FileOutputStream
import android.Manifest
import androidx.compose.ui.Alignment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.appeatskfcburguerking.Main.BKingStore.Model.TipoDeOpcion

private const val REQUEST_CODE = 1001
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RealizarPagoCarritoScreenBK(carritoViewModelBK: CarritoViewModelBK, navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var metodoPago by remember { mutableStateOf("Efectivo") }
    var tarjetaNumero by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var fechaExpiracion by remember { mutableStateOf("") }
    val context = LocalContext.current
    val activity = context as? Activity
    var mensajeError by remember { mutableStateOf("") }
    var mostrarDialogoExito by remember { mutableStateOf(false) }
    var mostrarDialogoConfirmacion by remember { mutableStateOf(false) }
    var horaRetiro by remember { mutableStateOf("") }

    val camposCompletos = nombre.isNotEmpty() && dni.isNotEmpty() && telefono.isNotEmpty() && correo.isNotEmpty()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Realizar Pago", color = Color.White)
                    }
                        },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        if (camposCompletos) {
                            mostrarDialogoConfirmacion = true
                        } else {
                            mensajeError = "Por favor, complete todos los campos antes de confirmar el pago."
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = camposCompletos,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text("Confirmar Pago", color = Color.White)
                }
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            item {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    label = { Text("Nombre Usuario",color = Color.White) }
                )
            }
            item {
                TextField(
                    value = dni,
                    onValueChange = { dni = it },
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    label = { Text("DNI o Cédula",color = Color.White) }
                )
            }
            item {
                TextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                    label = { Text("Número de Teléfono",color = Color.White) }
                )
            }
            item {
                TextField(
                    value = correo,
                    onValueChange = { correo = it },
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    label = { Text("Correo Electrónico",color = Color.White) }
                )
            }
            item {
                TextField(
                    value = horaRetiro,
                    onValueChange = { horaRetiro = it },
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    label = { Text("Hora de Retiro, HH:MM", color = Color.White) },
                )
            }
            item {
                Text("Método de Pago:", modifier = Modifier.padding(16.dp))
                Row(modifier = Modifier.padding(16.dp)) {
                    RadioButton(
                        selected = metodoPago == "Efectivo",
                        onClick = { metodoPago = "Efectivo" }
                    )
                    Text("Efectivo", modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    RadioButton(
                        selected = metodoPago == "Tarjeta",
                        onClick = { metodoPago = "Tarjeta" }
                    )
                    Text("Tarjeta de Débito", modifier = Modifier.padding(start = 8.dp))
                }
            }
            if (metodoPago == "Tarjeta") {
                item {
                    TextField(
                        value = tarjetaNumero,
                        onValueChange = { tarjetaNumero = it },
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        label = { Text("Número de Tarjeta",color = Color.White) }
                    )
                }
                item {
                    TextField(
                        value = cvv,
                        onValueChange = { cvv = it },
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        label = { Text("CVV (Ultimos 3 dígitos de su tarjeta, parte trasera)",color = Color.White) }
                    )
                }
                item {
                    TextField(
                        value = fechaExpiracion,
                        onValueChange = { fechaExpiracion = it },
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        label = { Text("MM/YY",color = Color.White) }
                    )
                }
            }
            item {
                if (mensajeError.isNotEmpty()) {
                    Text(
                        text = mensajeError,
                        color = Color.Blue,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }

    if (mostrarDialogoConfirmacion) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoConfirmacion = false },
            title = {
                Text(
                    text = "Confirmar Pago",
                    color = Color.White
                )
            },
            text = {
                Text(
                    text = "¿Estás seguro de realizar el pago?, Recuerda ver en Descargas el Recibo de Cobro",
                    color = Color.White
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            activity?.let {
                                ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE)
                            }
                        } else {
                            generarPDF(carritoViewModelBK, nombre, dni, telefono, correo,horaRetiro, metodoPago, tarjetaNumero)
                            mostrarDialogoConfirmacion = false
                            navController.navigate("tienda_brk")
                            carritoViewModelBK.limpiarCarrito()
                            carritoViewModelBK.limpiarHistorial()
                        }

                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Sí",color = Color.White)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDialogoConfirmacion = false
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("No",color = Color.White)
                }
            },
            modifier = Modifier.padding(16.dp)
        )


    }
}

fun generarPDF(
    carritoViewModel: CarritoViewModelBK,
    nombre: String,
    dni: String,
    telefono: String,
    correo: String,
    horaRetiro: String,
    metodoPago: String,
    tarjetaNumero: String,

) {
    try {
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
            println("El almacenamiento externo no está disponible.")
            return
        }

        val directory = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "OrdenesBurgerKing"
        )
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val fileName = "Recibo_Pago_${dni}_BURGERKINGSTORE.pdf"
        val pdfFile = File(directory, fileName)
        val outputStream = FileOutputStream(pdfFile)
        val writer = PdfWriter(outputStream)
        val font: PdfFont = PdfFontFactory.createFont("Helvetica-Bold")

        val pdfDocument = PdfDocument(writer)

        val documento = Document(pdfDocument)

        documento.add(Paragraph("Recibo de Pago Burger King").setFont(font).setFontSize(18f))

        documento.add(Paragraph(Text("")))

        documento.add(Paragraph("Nombre: $nombre"))
        documento.add(Paragraph("DNI: $dni"))
        documento.add(Paragraph("Teléfono: $telefono"))
        documento.add(Paragraph("Correo: $correo"))

        documento.add(Paragraph("Hora de Retiro: $horaRetiro"))

        documento.add(Paragraph(Text("")))

        documento.add(Paragraph("Método de Pago: $metodoPago"))
        if (metodoPago == "Tarjeta") {
            documento.add(Paragraph("Número de Tarjeta: ${"X".repeat(tarjetaNumero.length - 4)}${tarjetaNumero.takeLast(4)}"))
        }

        documento.add(Paragraph(Text("")))

        documento.add(Paragraph("Historial de Pedido"))
        val table = Table(4)
        table.addCell("Producto")
        table.addCell("Cantidad")
        table.addCell("Precio")
        table.addCell("Subtotal")

        carritoViewModel._productosSeleccionados.forEach { producto ->
            table.addCell(producto.nombre)
            table.addCell(producto.cantidad.toString())
            table.addCell("$${producto.precio}")
            table.addCell("$${(producto.precio.toDouble() * producto.cantidad)}")
        }

        carritoViewModel._opcionesSeleccionadas.forEach { opcion ->
            if (opcion.tipo == TipoDeOpcion.CARNE ) {
                table.addCell("Carne: "+opcion.nombre)
                table.addCell("1")
                table.addCell("NO APLICA")
                table.addCell("NO APLICA")
            } else {
                table.addCell(opcion.nombre)
                table.addCell(opcion.cantidad.toString())
                table.addCell("$${opcion.precio}")
                table.addCell("$${(opcion.precio * opcion.cantidad)}")
            }

        }

        documento.add(table)

        val total = carritoViewModel.calcularTotal()
        documento.add(Paragraph("Total: $$total"))

        documento.close()

        println("PDF generado con éxito: ${pdfFile.absolutePath}")

    } catch (e: Exception) {
        e.printStackTrace()
        println("Error al generar el PDF.")
    }
}



