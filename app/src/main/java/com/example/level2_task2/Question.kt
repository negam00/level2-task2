package com.example.level2_task2

data class Question(var question: String, var isCorrect: Boolean){
    companion object{
        val QUESTIONS = arrayOf(
            "Albert Einstein was awarded the Nobel Prize in Physics.",
            "Baby koalas are called joeys.",
            "Brazil is the only country whose official language is Portuguese.",
            "No bird can fly backwards.",
            "Adults have more bones than babies do."
        )
        val IS_CORRECT = arrayOf(
            true,
            true,
            false,
            false,
            false
        )
    }
}