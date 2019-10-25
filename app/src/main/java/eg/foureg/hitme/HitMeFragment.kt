package eg.foureg.hitme


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_hit_me.*


/**
 * This fragment to show the Hitme game board
 *
 */
class HitMeFragment : Fragment() {

    private lateinit var viewModel: HitMeFragViewModel
    private var disposables: ArrayList<Disposable> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hit_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HitMeFragViewModel::class.java)

        // set value of generated target score into target text view
        viewModel.targetScore.observe(this, Observer { target: Int? ->
            hit_me_target_text_view.text = target.toString()
        })

        // set value of current seek value text view
        viewModel.currentSeekVal.observe(this, Observer { seekVal: Int? ->
            hit_me_seek_score_text_view.text = seekVal.toString()
        })

        // set result trials score counter
        viewModel.resultScoreStr.observe(this, Observer { str ->
            hit_me_score_text_view.text = str
        })

        viewModel.resultScoreColor.observe(this, Observer {  color ->
            hit_me_seek_score_text_view.setTextColor(color!!)
        } )

        // Handle click on generate new score target
        disposables.add(RxView.clicks(hit_me_generate_target_btn).subscribe {
            viewModel.generateNewTarget()
        })

        // Handle click on hitme
        disposables.add(RxView.clicks(hit_me_btn).subscribe {
            viewModel.hitme(resources, hit_me_seek_bar.progress)
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        Observable.fromIterable(disposables)
            .blockingSubscribe { item ->
                item.dispose()
            }
    }

}
