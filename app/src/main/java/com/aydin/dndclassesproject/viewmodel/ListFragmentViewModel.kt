package com.aydin.dndclassesproject.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aydin.dndclassesproject.model.Class
import com.aydin.dndclassesproject.roomDb.ClassDatabase
import com.aydin.dndclassesproject.service.ClassAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListFragmentViewModel(application: Application) : AndroidViewModel(application){

    private val classAPIService = ClassAPIService()
    val classLiveData = MutableLiveData<List<Class>>()

    fun getDataFromInternetAndSaveToRoom () {
        viewModelScope.launch (Dispatchers.IO) {
            var classList = classAPIService.getData()
            val dao = ClassDatabase(getApplication()).classDao()
            withContext(Dispatchers.Main){
                setDataToRoom(classList)
                Toast.makeText(getApplication(), "Veriler internetten Alındı", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getDataFromRoom () {
        viewModelScope.launch (Dispatchers.IO) {
            val classes = ClassDatabase(getApplication()).classDao().getAll()
            withContext(Dispatchers.Main){
                showClasses(classes)
                Toast.makeText(getApplication(), "Veriler Roomdan Alındı", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setDataToRoom (classes : List<Class>) {
        viewModelScope.launch {
            val dao = ClassDatabase(getApplication()).classDao()
            dao.deleteAll()
            val idList = dao.insertAll(*classes.toTypedArray())
            var i = 0
            while (i < idList.size) {
                classes[i].uuid = idList[i].toInt()
                i = i + 1
            }
            withContext(Dispatchers.Main){
                showClasses(classes)
            }
        }
    }

    private fun showClasses(classes: List<Class>) {
        classLiveData.value = classes
    }
}