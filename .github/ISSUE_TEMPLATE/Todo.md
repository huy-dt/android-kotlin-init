---

# 🧩 ISSUE CỤ THỂ – DÙNG NGAY

👉 Vào **GitHub → Issues → New Issue**
Chọn **Todo / Idea** rồi paste nội dung sau:

---

## 🏷️ Title

```
[INIT] Hoàn thiện Android Base Init App
```

## 📝 Description (Body)

```md
## Mục tiêu
Hoàn thiện repo base init app Android để dùng cho các project sau.

## Checklist
- [x] Init project Android (Compose, no Material3)
- [x] Version Catalog (libs.versions.toml)
- [x] Core module dùng chung
- [x] Signing config qua local.properties
- [x] Build debug / release OK
- [x] CMD workflow (không Android Studio)

## Cần làm thêm
- [ ] Chuẩn hóa README (usage + structure)
- [ ] Đặt repo làm Template
- [ ] Tạo develop branch
- [ ] Tag version v1.0.0
- [ ] Tạo issue template

## Ghi chú
- Repo dùng làm base cho nhiều app
- Ưu tiên đơn giản, dễ clone, dễ maintain
```

👉 **Issue này bạn có thể đóng sau khi hoàn tất** → rất đã 😄

---

# 🗂️ ISSUE TEMPLATE (MD) – ĐỂ DÙNG LÂU DÀI

Nếu bạn muốn **luôn tạo issue kiểu này**, dùng template sau:

📄 `.github/ISSUE_TEMPLATE/base-task.md`

```md
---
name: Base Task
about: Công việc / cải tiến cho base repo
title: "[TASK]"
labels: task
assignees: ''
---

## Mục tiêu
Mô tả ngắn gọn việc cần làm.

## Checklist
- [ ] Việc 1
- [ ] Việc 2
- [ ] Việc 3

## Ghi chú
- Link liên quan
- Ý tưởng thêm
```

---

# 🧠 CÁCH DÙNG ISSUE CHO DEV SOLO (CỰC HIỆU QUẢ)

| Khi nào      | Tạo issue        |
| ------------ | ---------------- |
| Ý tưởng mới  | `[IDEA] ...`     |
| Việc cần làm | `[TASK] ...`     |
| Bug          | `[BUG] ...`      |
| Refactor     | `[REFACTOR] ...` |

📌 **Không cần Jira, Trello, Notion**
👉 GitHub Issues là đủ.

---
