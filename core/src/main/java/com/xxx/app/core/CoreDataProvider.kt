package com.xxx.app.core

/**
 * Core layer
 * - Không phụ thuộc UI
 * - Có thể dùng cho nhiều app
 */
object CoreDataProvider {

    fun getAppName(): String {
        return "Hello from Core Module 🚀"
    }

    fun getVersion(): String {
        return "Core v1.0.0"
    }
}
