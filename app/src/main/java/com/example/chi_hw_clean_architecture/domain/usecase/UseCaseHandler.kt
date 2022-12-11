package com.example.chi_hw_clean_architecture.domain.usecase


class UseCaseHandler {


    companion object {
        private var INSTANCE: UseCaseHandler? = null
            fun getInstance(): UseCaseHandler {
                if (INSTANCE == null) {
                    INSTANCE = UseCaseHandler()
                }
                return INSTANCE!!
        }
    }
}