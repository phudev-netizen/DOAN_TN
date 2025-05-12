//package com.example.lapstore.viewmodels
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.lapstore.models.BinhLuan
//import com.example.lapstore.repositories.BinhLuanRepository
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class BinhLuanViewModel(private val repository: BinhLuanRepository) : ViewModel() {
//
//    private val _danhSachBinhLuan = MutableStateFlow<List<BinhLuan>>(emptyList())
//    val danhSachBinhLuan: StateFlow<List<BinhLuan>> get() = _danhSachBinhLuan
//
//    fun getBinhLuanBySanPham(maSanPham: Int) {
//        viewModelScope.launch {
//            try {
//                val binhLuans = repository.layBinhLuanTheoSanPham(maSanPham)
//                _danhSachBinhLuan.value = binhLuans
//            } catch (e: Exception) {
//                e.printStackTrace()
//                _danhSachBinhLuan.value = emptyList()
//            }
//        }
//    }
//
//    fun addBinhLuan(binhLuan: BinhLuan) {
//        viewModelScope.launch {
//            try {
//                repository.themBinhLuan(binhLuan)
//                getBinhLuanBySanPham(binhLuan.MaSanPham)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//}
//
