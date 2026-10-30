# Bilado Figma Screen Inventory

## Purpose

This document tracks design coverage for the MVP. It is not a runtime specification.

- `docs/product/user-flows.md` defines product behavior.
- `docs/product/design-system.md` defines UI rules.
- `docs/product/design-tokens.json` defines visual values.
- Figma provides the detailed visual reference.

Design file: [Bilado — Aurora Design System](https://www.figma.com/design/umg2yTKhy1vm0XrRMc0IbG/Bilado-%E2%80%94-Aurora-Design-System)

The four supported visual themes are Minimalistic Light, Minimalistic Dark, Glassy Light, and Glassy Dark. They should cover the same product flows.

## Orientation coverage

Bilado must support both portrait and landscape layouts on Android. Landscape coverage is pending for the complete MVP screen inventory and all four visual themes.

Pending work:

- Create landscape variants for every required screen and critical state.
- Review responsive behavior at landscape phone dimensions.
- Confirm navigation, forms, tables, payment summaries, and actions remain usable without clipping or overlap.
- Implement and test the landscape layouts in Android.

Portrait designs must not be treated as complete development handoff until landscape coverage is reviewed.

## Screen inventory

### Account and company access

- Login
- Create a company
- Request access to an existing company
- Pending, approved, and rejected access request
- Forgot password
- Invalid credentials

### Subscription

- Subscription overview
- Coupon applied and coupon invalid or expired
- Payment confirmation, pending, and failed
- Active subscription
- Expired subscription with access blocked

### Home and collection cycles

- Home dashboard with company-wide totals
- Empty dashboard
- Create cycle and validation errors
- Cycle list
- Cycle detail
- Add or edit a student
- Edit fee after a payment exists
- Overdue and partial-payment states

### Payment requests and delivery

- Student payment-request row
- Send-options sheet
- Email delivery confirmation and failure
- Android Share Sheet handoff
- Resend one request
- Send eligible requests from a cycle
- Already-paid student prevention

### Student payment

- Hosted payment page
- Outstanding partial balance
- Applicable fine
- Payment pending, successful, and failed
- Already-settled payment
- Expired payment link

### Receipts and reports

- Student payment history
- Partial-payment receipt
- Final receipt
- CSV export confirmation
- Empty report state

## Required user paths

### Core collection path

Login → active subscription → dashboard → create cycle → add students → generate payment requests → share or email request → student payment → updated dashboard → receipt.

### Partial-payment path

Cycle detail → partial payment → updated outstanding balance → second payment → paid status.

### Access approval path

Request access → pending state → admin review → role assignment → approved user enters the company.

### Subscription-expiry path

Active product → subscription expires → blocked-access screen → renewal or coupon → product access restored.

## Design review requirements

- A tutor can understand expected, collected, and outstanding amounts quickly.
- Every empty state has an explanation and a next action.
- Paid, pending, overdue, partial, failed, and refunded states are distinguishable without color alone.
- Sending one request cannot accidentally send to the entire cycle.
- Missing email addresses have a clear manual-sharing path.
- Loading, offline, validation, server-error, and expired-subscription states are designed.
- Layout works at 360 × 800dp and 412 × 915dp with large text enabled.
