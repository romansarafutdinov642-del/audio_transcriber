# Aviation — Jmix 2.x (ПР 9.1–9.3, вариант 4)

Проект практических работ по Jmix: модель данных, Generic UI и сервисы для варианта 4 (Airplane, Airport, Flight).

## Требования

- JDK 17 или 21
- Gradle 8.x (wrapper включён)

## Запуск

```bash
cd jmix-aviation
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
