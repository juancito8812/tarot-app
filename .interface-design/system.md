# Design System — Tarot App

## Brand Identity
- **Theme**: Místico, esotérico, elegante
- **Sensación**: Oscuro y sofisticado con acentos dorados
- **Tipografía base**: Serif para títulos, SansSerif para cuerpo

## Colors

### Palette
| Token | Hex | Usage |
|---|---|---|
| ClassicBurgundy | `#800020` | Primary, card bg (tiradas), TopBar/BottomBar bg |
| DarkBurgundy | `#4D0013` | Card back face, badge bg |
| ClassicGold | `#C9A84C` | Secondary, text accents, icons selected |
| LightGold | `#E8D48B` | Gold hover/variant |
| ClassicIvory | `#FFF8E7` | App background base |
| Parchment | `#F5E6C8` | Card container surface |
| DarkText | `#2C1810` | Body text |

### Gradients
| Token | Definition | Usage |
|---|---|---|
| BackgroundGradient | `#1A0005 → #3D0C11 → #1A0005` (vertical) | Background de todas las pantallas |

### Special
| Token | Hex/Alpha | Usage |
|---|---|---|
| CardBorderGold | `#D4AF37` | Card borders (1-2dp) |
| DividerGold | `#D4AF37 @ 40%` | Horizontal dividers decorativos |
| SubduedGold | `#C9A84C @ 60%` | Texto secundario |

## Typography

### TarotTypography
- **headlineLarge**: 28sp, Bold, Serif, ClassicBurgundy → Titles like "Carta del Día"
- **headlineMedium**: 22sp, SemiBold, Serif, ClassicBurgundy → Section headers
- **titleLarge**: 20sp, Medium, Serif, DarkText → Card names
- **bodyLarge**: 16sp, SansSerif, DarkText → Meanings, descriptions
- **bodyMedium**: 14sp, SansSerif, DarkText → Secondary info
- **labelLarge**: 14sp, Medium, SansSerif, ClassicGold → Labels, badges

## Cards

### TarotCardView (grid, DeckScreen)
- Shape: RoundedCornerShape(8dp)
- Border: 1dp, CardBorderGold @ 60%
- Container: Parchment
- Elevation: 6dp
- Image: ContentScale.Fit, 140dp height
- Name: bodyMedium, Serif, centered

### Daily Card (DailyScreen)
- Size: 200x280dp
- Card Back: DarkBurgundy bg, CardBorderGold border (2dp), inner border gold @ 50%
- Card Front: Parchment bg, CardBorderGold border (2dp), 8dp elevation
- Flip Animation: 600ms tween, FastOutSlowInEasing
- Glow: infiniteRepeatable pulse (0.3→0.7 alpha, 1000ms) on hint text

### Spread Card (SpreadsScreen)
- Shape: RoundedCornerShape(12dp)
- Border: 1dp, CardBorderGold @ 50%
- Container: ClassicBurgundy
- Title: titleLarge, Serif, ClassicGold
- Subtitle: bodyMedium, ClassicGold @ 70%

### Reading Card (SpreadLayout)
- Shape: RoundedCornerShape(8dp)
- Border: 1dp, CardBorderGold @ 40%
- Container: Parchment
- Position badge: CircleShape, ClassicBurgundy bg, ClassicGold text, 28dp
- Divider: DividerGold between position header and content

## Navigation

### TopAppBar
- Container: ClassicBurgundy
- Title: Serif, 22sp, ClassicGold
- Content: "Tarot"

### BottomNavigationBar
- Container: ClassicBurgundy
- Selected: ClassicGold (icon + text)
- Unselected: ClassicGold @ 50%
- Indicator: ClassicBurgundy @ 30%
- Labels: Serif

## Deck Dialog (CardDetailScreen)
- Container: Parchment
- Image: fit, 250dp, 1dp CardBorderGold border
- Title: headlineMedium, Serif, ClassicBurgundy
- Divider: DividerGold between sections
- Element label: labelLarge, ClassicGold
- Button: "Cerrar" ClassicGold, Serif

## Layout
- Background: BackgroundGradient on all screens
- Dividers: HorizontalDivider, 1dp, DividerGold for section separation
- Spacing: 16-24dp padding, 8-12dp between elements
