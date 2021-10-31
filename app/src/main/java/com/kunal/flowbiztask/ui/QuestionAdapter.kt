package com.kunal.flowbiztask.ui

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import android.widget.TextView
import androidx.cardview.widget.CardView
import de.hdodenhof.circleimageview.CircleImageView
import java.lang.Exception
import android.content.Intent
import com.kunal.flowbiztask.R
import com.kunal.flowbiztask.data.network.models.Question

import com.squareup.picasso.Picasso
import android.net.NetworkInfo

import androidx.core.content.ContextCompat.getSystemService

import android.net.ConnectivityManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemService








class QuestionAdapter(
    private val context:Context,
    private val questions:MutableList<Question>,
    private val ads:MutableList<String>
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_QUESTION = 0
    private var TYPE_AD = 1
    private var ad_cnt = 0
    private var questions_cnt = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_QUESTION){ // For Question Layout
            val rootView:View =
                LayoutInflater.from(context).inflate(R.layout.item_question,parent, false)
            val displayMetrics = DisplayMetrics()
            (context as Activity?)!!.windowManager
                .defaultDisplay
                .getMetrics(displayMetrics)
            val height = displayMetrics.heightPixels
            val newHeight = (height * 20)/100
            rootView.layoutParams.height = newHeight
            QuestionViewHolder(rootView)
        } else { // For Ad Layout
            val rootView:View =
                LayoutInflater.from(context).inflate(R.layout.item_ad,parent, false)
            val displayMetrics = DisplayMetrics()
            (context as Activity?)!!.windowManager
                .defaultDisplay
                .getMetrics(displayMetrics)
            val height = displayMetrics.heightPixels
            val newHeight = (height * 25)/100
            rootView.layoutParams.height = newHeight
            AdViewHolder(rootView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_AD){
            try {
                val adv = ads[ad_cnt]
                (holder as AdViewHolder).initAdVH(context,adv)
                ad_cnt++
            }catch (e:Exception){ }
        }
        else if(getItemViewType(position) == TYPE_QUESTION){
            try {
                val q = questions[questions_cnt]
                (holder as QuestionViewHolder).initQuestionVH(context,q)
                questions_cnt++
            }catch (e:Exception){ }
        }
    }

    override fun getItemCount(): Int {
        return questions.size + ads.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 4 == 0){
            TYPE_AD
        } else{
            TYPE_QUESTION
        }
    }


    class QuestionViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val card: CardView = itemView.findViewById(R.id.card)
        val image: CircleImageView = itemView.findViewById(R.id.image)
        val title:TextView = itemView.findViewById(R.id.question_title)
        val name:TextView = itemView.findViewById(R.id.owner_name)
        val date:TextView = itemView.findViewById(R.id.date)

        fun initQuestionVH(context: Context,question: Question) {
            Picasso.get().load(question.owner.profile_image).into(image)
            title.text = question.title
            name.text = question.owner.display_name
            card.setOnClickListener {
                if (isNetworkAvailable(context)){
                    context.startActivity(Intent(context.applicationContext, WebViewActivity::class.java).putExtra("link",question.link))
                }else{
                    Toast.makeText(
                        context.applicationContext,
                        "Internet Unavailable",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        private fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }



    class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.card)
        val image: ImageView = itemView.findViewById(R.id.image)

        fun initAdVH(context: Context,img: String) {
            Picasso.get().load(img).into(image)
        }
    }

}