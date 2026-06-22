# Tarot App

Aplicación Android de Tarot con Lectura Diaria, Tiradas y Mazo completo. 100% offline, construida con Kotlin y Jetpack Compose.

## Características

- **Lectura Diaria** — carta del día con animación 3D flip y significado upright/reversed
- **Tiradas** — selector de tiradas (1, 3 o 10 cartas) con layout posicional y Celtic Cross
- **Mazo Completo** — grid de 78 cartas Rider-Waite-Smith con detalle en diálogo

## Stack técnico

| Capa | Tecnología |
|------|-----------|
| UI | Jetpack Compose + Material 3 |
| Navegación | Navigation Compose + BottomNav |
| DI | Hilt |
| Arquitectura | MVVM con UseCases |
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

## CI/CD

| Workflow | Trigger | Acción |
|----------|---------|--------|
| CI | push/PR a master | `testDebugUnitTest` + `lintDebug` |
| Build APK | push/PR/workflow_dispatch | `assembleDebug` + `assembleRelease` (tags v*) y upload artifact |

## Paleta de colores

| Color | Código |
|-------|--------|
| Burdeos (primary) | `#800020` |
| Dorado (secondary) | `#C9A84C` |
| Marfil (background) | `#FFF8E7` |

## Licencia

MIT
