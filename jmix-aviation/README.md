# Aviation — Jmix 2.x (ПР 9.1–9.3, вариант 4)

**Отдельный проект.** Не связан с `audio_transcriber` и другими репозиториями.

Корень приложения — эта папка (`jmix-aviation`). Откройте в IDE именно её, запускайте только из неё.

Проект практических работ по Jmix: модель данных, Generic UI и сервисы для варианта 4 (Airplane, Airport, Flight).

## Требования

- JDK 17 или 21
- Gradle 8.x (wrapper включён)

## Как вынести на свой компьютер

1. Скопируйте папку `jmix-aviation` куда удобно, например `C:\Projects\jmix-aviation` или `~/jmix-aviation`.
2. В IntelliJ IDEA: **File → Open** → выберите эту папку (не родительский репозиторий).
3. Или создайте отдельный Git-репозиторий только для Jmix:

```bash
cd jmix-aviation
git init
git add .
git commit -m "Jmix PR 9.1-9.3 variant 4"
```

## Запуск

```bash
cd jmix-aviation   # или cd C:\Projects\jmix-aviation
./gradlew bootRun
```

Приложение: http://localhost:8080  
Логин: `admin` / `admin`

На экране входа можно выбрать язык **EN** или **RU**.

## Структура

| Компонент | Пакет / путь |
|-----------|----------------|
| Сущности | `com.company.aviation.entity` |
| Сервис | `com.company.aviation.service.AviationService` |
| Экраны | `com.company.aviation.view.*` |
| Меню | `com/company/aviation/menu.xml` |
| Локализация | `messages.properties`, `messages_ru.properties` |

## Вариант 4

- **Airplane** — ManyToOne → Airport, ManyToMany → Flight (ведущая сторона, `AIRPLANE_FLIGHT_LINK`)
- **Airport** — OneToMany → Flight, Airplane
- **Flight** — ManyToOne → Airport, ManyToMany → Airplane (ведомая сторона)

На карточке самолёта в списке рейсов доступны только рейсы того же аэропорта (`Flight.airport.id = Airplane.airport.id`).

На списке самолётов четыре кнопки вызывают методы `AviationService` и показывают результат через `Notifications`.
