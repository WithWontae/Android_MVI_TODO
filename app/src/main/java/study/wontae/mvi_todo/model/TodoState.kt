package study.wontae.mvi_todo.model

/**
 * Wish
 * Created by wtkim on 2025. 3. 6..
 * Copyright (c) 2025 ContextLogic. All rights reserved.
 *
 * TODO - A short description of the class
 */
data class TodoState(
    val todos: List<String> = emptyList(), // 할일 목록
    val isLoading: Boolean = false,  // 로딩 상태
    val error: String? = null   //에러 메시지
)
