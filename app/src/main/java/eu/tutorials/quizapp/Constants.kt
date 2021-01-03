package eu.tutorials.quizapp

object Constants {

    const val USER_NAME :String = "user_name"
    const val TOTAL_QUESTIONS:String = "total_question"
    const val CORRECT_ANSWERS :String = "correct_answers"



    fun getQuestion() : ArrayList<Questions>{
        val questionsList = ArrayList<Questions>();
        val que1 =  Questions(
            1,
            "What Country Does this belong to?",
             R.drawable.download,
            "Flowers",
            "Mountains",
            "Lakes",
            "Beaches",
            1);
        questionsList.add(que1);

        val que2 = Questions(
            2,
            "Who is this?",
            R.drawable.ques,
            "Anuj",
            "Akhil",
            "Advik",
            "Ashwin",
            4
        );
        questionsList.add(que2);

        return questionsList;

    }
}