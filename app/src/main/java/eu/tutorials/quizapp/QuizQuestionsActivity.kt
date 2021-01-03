package eu.tutorials.quizapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import java.time.format.ResolverStyle

class QuizQuestionsActivity : AppCompatActivity() ,View.OnClickListener{

    private var mCurrentPosition:Int = 1
    private var mQuestionsList : ArrayList<Questions>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswers : Int = 0
    private var mUserName : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

         mQuestionsList = Constants.getQuestion();
        //Log.i("Questions size","${questionsList.size}")

        setQuestion()
        tv_option1.setOnClickListener(this)
        tv_option2.setOnClickListener(this)
        tv_option3.setOnClickListener(this)
        tv_option4.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

    }

    private fun setQuestion(){
//        mCurrentPosition = 1
        val question  = mQuestionsList!![mCurrentPosition -1]

        defaultOptionView()
        if(mCurrentPosition == mQuestionsList!!.size) {
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = "SUBMIT"
        }
        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max

        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)

        tv_option1.text = question.option1
        tv_option2.text = question.option2
        tv_option3.text = question.option3
        tv_option4.text = question.option4

        //question = mQuestionsList!![mCurrentPosition - 1  + 1]
    }

    private fun defaultOptionView(){


        val options = ArrayList<TextView>()
        options.add(0,tv_option1)
        options.add(1,tv_option2)
        options.add(2,tv_option3)
        options.add(3,tv_option4)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background =  ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )

        }

    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.tv_option1 -> {
                selectedOptionView(tv_option1, 1)
            }
            R.id.tv_option2 -> {
                selectedOptionView(tv_option2, 2)
            }
            R.id.tv_option3 -> {
                selectedOptionView(tv_option3, 3)
            }
            R.id.tv_option4 -> {
                selectedOptionView(tv_option4, 4)
            }//else -> Toast.makeText(this,"Press a button",Toast.LENGTH_SHORT)
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    //mSelectedOptionPosition++
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                        }
                    }

                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        btn_submit.text = "Finish"
                    }else{
                        btn_submit.text = "GO to Next Question"
                    }
                    mSelectedOptionPosition = 0
                }

            }

        }
    }
    private fun answerView(answer:Int,drawableView : Int){
        when(answer){
            1 -> {
                tv_option1.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tv_option2.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 ->{
                tv_option3.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4->{
                tv_option4.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }

    }

    private fun selectedOptionView(tv: TextView,selectedOptionNUmber:Int){
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNUmber
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background =  ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }
}