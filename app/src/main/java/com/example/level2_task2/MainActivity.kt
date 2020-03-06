package com.example.level2_task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private var answer = false
    private var questions = arrayListOf<Question>()
    private var questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        // Set the recycleview layout manager and adapter
        swipeRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        swipeRv.adapter = questionAdapter

        // Add all questions and belonging answers
        for (q in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[q], Question.IS_CORRECT[q]))
        }

        questionAdapter.notifyDataSetChanged()

    }

    private fun makeItemTouchHelper(): ItemTouchHelper {

        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.RIGHT) {
                    answer = true
                }

                if (questions[position].isCorrect == answer) {
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                } else {
                    Snackbar.make(swipeRv, "Wrong answer!", Snackbar.LENGTH_SHORT).show()
                }
            }

        }

        return ItemTouchHelper(callback)
    }


}
