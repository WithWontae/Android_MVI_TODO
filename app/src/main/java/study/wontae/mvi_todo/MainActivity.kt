package study.wontae.mvi_todo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import study.wontae.mvi_todo.databinding.ActivityMainBinding
import study.wontae.mvi_todo.intent.TodoIntent
import study.wontae.mvi_todo.ui.TodoViewModel


/**
 * Wish
 * Created by wtkim on 2025. 3. 6..
 * Copyright (c) 2025 ContextLogic. All rights reserved.
 *
 *
 * TODO - A short description of the class
 */
class MainActivity : AppCompatActivity() {
    private val viewModel: TodoViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding   // ViewBinding 설정

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // View 요소 접근
        val todoInput = binding.todoInput
        val addButton = binding.addButton
        val todoList = binding.todoList

        // 초기 할일 목록 불러오기
        viewModel.processIntent(TodoIntent.LoadTodos)

        // State 관찰하여 UI 업데이트
        lifecycleScope.launch {
            viewModel.state.collect() { state ->
                todoList.removeAllViews()
                state.todos.forEachIndexed { index, todo ->
                    val textView = TextView(this@MainActivity).apply {
                        text = todo
                        setOnClickListener {
                            viewModel.processIntent(TodoIntent.RemoveTodo(index))
                        }
                    }
                    todoList.addView(textView)
                }
            }
        }

        addButton.setOnClickListener {
            val todo = todoInput.text.toString()
            if (todo.isNotBlank()) {
                viewModel.processIntent(TodoIntent.AddTodo(todo))
                todoInput.text.clear()
            }
        }

    }
}
