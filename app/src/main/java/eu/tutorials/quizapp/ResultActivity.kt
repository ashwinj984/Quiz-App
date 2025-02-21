package eu.tutorials.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result2)

        val username = intent.getStringExtra(Constants.USER_NAME)
        tv_name.text = username

        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)

        tv_score.text = "Your Score is $correctAnswer out of $totalQuestions"
        btn_finish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}