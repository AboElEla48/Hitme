package eg.foureg.hitme

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import eg.foureg.hitme.logger.Logger
import eg.foureg.hitme.rand.generateRandom

class HitMeFragViewModel : ViewModel() {
    val LOG_TAG = "HitMeFragViewModel"

    val targetScore: MutableLiveData<Int> = MutableLiveData()
    val currentSeekVal: MutableLiveData<Int> = MutableLiveData()
    val resultScoreStr: MutableLiveData<String> = MutableLiveData()
    val resultScoreColor: MutableLiveData<Int> = MutableLiveData()

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
    fun hitme(resources: Resources, score: Int) {
        currentSeekVal.value = score

        if(score == targetScore.value) {
            ++ successTrialsCounter
            resultScoreColor.value = resources.getColor(R.color.colorGreen, null)
        }
        else {
            resultScoreColor.value = resources.getColor(R.color.colorRed, null)
        }

        ++ trialsCounter

        resultScoreStr.value = "$successTrialsCounter / $trialsCounter"
    }
}