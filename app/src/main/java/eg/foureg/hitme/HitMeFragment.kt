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

    private lateinit var viewModel : HitMeFragViewModel
    private var disposables : ArrayList<Disposable> = ArrayList()

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
        viewModel.targetScore.observe(this, Observer { target : Int? ->
            hit_me_target_text_view.text = target.toString()
        })

        disposables.add(RxView.clicks(hit_me_generate_target_btn).subscribe {
            viewModel.generateNewTarget()
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        Observable.fromIterable(disposables)
            .blockingSubscribe{ item ->
                item.dispose()
            }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         *
         * @return A new instance of fragment HitMeFragment.
         */
        @JvmStatic
        fun newInstance() =
            HitMeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
