package study.wontae.mvi_todo.intent

/**
 * Wish
 * Created by wtkim on 2025. 3. 6..
 * Copyright (c) 2025 ContextLogic. All rights reserved.
 *
 * TODO - A short description of the class
 */
sealed class TodoIntent {
    object LoadTodos : TodoIntent()    // 할일 목록 불러 오기
    data class AddTodo(val todo: String) : TodoIntent()   // 새로운 할일 추가
    data class RemoveTodo(val index: Int) : TodoIntent()    // 특정 할일 삭제
}