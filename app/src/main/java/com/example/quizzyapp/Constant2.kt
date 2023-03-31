package com.example.quizzyapp

object Constant2 {

    public const val PLAYER_NAME = "user_name"
    public const val TOTAL_QUESTION = "no_of_questions"
    public const val CORRECT_ANS = "correct_answers"

    fun getQuestions():ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        var q = Question(1,
        "Which Company's logo is this?",
        R.drawable.img1,
        arrayOf<String>("MacAfee","Microsoft","Azure","Mazza"),
        1)
        questionsList.add(q)

        q = Question(2,
            "Which Company's logo is this?",
            R.drawable.img2,
            arrayOf<String>("Ubisoft","Uni","Utorrent","Uniball"),
            3)
        questionsList.add(q)

        q = Question(3,
            "Which Company's logo is this?",
            R.drawable.img3,
            arrayOf<String>("Kaza","Kingston","Kingfisher","Roblox"),
            2)
        questionsList.add(q)

        q = Question(4,
            "Which Company's logo is this?",
            R.drawable.img4,
            arrayOf<String>("Facebook","WhatsApp","Instagram","Meta"),
            4)
        questionsList.add(q)

        q = Question(5,
            "Which Company's logo is this?",
            R.drawable.img5,
            arrayOf<String>("Amazon","HeyReader","Kindle","YoLibrary"),
            3)
        questionsList.add(q)

        q = Question(6,
            "Which Company's logo is this?",
            R.drawable.img6,
            arrayOf<String>("Nasa","AeroSpace","BlueLabel","Reddit"),
            1)
        questionsList.add(q)

        q = Question(7,
            "Which Company's logo is this?",
            R.drawable.img7,
            arrayOf<String>("Chevrolet","Tata","Mahindra","Renault"),
            1)
        questionsList.add(q)

        q = Question(8,
            "Which Company's logo is this?",
            R.drawable.img8,
            arrayOf<String>("Dettol","Savlon","Dove","Lux"),
            3)
        questionsList.add(q)

        q = Question(9,
            "Which Company's logo is this?",
            R.drawable.img9,
            arrayOf<String>("Ferrari","Lamborghini","Bentley","Honda"),
            2)
        questionsList.add(q)

        q = Question(10,
            "Which Company's logo is this?",
            R.drawable.img10,
            arrayOf<String>("Brazzers","Indie","Naughty America","Vixen"),
            3)
        questionsList.add(q)

        return questionsList
    }
}