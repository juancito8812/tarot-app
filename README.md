# ✦ Tarot App

Aplicación Android de Tarot con Lectura Diaria, Tiradas y Mazo completo. 100% offline, construida con Kotlin y Jetpack Compose. Incluye las 78 cartas Rider-Waite-Smith con imágenes originales del dominio público.

## ✨ Características

- **Lectura Diaria** — carta aleatoria con animación 3D flip y efecto glow pulsante
- **Tiradas** — 3 tipos (1, 3 o 10 cartas) con layout Celtic Cross y badges de posición numerados
- **Mazo Completo** — grid de 78 cartas con imágenes Rider-Waite-Smith y detalle en diálogo
- **Offline total** — sin conexión a internet, todos los datos embebidos
- **UI mística** — fondo gradiente oscuro, bordes dorados, tipografía serif, paleta burdeos/dorado

## 🎨 Diseño visual

Sistema de diseño completo en `.interface-design/system.md`.

### Paleta

```
▌ClassicBurgundy  ████████  #800020  — Barras, primary
▌DarkBurgundy     ████████  #4D0013  — Dorso cartas, badges
▌ClassicGold      ████████  #C9A84C  — Acentos, secondary
▌Parchment        ████████  #F5E6C8  — Superficie cartas
▌CardBorderGold   ████████  #D4AF37  — Bordes dorados
```

Fondo gradiente vertical: `#1A0005 ➔ #3D0C11 ➔ #1A0005` en todas las pantallas.

### Pantallas

```
┌──────────────────────────────────────┐
│  Tarot                         ✦ Diaria  │  ← TopAppBar burdeos/dorado
├──────────────────────────────────────┤
│                                      │
│        ✦ Carta del Día ✦           │  ← título serif dorado
│        ───────────────              │  ← divisor dorado
│                                      │
│       ┌────────────────┐            │
│       │ ░░░░░░░░░░░░░  │            │  ← dorso DarkBurgundy
│       │ ░     ✦     ░  │            │     borde dorado doble
│       │ ░░░░░░░░░░░░░  │            │
│       └────────────────┘            │
│                                      │
│    Toca la carta para revelar...    │  ← texto con glow pulsante
│                                      │
├──────────────────────────────────────┤
│  ✦ Diaria    Tiradas    Mazo       │  ← BottomNav burdeos/dorado
└──────────────────────────────────────┘

┌──────────────────────────────────────┐
│  ✦ Elige una Tirada ✦              │  ← SpreadsScreen
│  ───────────────                     │
│                                      │
│  ┌──────────────────────────────┐   │
│  │ Una Carta                    │   │  ← spread card borde dorado
│  │ 1 carta — Mensaje directo    │   │     fondo ClassicBurgundy
│  └──────────────────────────────┘   │
│                                      │
│  ┌──────────────────────────────┐   │
│  │ Tres Cartas                  │   │
│  │ 3 cartas — Pasado, presente… │   │
│  └──────────────────────────────┘   │
│                                      │
│  ┌──────────────────────────────┐   │
│  │ Cruz Celta                   │   │
│  │ 10 cartas — Situación…       │   │
│  └──────────────────────────────┘   │
└──────────────────────────────────────┘

┌──────────────────────────────────────┐
│                                      │
│  ┌──────┐ ┌──────┐ ┌──────┐        │
│  │ RWS  │ │ RWS  │ │ RWS  │        │  ← Mazo: grid 3 columnas
│  │ img  │ │ img  │ │ img  │        │     cartas con borde dorado
│  │ElLoco│ │Mago  │ │Sacer│        │     fondo Parchment
│  └──────┘ └──────┘ └──────┘        │
│                                      │
│  ┌──────┐ ┌──────┐ ┌──────┐        │
│  │ RWS  │ │ RWS  │ │ RWS  │        │
│  │ img  │ │ img  │ │ img  │        │
│  │Empera│ │Emper │ │Sumo  │        │
│  └──────┘ └──────┘ └──────┘        │
└──────────────────────────────────────┘
```

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

APK en `app/build/outputs/apk/debug/app-debug.apk`

## Tests

```bash
./gradlew :app:testDebugUnitTest
```

17 tests unitarios: parseo JSON, repositorio, casos de uso (1/3/10 cartas + errores), ViewModels.

## CI/CD

| Workflow | Trigger | Acción |
|----------|---------|--------|
| CI | push/PR a master | `testDebugUnitTest` + `lintDebug` |
| Build APK | push/PR + workflow_dispatch | `assembleDebug` + `assembleRelease` (tags v*) + upload artifact |

## Cartas Rider-Waite-Smith

Las 78 imágenes provienen del deck Rider-Waite-Smith en dominio público ([yunru.se/tarot](https://yunru.se/tarot/)). Cada carta muestra imagen original, nombre español, significado upright/invertido, keywords y elemento.

## Estructura

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
