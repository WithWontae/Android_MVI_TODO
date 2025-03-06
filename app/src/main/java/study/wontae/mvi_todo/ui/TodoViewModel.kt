package study.wontae.mvi_todo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import study.wontae.mvi_todo.intent.TodoIntent
import study.wontae.mvi_todo.model.TodoState

/**
 * Wish
 * Created by wtkim on 2025. 3. 6..
 * Copyright (c) 2025 ContextLogic. All rights reserved.
 *
 * TODO - A short description of the class
 */
class TodoViewModel : ViewModel() {
    private val _state = MutableStateFlow(TodoState()) // 초기 상태 설정
    val state: StateFlow<TodoState> get() = _state  // 상태 공개

    fun processIntent(intent: TodoIntent) {
        when (intent) {
            is TodoIntent.LoadTodos -> loadTodos()
            is TodoIntent.AddTodo -> addTodo(intent.todo)
            is TodoIntent.RemoveTodo -> removeTodo(intent.index)
        }
    }
    
    private fun loadTodos() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch { 
            // 간단한 초기 데이터 로드 
            _state.value = _state.value.copy(
                todos = listOf("운동하기", "책 읽기", "코딩"),
                isLoading = false
            )
        }
    }

    private fun addTodo(todo: String) {
        _state.value = _state.value.copy(
            todos = _state.value.todos + todo
        )
    }

    private fun removeTodo(index: Int) {
        _state.value = _state.value.copy(
            todos = _state.value.todos.toMutableList().apply { removeAt(index) }
        )
    }
}