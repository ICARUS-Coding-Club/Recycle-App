package com.icarus.recycle_app.ui.study_game.ui.in_progress

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.study_game.classes.CarutaCard
import com.icarus.recycle_app.ui.study_game.utils.CardCreator
import com.icarus.recycle_app.ui.study_game.utils.TimeFormatConvertor
import com.icarus.recycle_app.utils.ServerConnectHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InProgressViewModel : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _elapsedTime = MutableLiveData("00:00.00")
    val elapsedTime: LiveData<String> get() = _elapsedTime
    private var rawTime = 0L

    private val _selectedCard = MutableLiveData<CarutaCard>()
    val selectedCard: LiveData<CarutaCard> get() = _selectedCard

    private val _showCards = MutableLiveData<MutableList<CarutaCard>>()
    val showCards: LiveData<MutableList<CarutaCard>> get() = _showCards

    private val _selectCards = MutableLiveData<MutableList<CarutaCard>>()
    val selectCards: LiveData<MutableList<CarutaCard>> get() = _selectCards

    init {

        val serverConnectHelper = ServerConnectHelper()

        val count = 30

        serverConnectHelper.requestTrashesRandom = object: ServerConnectHelper.RequestTrashes {
            override fun onSuccess(trashes: List<Trash>) {
                val showCards = mutableListOf<CarutaCard>()
                val selectCards = mutableListOf<CarutaCard>()
                Log.d("testx", "성공")

                for (trash in trashes) {
                    Log.d("testx", trash.toString())

                    val upCard = CarutaCard(source = trash.image)
                    showCards.add(upCard
                    )

                    val downCard = CarutaCard(description = trash.type)
                    selectCards.add(downCard)
                }

                val cardCreator = CardCreator(trashes.size, showCards, selectCards)
                _showCards.value = cardCreator.getConversionShowCards()
                _selectCards.value = cardCreator.getConversionSelectCards()
                selectRandomCard()
            }

            override fun onFailure() {
                Log.d("testx", "실패")
                val cardCreator = CardCreator(count)
                _showCards.value = cardCreator.getConversionShowCards()
                _selectCards.value = cardCreator.getConversionSelectCards()
                selectRandomCard()
            }
        }

        serverConnectHelper.getRandomTrashes(count)
    }

    fun selectRandomCard() {
        if (_showCards.value?.isNotEmpty() == true) {
            val randomCard = _showCards.value?.random()
            randomCard?.let {
                _selectedCard.postValue(it)
                _showCards.value?.remove(it)
            }

        }
    }

    fun startTimer() {
        uiScope.launch {
            while (true) {
                delay(1)
                rawTime += 1L
                _elapsedTime.postValue(TimeFormatConvertor.convertToTimeFormat(rawTime))

                if (rawTime % 500 == 0L) {
                    selectRandomCard()
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}