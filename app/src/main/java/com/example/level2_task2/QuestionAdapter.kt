package com.example.level2_task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.question_item.view.*

class QuestionAdapter(private val questions: List<Question>) :
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

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

    inner class ViewHolder(private val questionView: View) : RecyclerView.ViewHolder(questionView) {
        fun bind(question: Question) {
            questionView.tvQuestion.text = question.question

            questionView.setOnClickListener {
                itemView.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "This statement is " + question.isCorrect.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }
}