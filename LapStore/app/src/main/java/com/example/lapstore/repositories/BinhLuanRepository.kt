//package com.example.lapstore.repositories
//
//import com.example.lapstore.models.BinhLuan
//import kotlinx.coroutines.delay
//
//class BinhLuanRepository {
//    // Danh sách giả lập để lưu bình luận
//    private val danhSachBinhLuan = mutableListOf<BinhLuan>()
//
//    // Lấy danh sách bình luận theo mã sản phẩm
//    suspend fun layBinhLuanTheoSanPham(maSanPham: Int): List<BinhLuan> {
//        delay(500) // giả lập độ trễ mạng
//        return danhSachBinhLuan.filter { it.MaSanPham == maSanPham }
//    }
//
//    // Thêm bình luận mới
//    suspend fun themBinhLuan(binhLuan: BinhLuan) {
//        delay(200) // giả lập độ trễ khi thêm
//        val newId = (danhSachBinhLuan.maxOfOrNull { it.MaBinhLuan } ?: 0) + 1
//        val bl = binhLuan.copy(MaBinhLuan = newId)
//        danhSachBinhLuan.add(bl)
//    }
//}

