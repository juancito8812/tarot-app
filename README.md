# ✦ Tarot App

Aplicación Android de Tarot con Lectura Diaria, Tiradas y Mazo completo. 100% offline, construida con Kotlin y Jetpack Compose. Incluye las 78 cartas Rider-Waite-Smith con imágenes originales del dominio público.

## ✨ Características

- **Lectura Diaria** — carta aleatoria con animación 3D flip y efecto glow pulsante
- **Tiradas** — 3 tipos de tirada (1, 3 o 10 cartas) con layout Celtic Cross y badges de posición
- **Mazo Completo** — grid de 78 cartas con imágenes Rider-Waite-Smith y detalle en diálogo
- **Offline total** — sin conexión a internet, todos los datos embebidos
- **UI mística** — fondo gradiente oscuro, bordes dorados, tipografía serif, paleta burdeos/dorado

## Stack técnico

| Capa | Tecnología |
|------|-----------|
| UI | Jetpack Compose + Material 3 |
| Navegación | Navigation Compose + BottomNavigationBar |
| DI | Hilt (kapt) |
| Arquitectura | MVVM con UseCases |
| Imágenes | Drawables JPG embebidos (78 cartas RWS) |
| Datos | JSON en `res/raw/` parseado con `org.json` |
| Tests | JUnit 5 + MockK + kotlinx-coroutines-test |
| CI/CD | GitHub Actions |
| Min SDK / Target | 26 / 35 |

## Requisitos

- Android Studio Ladybug (2024.2+) o Hedgehog
- JDK 17
- Gradle 8.11.1

## Build

```bash
./gradlew :app:assembleDebug
```

APK generado en: `app/build/outputs/apk/debug/app-debug.apk`

## Tests

```bash
./gradlew :app:testDebugUnitTest
```

17 tests (unitarios, sin emulador):

- Parseo de JSON de cartas
- Consultas del repositorio
- Casos de uso (tiradas 1/3/10, manejo de errores)
- ViewModels (carga de mazo, carta diaria, selector de tirada)

## CI/CD

| Workflow | Trigger | Acción |
|----------|---------|--------|
| CI | push/PR a master | `testDebugUnitTest` + `lintDebug` |
| Build APK | push/PR + workflow_dispatch | `assembleDebug` + `assembleRelease` (tags v*) y upload artifact |

## Diseño visual

Sistema de diseño documentado en `.interface-design/system.md`.

### Paleta de colores

| Color | Código | Uso |
|-------|--------|-----|
| ClassicBurgundy | `#800020` | Primary, barras de navegación |
| DarkBurgundy | `#4D0013` | Dorso de cartas, badges |
| ClassicGold | `#C9A84C` | Secondary, acentos dorados |
| Parchment | `#F5E6C8` | Superficie de cartas |
| CardBorderGold | `#D4AF37` | Bordes dorados |

### Gradiente de fondo

`#1A0005 → #3D0C11 → #1A0005` (vertical) en todas las pantallas.

## Cartas Rider-Waite-Smith

Las 78 imágenes provienen del deck Rider-Waite-Smith en dominio público, obtenidas de [yunru.se/tarot](https://yunru.se/tarot/). Cada carta se muestra con:

- Imagen original del arcano
- Nombre en español
- Significado upright (derecha) e invertido
- Palabras clave y elemento asociado

## Estructura del proyecto

```
com.tarot.app/
├── data/
│   ├── model/        → TarotCard, Spread, Reading
│   └── repository/   → TarotRepository
├── di/               → AppModule (Hilt)
├── domain/usecase/   → GetDailyCard, DrawSpread, GetDeck
└── ui/
    ├── components/   → TarotCardView, SpreadLayout, CardBack
    ├── daily/        → DailyScreen + ViewModel
    ├── deck/         → DeckScreen + ViewModel + CardDetailScreen
    ├── navigation/   → AppNavigation (BottomNav)
    ├── spreads/      → SpreadsScreen + ViewModel
    └── theme/        → Color, Theme, Type
```

## Licencia

MIT

---

Desarrollada por [juancito8812](https://github.com/juancito8812)
