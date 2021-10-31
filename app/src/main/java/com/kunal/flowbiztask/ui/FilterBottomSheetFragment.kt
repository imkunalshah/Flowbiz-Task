package com.kunal.flowbiztask.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.app.Activity
import android.content.DialogInterface
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kunal.flowbiztask.R
import com.kunal.flowbiztask.data.network.models.Question
import com.kunal.flowbiztask.databinding.FragmentFilterBottomSheetBinding
import com.kunal.flowbiztask.util.EventListener
import com.saksham.customloadingdialog.showDialog
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.util.*

class FilterBottomSheetFragment : BottomSheetDialogFragment(), KodeinAware, EventListener {
    lateinit var rv: RecyclerView
    lateinit var list: ArrayList<Question>
    lateinit var viewModel: FilterFragmentVM
    override val kodein by kodein()
    private val factory: FilterFragmentVMFactory by instance()
    lateinit var layoutManager: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_filter_bottom_sheet, container, false)

        viewModel = ViewModelProviders.of(this,factory).get(FilterFragmentVM::class.java)
        viewModel.eventListener = this
        list = ArrayList()
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        viewModel.fetch()
        rv = view.findViewById(R.id.rvTags)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager
            .defaultDisplay
            .getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val new_h = (height * 70)/100
        view.getLayoutParams().height = new_h

//        rv.apply {
//            layoutManager = this@FilterBottomSheetFragment.layoutManager
//            adapter = FilterAdapter(activity.applicationContext,)
//        }
        val apply_btn:AppCompatButton = view.findViewById(R.id.filter_button)

        apply_btn.setOnClickListener {
            showDialog(context,           //context or this
                true,          //dismiss dialog onBackPressed
                R.raw.load             //lottie file json stored in res/raw
            )
            dismiss()
        }

        val clear_btn:AppCompatButton = view.findViewById(R.id.clear_button)

        clear_btn.setOnClickListener {
            showDialog(context,           //context or this
                true,          //dismiss dialog onBackPressed
                R.raw.load             //lottie file json stored in res/raw
            )
            dismiss()
        }
    }

//    override fun onCancel(dialog: DialogInterface) {
//        super.onCancel(dialog)
//        Toast.makeText(activity, "Cancelled", Toast.LENGTH_SHORT).show()
//    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Toast.makeText(activity, "Dismissed", Toast.LENGTH_SHORT).show()
        showDialog(context,           //context or this
            true,          //dismiss dialog onBackPressed
            R.raw.load             //lottie file json stored in res/raw
        )
    }

    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onStarted() {
        TODO("Not yet implemented")
    }
}