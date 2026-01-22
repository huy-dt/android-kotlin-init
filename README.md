````md
# Android Kotlin Init (CLI Friendly)

Base Android project dùng Kotlin + Jetpack Compose, được thiết kế để:
- Build hoàn toàn bằng **CMD / Terminal**
- Không phụ thuộc Android Studio
- Dễ tái sử dụng làm **project init / template**
- Cấu trúc dependency rõ ràng bằng **Version Catalog (libs.versions.toml)**

---

## ✨ Tech Stack

- **Kotlin** 1.9.x
- **Gradle (AGP 8.x)** + Version Catalog
- **Jetpack Compose** (không Material mặc định)
- **Multi-module**: `app` + `core`
- **Java 17**
- **Build bằng Gradle CLI**

---

## 📦 Module Structure

```text
root
├── app/        # Android Application (Compose UI)
├── core/       # Core logic dùng chung (không UI)
├── gradle/
├── gradlew
├── libs.versions.toml
└── README.md
````

---

## 🔗 Dependency Management

Project sử dụng **Version Catalog** (`libs.versions.toml`)
Dependency được chia theo **group rõ ràng** như sau:

---

### 1️⃣ Core Android

Dùng cho toàn bộ app & module:

```toml
androidx-core-ktx
androidx-lifecycle-runtime-ktx
androidx-lifecycle-viewmodel-ktx
```

Bundle:

```toml
bundles.core
```

---

### 2️⃣ Coroutines

Xử lý bất đồng bộ / background:

```toml
kotlinx-coroutines-core
kotlinx-coroutines-android
```

Bundle:

```toml
bundles.coroutines
```

---

### 3️⃣ Compose Core (KHÔNG Material)

Các thành phần Compose cơ bản, **không phụ thuộc Material Design**:

```toml
androidx-compose-runtime
androidx-compose-ui
androidx-compose-foundation
androidx-activity-compose
```

Bundle:

```toml
bundles.compose.core
```

> Bao gồm: `Box`, `Column`, `Row`, `Modifier`, `BasicText`, layout, gesture…

---

### 4️⃣ Compose Preview & Debug

Dùng cho preview & tooling:

```toml
androidx-compose-ui-tooling-preview   # @Preview
androidx-compose-ui-tooling           # Layout Inspector (debug)
```

Bundles:

```toml
bundles.compose.preview
bundles.compose.debug
```

---

### 5️⃣ Compose Optional (KHÔNG dùng mặc định)

#### Material 3 (tuỳ app)

```toml
androidx-compose-material3
```

Bundle:

```toml
bundles.compose.material3
```

#### Navigation Compose

```toml
androidx-navigation-compose
```

Bundle:

```toml
bundles.compose.navigation
```

---

### 6️⃣ Dependency Injection (Optional)

Dùng khi cần DI:

```toml
hilt-android
hilt-compiler
androidx-hilt-navigation-compose
```

Bundle:

```toml
bundles.hilt
```

---

### 7️⃣ Database (Optional)

Room database:

```toml
androidx-room-runtime
androidx-room-ktx
androidx-room-compiler
```

Bundle:

```toml
bundles.room
```

---

### 8️⃣ XML / Legacy (Optional)

Chỉ dùng khi cần View/XML cũ:

```toml
androidx-appcompat
```

---

## 🛠 Build & Run (CLI)

### Debug build

```bash
./gradlew assembleDebug
```

### Release build

> Cần cấu hình signing

```bash
./gradlew assembleRelease
```

---

## 🔐 Signing (Local – KHÔNG COMMIT)

Tạo file `local.properties` (đã được `.gitignore`):

```properties
RELEASE_STORE_FILE=your_keystore.jks
RELEASE_STORE_PASSWORD=*****
RELEASE_KEY_ALIAS=*****
RELEASE_KEY_PASSWORD=*****
```

⚠️ **Không commit**:

* `local.properties`
* `*.jks`

---

## ⚙️ Gradle Optimization (CLI Friendly)

Project đã bật:

* Configuration Cache
* Parallel build
* Non-transitive R
* AndroidX

Phù hợp cho:

* CMD / Terminal
* CI/CD
* Máy không dùng Android Studio

---

## 🎯 Mục tiêu của template

* Làm base init cho mọi Android project
* Hiểu rõ từng dependency đang dùng
* Không overkill Material / DI khi chưa cần
* Dễ mở rộng khi app lớn dần

---

## 📌 Ghi chú

* `core` **không phụ thuộc UI**
* `app` chỉ consume từ `core`
* Có thể tách thêm `domain`, `data` khi cần

---

## 🧩 License

MIT (hoặc tuỳ bạn)

```

---

## ✅ KẾT LUẬN

README này:
- ✔ Phản ánh **đúng `libs.versions.toml`**
- ✔ Giải thích **rõ dependency theo group**
- ✔ Phù hợp **repo template / base init**
- ✔ Người khác clone là hiểu ngay

Nếu bạn muốn bước tiếp:
- tạo **GitHub Template Repo**
- thêm **badge build**
- hoặc viết **CONTRIBUTING.md**

👉 cứ nói, mình làm tiếp cho bạn.
```
