package com.example.level2_task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.question_item.view.*

class QuestionAdapter(val questions: List<Question>): RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        )
    }

    /**
     * Returns the total amount of questions
     */
    override fun getItemCount(): Int {
        return questions.size
     }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    inner class ViewHolder(val questionView: View):RecyclerView.ViewHolder(questionView)  {
        fun bind(question: Question){
            questionView.tvQuestion.text = question.question
        }
    }
}