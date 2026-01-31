Câu này hỏi **đúng lúc luôn** 👍
Bạn đã đi tới đoạn *“core nên có gì để xài lâu mà không phình”* rồi đó.

Mình trả lời theo **chuẩn Clean Architecture + thực chiến Android/Kotlin**, không phải lý thuyết sách vở.

---

## 🎯 Mục tiêu của `core`

> **Chứa business rules thuần Kotlin**
> **Không phụ thuộc Android / Room / Retrofit**

Nếu mai bạn viết backend bằng Kotlin → **core vẫn dùng lại được**

---

## 🧱 Cấu trúc `core` CHUẨN & THƯỜNG DÙNG

```text
core/
└── domain/
    ├── model/
    ├── repository/
    ├── usecase/
    ├── exception/
    ├── valueobject/
    ├── result/
    ├── validator/
    └── util/
```

Giờ mình đi từng cái, cái nào **nên có / khi nào có**.

---

## 1️⃣ `domain/model`

Bạn biết rồi 👍

```kotlin
data class User(
    val id: Long,
    val username: String,
    val role: Role
)
```

Rule:

* Không annotation
* Không framework
* Không mutable lung tung

---

## 2️⃣ `domain/repository`

Interface mô tả **business cần gì**, không quan tâm lấy từ đâu

```kotlin
interface AuthRepository {
    suspend fun login(username: String, password: String): User
}
```

---

## 3️⃣ `domain/usecase`

1 hành động = 1 use case

```kotlin
class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(...) = repository.login(...)
}
```

---

## 4️⃣ ⭐ `domain/valueobject` (rất đáng có)

Dùng khi:

* Field có rule riêng
* Validation lặp lại

```kotlin
@JvmInline
value class Email(val value: String) {
    init {
        require(value.contains("@"))
    }
}
```

🔥 Giảm bug cực mạnh

---

## 5️⃣ `domain/exception`

Business error, **không phải HTTP / SQL**

```kotlin
sealed class AuthException : RuntimeException() {
    object InvalidCredential : AuthException()
    object UserBlocked : AuthException()
}
```

---

## 6️⃣ `domain/result` hoặc `domain/state`

Chuẩn hóa output use case

```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val e: Throwable) : Result<Nothing>()
}
```

(hoặc dùng `Either`, `Outcome`, tuỳ team)

---

## 7️⃣ `domain/validator`

Rule kiểm tra nghiệp vụ

```kotlin
class PasswordValidator {
    fun validate(password: String) {
        if (password.length < 8) throw WeakPassword
    }
}
```

👉 Tách khỏi UI & data

---

## 8️⃣ `domain/util`

⚠️ Cái này dễ bị lạm dụng

Chỉ để:

* Helper **thuần business**
* Không date/time Android
* Không string resource

Ví dụ:

```kotlin
fun calculateDiscount(price: Money): Money
```

---

## 9️⃣ Những thứ KHÔNG nên có trong `core`

❌ `Entity` (Room)
❌ `Dto` (API)
❌ `Mapper`
❌ `ViewModel`
❌ `Context`
❌ `CoroutineDispatcher` (Android)

---

## 10️⃣ Một core “đẹp” trong dự án thật

```text
core/
└── domain/
    ├── model/
    │   ├── User.kt
    │   └── Role.kt
    ├── repository/
    │   └── AuthRepository.kt
    ├── usecase/
    │   └── LoginUseCase.kt
    ├── valueobject/
    │   └── Email.kt
    ├── exception/
    │   └── AuthException.kt
    └── result/
        └── Result.kt
```

---

## 11️⃣ Rule chốt cho bạn (xài lâu không sai)

> 🔥 Core = **business, không phải technical**
> 🔥 Cái gì đổi theo framework → đừng cho vào core
> 🔥 Core càng boring → app càng dễ scale

---
