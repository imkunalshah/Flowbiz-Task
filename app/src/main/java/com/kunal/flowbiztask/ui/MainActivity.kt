package com.kunal.flowbiztask.ui

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kunal.flowbiztask.R

import com.kunal.flowbiztask.data.network.models.Question
import com.kunal.flowbiztask.databinding.ActivityMainBinding
import com.kunal.flowbiztask.util.EventListener
import com.kunal.flowbiztask.util.toast
import com.saksham.customloadingdialog.hideDialog
import com.saksham.customloadingdialog.showDialog
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.ArrayList

class MainActivity : AppCompatActivity(), KodeinAware,EventListener {
    lateinit var als: ArrayList<Question>
    lateinit var viewModel:QuestionsViewModel
    override val kodein by kodein()
    private val factory:QuestionsViewModelFactory by instance()
    lateinit var rvQuestions:RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var filter:ImageView
    lateinit var filters:MutableList<String>
    lateinit var go: AppCompatButton
    lateinit var searchET:EditText
    lateinit var adList:MutableList<String>
//    lateinit var scrollToTop: RelativeLayout

    var isSearchOn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this,factory).get(QuestionsViewModel::class.java)
        viewModel.eventListener = this
        rvQuestions = findViewById(R.id.rvQuestions)
        filter = findViewById(R.id.filter)
        go = findViewById(R.id.go)
        searchET = findViewById(R.id.searchET)
//        scrollToTop = findViewById(R.id.scroll_to_top)
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        viewModel.fetch()
        adList = ArrayList()
        adList.add("https://www.dance.nyc/images/opt/ad-sample-900x225.gif")
        adList.add("https://www.dance.nyc/images/opt/ad-sample-900x225.gif")
        adList.add("https://www.dance.nyc/images/opt/ad-sample-900x225.gif")
        adList.add("https://www.dance.nyc/images/opt/ad-sample-900x225.gif")
        adList.add("https://www.dance.nyc/images/opt/ad-sample-900x225.gif")
        adList.add("https://www.dance.nyc/images/opt/ad-sample-900x225.gif")
//        filters = ArrayList()
//        val fm: FragmentManager = this.supportFragmentManager

//        filter.setOnClickListener{
//            val bundle = Bundle()
//            bundle.putStringArrayList("tags",als.)
//            val filterBottomSheet = FilterBottomSheetFragment()
//            filterBottomSheet.arguments = bundle
//            filterBottomSheet.show(fm,"FM")
//        }
        go.setOnClickListener{
            showDialog(
                this,               //context or this
                false,          //dismiss dialog onBackPressed
                R.raw.load               //lottie file json stored in res/raw
            )
            val txt = searchET.text.trim().toString()
            if (txt.isEmpty()){
                toast("Name is required")
                hideDialog()
                return@setOnClickListener
            }
            else if (txt.length < 4){
                toast("Minimum 4 characters required")
                hideDialog()
                return@setOnClickListener
            }
            val filteredList:ArrayList<Question> = ArrayList()
            for (i in als.indices){
                val str = als[i].title.toLowerCase()
                if (str.contains(txt.toLowerCase())){
                    filteredList.add(als[i])
                }
            }
            rvQuestions.apply {
                layoutManager = this@MainActivity.layoutManager
                adapter = QuestionAdapter(this@MainActivity,filteredList.toMutableList(),adList)
            }
            isSearchOn = true
            hideDialog()
        }

//        rvQuestions.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (layoutManager.findFirstVisibleItemPosition() > 0) {
//                    scrollToTop.visibility = View.VISIBLE
//                } else {
//                    scrollToTop.visibility = View.GONE
//                }
//            }
//        })
    }

    override fun onSuccess() {
        als = viewModel.questions
        rvQuestions.apply {
            layoutManager = this@MainActivity.layoutManager
            adapter = QuestionAdapter(this@MainActivity,als,adList)
        }
        hideDialog()
    }

    override fun onFailure(message: String) {
        hideDialog()
        toast(message)
    }

    override fun onStarted() {
        showDialog(
            this,               //context or this
            false,          //dismiss dialog onBackPressed
            R.raw.load               //lottie file json stored in res/raw
        )
    }

    override fun onBackPressed() {
        if (isSearchOn){
            als = viewModel.questions
            rvQuestions.apply {
                layoutManager = this@MainActivity.layoutManager
                adapter = QuestionAdapter(this@MainActivity,als,adList)
            }
            searchET.text = null
            searchET.clearFocus()
            isSearchOn = false
        }
        else{
            showExitDialog()
        }
    }
    private fun showExitDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setTitle("Exit Or Not")
        builder.setMessage("Do you want to exit app? ")
        builder.setPositiveButton("YES", DialogInterface.OnClickListener { dialog, id ->
            finish()
        })
        builder.setNegativeButton("No",
            DialogInterface.OnClickListener { dialog, id -> dialog.dismiss() })
        builder.show()
    }


}