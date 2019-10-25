package eg.foureg.hitme

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class HitMeFragViewModel : ViewModel() {
    var targetScore : MutableLiveData<Int> = MutableLiveData()

    fun generateNewTarget() {

    }
}