package fr.iut.animelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.animelist.ui.RetrofitClient
import kotlinx.coroutines.launch

class AnimeVM: ViewModel() {
    private val _status = MutableLiveData<String>()

    val status: LiveData<String> = _status

    init {
        getAnime()
    }

    private fun getAnime() {
        viewModelScope.launch {
            try {
                var listResult = RetrofitClient().getClient()?.getAnime()

                Log.e("Service", "success" + listResult?.size)
                _status.value = "Success: anime"
                Log.e("Service", "success 2")
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
                Log.e("Service", "Not success")
            }
        }
    }

}


