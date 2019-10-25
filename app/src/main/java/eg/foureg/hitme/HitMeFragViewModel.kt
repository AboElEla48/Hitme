package eg.foureg.hitme

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import eg.foureg.hitme.logger.Logger
import eg.foureg.hitme.rand.generateRandom

class HitMeFragViewModel : ViewModel() {
    val LOG_TAG = "HitMeFragViewModel"

    var targetScore : MutableLiveData<Int> = MutableLiveData()

    fun generateNewTarget() {
        //generate new target score
        targetScore.value = generateRandom()
        Logger.debug(LOG_TAG, "Random Target Score is: ${targetScore.value}")

    }
}