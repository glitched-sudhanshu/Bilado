# Bilado Design System

## Source of truth

`design-tokens.json` is the implementation contract for Bilado's four visual themes:

- Minimalistic Light
- Minimalistic Dark
- Glassy Light
- Glassy Dark

Figma is a visual reference. Android code must use the semantic and component tokens defined in the JSON contract, not copied HEX values or Figma canvas coordinates.

## Token usage

The contract has three layers:

- Primitives: raw colors and numeric values.
- Semantic tokens: roles such as `text.primary`, `action.primary`, and `status.overdue`.
- Component tokens: reusable metrics such as `radiiDp.card` and `componentDp.buttonHeight`.

Feature code should consume semantic or component tokens. Do not scatter raw `Color`, `dp`, or `sp` values across screens. Intrinsic asset measurements are acceptable when documented locally.

The four themes share the same semantic roles. Minimalistic themes use solid surfaces; glassy themes use gradients and effects. Switching themes must change the complete visual recipe, not only the primary color.

## Visual rules

- Use Plus Jakarta Sans with a platform sans-serif fallback.
- Use tabular numerals for money, counts, and dates.
- Keep glass effects for high-value hierarchy such as the dashboard hero, key metrics, subscription hero, primary CTA, and navigation.
- Keep forms and dense financial lists readable; do not blur every surface.
- Use the material roles `cta`, `hero`, `surface`, `accent`, and `border` consistently.
- Preserve the gradient stop positions and transform values from the token contract.

## Layout and interaction

- Base grid: 4dp.
- Common spacing: 8, 12, 16, and 24dp.
- Screen padding: 24dp on the compact-phone layouts.
- Minimum touch target: 48dp.
- Validate at 360 × 800dp and 412 × 915dp.
- Support font scaling without clipping, overlap, or truncated money values.
- Respect reduced-motion settings.

## States and accessibility

Controls need default, pressed, focused, disabled, and loading states. Product flows need explicit empty, loading, offline, validation, permission, server-error, and subscription states.

Payment states include paid, pending, overdue, partial, failed, and refunded. Status must use text and, where useful, an icon in addition to color.

Empty states explain the situation and provide a next action. Form errors preserve entered values and appear inline; a toast alone is insufficient.

Meet WCAG AA contrast, provide content descriptions for meaningful icons, and test long company, cycle, and student names.

## Android mapping

Expose the contract through typed Compose theme values such as `BiladoColors`, `BiladoTypography`, `BiladoDimensions`, `BiladoEffects`, and `BiladoMotion`. Composables should read these values through one theme entry point.
