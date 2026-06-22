# Handoff — Tarot App

## Estado actual

App funcional con las 3 pantallas principales compilando y pasando todos los tests en CI/CD.

## Arquitectura

```
com.tarot.app/
├── data/
│   ├── model/        → TarotCard, Spread, Reading
│   └── repository/   → TarotRepository (carga JSON de raw/)
├── di/               → AppModule (Hilt, provee repo + use cases)
├── domain/usecase/   → GetDailyCardUseCase, DrawSpreadUseCase, GetDeckUseCase
└── ui/
    ├── components/   → TarotCardView, SpreadLayout, CardBack
    ├── daily/        → DailyScreen + DailyViewModel
    ├── deck/         → DeckScreen + DeckViewModel + CardDetailScreen
    ├── navigation/   → AppNavigation (BottomNav) + Screen
    ├── spreads/      → SpreadsScreen + SpreadViewModel
    └── theme/        → Color, Theme, Type
```

- MVVM: cada Screen tiene su ViewModel con Hilt injection
- Navigation Compose con BottomNavigationBar (3 tabs: Daily, Spreads, Deck)
- Datos: JSON en `res/raw/tarot_cards.json` parseado con `org.json.JSONObject`
- Sin Room, sin Retrofit, sin dependencias de red

## Decisiones clave

| Decisión | Razón |
|----------|-------|
| compileSdk = 36 | SDK 35 no disponible en entorno local |
| Hilt 2.54 (kapt) | KSP incompatible con Windows local |
| image loading via `resources.getIdentifier()` | Evita reflection, compatible con ProGuard |
| Datos offline en JSON embebido | App 100% offline, sin API |
| Kotlin nativo + Compose | App solo Android, mejor rendimiento que RN |
| JUnit 5 + MockK | Tests modernos sin depende de AndroidJUnit |

## Tests (14 tests, todos passing)

- `TarotCardTest` — parseo JSON (4)
- `TarotRepositoryTest` — carga + consultas (3)
- `DrawSpreadUseCaseTest` — tiradas 1/3/10 + error (4)
- `DeckViewModelTest` — carga de mazo (2)
- `DailyViewModelTest` — carta diaria (2)
- `SpreadViewModelTest` — selector de tirada (2)

Para ejecutar: `./gradlew :app:testDebugUnitTest`

## CI/CD

- `.github/workflows/ci.yml`: corre tests + lint en cada push/PR
- `.github/workflows/build-apk.yml`: build debug en push/PR, build release en tags v*, upload artifact
- Ambos workflows requieren `git update-index --chmod=+x gradlew` (el gradlew debe tener permisos de ejecución)
- Warning conocido: Node 20 deprecation — los actions usan Node 24 forzado

## Lo que funciona

- [x] Pantalla Daily con animación flip 3D y carta aleatoria
- [x] Pantalla Spreads con selector (1/3/10) + layout Celtic Cross
- [x] Pantalla Mazo con grid 3 columnas + detalle en diálogo
- [x] 78 cartas Rider-Waite-Smith con significados upright/reversed
- [x] Tema clásico (burdeos/dorado/marfil)
- [x] Bottom Navigation con 3 tabs
- [x] Build APK exitoso (debug)
- [x] CI/CD con GitHub Actions

## Lo que NO funciona / pendiente

- [ ] Imágenes de cartas — no hay drawables empaquetados (las cartas se muestran sin imagen)
- [ ] Animación de volteo en Spreads (solo en Daily)
- [ ] Pantalla de configuración / ajustes
- [ ] Dark mode
- [ ] Tests de UI (ComposeTestRule)
- [ ] Publicación en Play Store
- [ ] No hay pantalla de onboarding

## Cómo compilar

```bash
./gradlew :app:assembleDebug    # APK debug
./gradlew :app:assembleRelease  # APK release (requiere keystore)
```

## Repositorio

`https://github.com/juancito8812/tarot-app`
Branch: `master`
