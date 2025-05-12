//import android.util.Log
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBackIosNew
//import androidx.compose.material.icons.filled.LocationOn
//import androidx.compose.material.icons.outlined.LocationOn
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Divider
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.SideEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
//import coil.compose.AsyncImage
//import com.example.lapstore.models.ChiTietHoaDonBan
//import com.example.lapstore.models.SanPham
//import com.example.lapstore.viewmodels.ChiTietHoaDonBanViewmodel
//import com.example.lapstore.viewmodels.DiaChiViewmodel
//import com.example.lapstore.viewmodels.HoaDonBanVỉewModel
//import com.google.accompanist.systemuicontroller.rememberSystemUiController
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DonHangDetailScreen(
//    navController: NavHostController,
//    mahoadon: Int,
//    tongtien: Int
//) {
//
//    val systemUiController = rememberSystemUiController()
//
//    SideEffect {
//        systemUiController.setStatusBarColor(color = Color.White, darkIcons = false)
//    }
//
//    val hoaDonBanVỉewModel: HoaDonBanVỉewModel = viewModel()
//    val chiTietHoaDonBanViewmodel: ChiTietHoaDonBanViewmodel = viewModel()
//    val diaChiViewmodel: DiaChiViewmodel = viewModel()
//    val sanPhamViewModel: SanPhamViewModel = viewModel()
//
//    var hoadon = hoaDonBanVỉewModel.HoaDonBan
//    val diachi = diaChiViewmodel.diachi
//    var danhsachchitiethoadon = chiTietHoaDonBanViewmodel.danhsachchitethoadon
//    val danhSachSanPham = sanPhamViewModel.danhSachSanPhamTrongHoaDon
//
//    val tongTienchuaship = danhsachchitiethoadon.sumOf {
//        val donGia = it.DonGia ?: 0
//        val soLuong = it.SoLuong ?: 0
//        donGia * soLuong
//    }
//
//    LaunchedEffect(mahoadon) {
//        hoaDonBanVỉewModel.getHoaDonByMaHoaDon(mahoadon)
//        sanPhamViewModel.getSanPhamTrongHoaDon(mahoadon)
//    }
//    if (hoadon != null) {
//        LaunchedEffect(hoadon.MaDiaChi) {
//            diaChiViewmodel.getDiaChiByMaDiaChi(hoadon.MaDiaChi)
//        }
//    }
//    LaunchedEffect(mahoadon) {
//        chiTietHoaDonBanViewmodel.getChiTietHoaDonTheoMaHoaDon(mahoadon)
//    }
//
//
//    Scaffold(
//        containerColor = Color.White,
//        topBar = {
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color.White
//                ),
//                title = {
//                    Text("Thông tin đơn hàng")
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        navController.popBackStack()
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBackIosNew,
//                            contentDescription = null,
//                            tint = Color.Red
//                        )
//                    }
//                }
//            )
//        }
//    ) { paddingValues ->
//        LazyColumn(
//            modifier = Modifier.padding(paddingValues)
//        ) {
//            // Card hiển thị địa chỉ nhận hàng
//            item {
//                if (diachi != null) {
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp),
//                        elevation = CardDefaults.cardElevation(4.dp),
//                        colors = CardDefaults.cardColors(containerColor = Color.White)
//                    ) {
//                        Column(
//                            modifier = Modifier.padding(10.dp),
//                            horizontalAlignment = Alignment.Start,
//                            verticalArrangement = Arrangement.Center
//                        ) {
//                            Text(
//                                "Địa chỉ nhận hàng",
//                                modifier = Modifier.padding(bottom = 10.dp),
//                            )
//                            Row(verticalAlignment = Alignment.CenterVertically) {
//                                Icon(
//                                    imageVector = Icons.Outlined.LocationOn,
//                                    contentDescription = null,
//                                    modifier = Modifier.size(24.dp),
//                                    tint = Color.Red
//                                )
//                                Spacer(modifier = Modifier.width(8.dp))
//                                Column {
//                                    Text(
//                                        text = diachi.TenNguoiNhan,
//                                    )
//                                    Text(
//                                        text = "SDT: ${diachi.SoDienThoai}",
//                                    )
//
//                                    Text(
//                                        text = diachi.ThongTinDiaChi,
//                                    )
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            // Card lớn chứa tất cả sản phẩm
//            item {
//                if (danhSachSanPham.isNotEmpty()) {
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(10.dp),
//                        elevation = CardDefaults.cardElevation(4.dp),
//                        colors = CardDefaults.cardColors(containerColor = Color.White)
//                    ) {
//                        Column(
//                            modifier = Modifier.padding(10.dp),
//                            verticalArrangement = Arrangement.Top,
//                            horizontalAlignment = Alignment.Start
//                        ) {
//                            Text(
//                                "Danh sách sản phẩm",
//                                modifier = Modifier.padding(bottom = 10.dp),
//                            )
//                            // Hiển thị danh sách sản phẩm trong một cột
//                            for (sanpham in danhSachSanPham) {
//                                Row(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(vertical = 8.dp),
//                                    verticalAlignment = Alignment.CenterVertically,
//                                    horizontalArrangement = Arrangement.Start
//                                ) {
//                                    AsyncImage(
//                                        model = sanpham.HinhAnh,
//                                        contentDescription = null,
//                                        modifier = Modifier
//                                            .size(80.dp)
//                                            .padding(end = 8.dp),
//                                        contentScale = ContentScale.Fit
//                                    )
//                                    Column(
//                                        modifier = Modifier
//                                            .weight(1f)
//                                            .fillMaxWidth()
//                                    ) {
//                                        Text(
//                                            text = sanpham.TenSanPham,
//                                        )
//                                        Row(
//                                            modifier = Modifier.fillMaxWidth(),
//                                            horizontalArrangement = Arrangement.SpaceBetween,
//                                            verticalAlignment = Alignment.CenterVertically
//                                        ) {
//                                            Text(
//                                                text = formatGiaTien(sanpham.Gia),
//                                            )
//                                            for (cthd in danhsachchitiethoadon) {
//                                                if (cthd.MaSanPham == sanpham.MaSanPham) {
//                                                    Text(
//                                                        text = "x${cthd.SoLuong}",
//                                                    )
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        HorizontalDivider(
//                            color = Color.Gray,
//                            thickness = 0.5.dp,
//                            modifier = Modifier.padding(3.dp)
//                        )
//
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(10.dp)
//                        ) {
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceBetween,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Text(
//                                    "Tổng tiền hàng"
//                                )
//                                Text(
//                                    formatGiaTien(tongTienchuaship),
//                                    fontWeight = FontWeight.Bold
//                                )
//                            }
//
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceBetween,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Text(
//                                    "Tổng tiền vận chuyển"
//                                )
//                                Text(
//                                    formatGiaTien(30000),
//                                    fontWeight = FontWeight.Bold
//                                )
//                            }
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceBetween,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Text(
//                                    "Thành tiền"
//                                )
//                                Text(
//                                    formatGiaTien(tongtien),
//                                    fontWeight = FontWeight.Bold
//                                )
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
//    }
//}

//code lại trang
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.lapstore.models.ChiTietHoaDonBan
import com.example.lapstore.models.SanPham
import com.example.lapstore.viewmodels.ChiTietHoaDonBanViewmodel
import com.example.lapstore.viewmodels.DiaChiViewmodel
import com.example.lapstore.viewmodels.HoaDonBanVỉewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonHangDetailScreen(
    navController: NavHostController,
    mahoadon: Int,
    tongtien: Int
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.White, darkIcons = true)
    }

    val hoaDonBanVỉewModel: HoaDonBanVỉewModel = viewModel()
    val chiTietHoaDonBanViewmodel: ChiTietHoaDonBanViewmodel = viewModel()
    val diaChiViewmodel: DiaChiViewmodel = viewModel()
    val sanPhamViewModel: SanPhamViewModel = viewModel()

    val hoadon = hoaDonBanVỉewModel.HoaDonBan
    val diachi = diaChiViewmodel.diachi
    val danhsachchitiethoadon = chiTietHoaDonBanViewmodel.danhsachchitethoadon
    val danhSachSanPham = sanPhamViewModel.danhSachSanPhamTrongHoaDon

    val tongTienchuaship = danhsachchitiethoadon.sumOf {
        (it.DonGia ?: 0) * (it.SoLuong ?: 0)
    }

    LaunchedEffect(mahoadon) {
        hoaDonBanVỉewModel.getHoaDonByMaHoaDon(mahoadon)
        sanPhamViewModel.getSanPhamTrongHoaDon(mahoadon)
        chiTietHoaDonBanViewmodel.getChiTietHoaDonTheoMaHoaDon(mahoadon)
    }

    if (hoadon != null) {
        LaunchedEffect(hoadon.MaDiaChi) {
            diaChiViewmodel.getDiaChiByMaDiaChi(hoadon.MaDiaChi)
        }
    }

    Scaffold(
        containerColor = Color(0xFFF8F8F8),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Thông tin đơn hàng",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color.Red
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {

            // Địa chỉ giao hàng
            item {
                if (diachi != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        elevation = CardDefaults.cardElevation(6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Địa chỉ nhận hàng",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.LocationOn,
                                    contentDescription = null,
                                    tint = Color.Red,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(text = diachi.TenNguoiNhan, fontWeight = FontWeight.Medium)
                                    Text(text = "SDT: ${diachi.SoDienThoai}")
                                    Text(text = diachi.ThongTinDiaChi)
                                }
                            }
                        }
                    }
                }
            }

            // Danh sách sản phẩm
            item {
                if (danhSachSanPham.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Danh sách sản phẩm",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            danhSachSanPham.forEach { sanpham ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = sanpham.HinhAnh,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(80.dp)
                                            .padding(end = 8.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = sanpham.TenSanPham,
                                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = formatGiaTien(sanpham.Gia),
                                                color = Color.Red,
                                                fontWeight = FontWeight.Bold
                                            )
                                            val sl = danhsachchitiethoadon.find { it.MaSanPham == sanpham.MaSanPham }?.SoLuong ?: 0
                                            Text("x$sl")
                                        }
                                    }
                                }
                            }

                            HorizontalDivider(
                                color = Color.LightGray,
                                thickness = 0.8.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            // Tổng tiền
                            Column {
                                InfoRow("Tổng tiền hàng", formatGiaTien(tongTienchuaship))
                                InfoRow("Phí vận chuyển", formatGiaTien(30000))
                                InfoRow(
                                    "Thành tiền",
                                    formatGiaTien(tongtien),
                                    isHighlight = true
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(title: String, value: String, isHighlight: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = if (isHighlight) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            color = if (isHighlight) Color(0xFFD32F2F) else Color.Black,
            fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Normal,
            style = if (isHighlight) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium
        )
    }
}
