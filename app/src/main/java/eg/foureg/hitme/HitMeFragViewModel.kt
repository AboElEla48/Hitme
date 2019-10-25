package eg.foureg.hitme

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import eg.foureg.hitme.logger.Logger
import eg.foureg.hitme.rand.generateRandom

class HitMeFragViewModel : ViewModel() {
    val LOG_TAG = "HitMeFragViewModel"

    val targetScore: MutableLiveData<Int> = MutableLiveData()
    val currentSeekVal: MutableLiveData<Int> = MutableLiveData()
    val resultScoreStr: MutableLiveData<String> = MutableLiveData()

    var trialsCounter = 0
    var successTrialsCounter = 0


    /**
     * Generate new target score
     */
    fun generateNewTarget() {
        //generate new target score
        targetScore.value = generateRandom()
        Logger.debug(LOG_TAG, "Random Target Score is: ${targetScore.value}")

    }

    /**
     * Handle user hitme challenge
     */
    fun hitme(score: Int) {
        currentSeekVal.value = score

        if(score == targetScore.value) {
            ++ successTrialsCounter
        }

        ++ trialsCounter

        resultScoreStr.value = "$successTrialsCounter / $trialsCounter"
    }
}