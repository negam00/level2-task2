package com.example.level2_task2

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private var questions = arrayListOf<Question>(
        Question("Albert Einstein was awarded the Nobel Prize in Physics.", true),
        Question("Baby koalas are called joeys.", true),
        Question("Brazil is the only country whose official language is Portuguese.", false),
        Question("No bird can fly backwards.", false),
        Question("Adults have more bones than babies do.", false)
    )
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
        swipeRv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        makeItemTouchHelper().attachToRecyclerView(swipeRv)
        questionAdapter.notifyDataSetChanged()

    }

    private fun makeItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                var answer = false

                if (direction == ItemTouchHelper.RIGHT) {
                    answer = true
                }

                if (questions[position].isCorrect == answer) {
                    Snackbar.make(
                        swipeRv,
                        "Rights answer!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                } else {
                    questionAdapter.notifyItemChanged(position)
                    Snackbar.make(swipeRv, "Wrong answer!", Snackbar.LENGTH_SHORT).show()
                }
            }

        }

        return ItemTouchHelper(callback)
    }


}
