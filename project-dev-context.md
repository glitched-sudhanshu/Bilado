# Bilado: Android-First Payment Collection Product

## Document purpose

This document is the durable product context for Bilado. It is intended to keep future engineering work and AI-assisted development aligned with the product direction.

Detailed database schemas, API contracts, webhook processing, authentication implementation, authorization, privacy, and security design belong in the engineering documentation created during implementation. This document defines the product intent and boundaries those designs must serve.

## Current project state

- The workspace started empty.
- Android code has not been created yet.
- Backend code has not been created yet.
- No payment provider has been selected.
- No Figma design system or user-flow designs have been created.
- The provisional Android application ID is `com.sudhanshuranjan.bilado`.
- The application ID must be reviewed and finalized before the first Google Play upload.

The planned repository structure is:

```text
android/
backend/
docs/
infra/
```

## Product definition

Bilado is an Android-first payment collection product. Its first validated workflow is tuition-fee collection for tutors managing approximately 20–200 students.

The product should eventually support other recurring-payment use cases, but the first release must not become a generic billing platform before the tutor workflow is complete and understood.

Bilado helps tutors:

- Add and manage students.
- Define recurring fees and due dates.
- Create payment requests.
- Share supported payment links or QR codes.
- Send payment reminders.
- Track paid, pending, overdue, partial, failed, and refunded payments.
- Review manually submitted payment references where automatic reconciliation is unavailable.
- Issue digital receipts.
- Export collection reports.

The tutor subscription and student fee collection are separate product concepts:

- The tutor pays Bilado for access to the product.
- The student or parent pays the tutor.

The initial commercial model is a paid tutor plan with a coupon-based free trial. Exact pricing, billing provider, and fee structure remain open until payment-provider and product feasibility work is completed.

The account model is company-based:

- A company owns its users, cycles, students, payments, reports, and subscription.
- The first company user is created as `master_admin`.
- `master_admin` or an approved `admin` can approve access requests and assign the final role.
- A requester cannot grant themselves administrative access.
- A company subscription controls access for all approved company users.
- One fee obligation supports multiple partial payments before becoming fully paid.

## Initial release scope

The first release focuses on:

- Tutor onboarding and account access.
- Student management.
- Monthly fee tracking and due dates.
- Payment requests through one supported payment provider.
- Manual payment-reference submission where automatic reconciliation is unavailable.
- Payment reminders.
- Collection-status dashboard.
- Digital receipts.
- CSV reports.
- Tutor subscription access with coupon-based trial.

The initial payment integration must not assume that an arbitrary payment identifier provides reliable webhooks or reconciliation.

## Explicitly out of scope for the first release

- Rent management.
- Inventory.
- Marketplace features.
- Lending or wallet functionality.
- Full accounting software.
- AI features.
- Multiple payment providers.
- Phone OTP authentication.
- WhatsApp API integration.
- Multi-country payment support as a first-class feature.

The product may later expand to rent collection, subscriptions, freelancers, and other small businesses only after the first workflow is implemented and evaluated.

## Product strategy and risks

Bilado needs a workflow advantage, not merely another payment dashboard. Existing competitors mean that copying a generic collection feature set is unlikely to be enough.

The main risks are:

- Tutors may not change their existing WhatsApp, notebook, spreadsheet, or memory-based workflow.
- Payment-provider onboarding and reconciliation may be harder than expected.
- A subscription paywall may reduce adoption before the core workflow proves useful.
- Manual payment verification may create operational work for tutors.
- WhatsApp API access may be expensive, restricted, or operationally unsuitable.
- Payment failures, refunds, partial payments, and delayed provider events may create confusing statuses.

The product positioning is intentionally broader than one geography or one future use case. However, the first payment provider and compliance scope will still determine the practical launch market.

## Product validation

Bilado is both:

1. A learning and portfolio project for building a production-shaped Android and Go product.
2. A potentially usable product for tutors.

The project does not depend on achieving traction to justify implementation. However, working software must not be confused with product validation.

Validation should measure:

- Whether tutors complete onboarding.
- Whether tutors add real students.
- Whether tutors create fee cycles.
- Whether tutors send payment requests.
- Whether tutors return during the next collection cycle.
- Whether reminders reduce manual follow-up.
- Whether tutors understand and accept the subscription model.
- Whether manual payment verification is tolerable.

An optional evidence checkpoint is observing five to ten tutors complete at least one real collection cycle. This is an evidence target, not a blocker for completing the learning project.

## Technology direction

### Android

- Kotlin.
- Jetpack Compose.
- Modern Android architecture.
- ViewModel-based state management.
- Navigation Compose.
- Local caching and retryable background work.
- Push notifications.
- Crash and performance monitoring.

### Backend

- Go.
- Modular monolith initially.
- PostgreSQL.
- REST/JSON APIs.
- Managed hosting.
- Automated tests, logging, monitoring, and deployment checks.

The architecture should preserve reusable payment concepts without prematurely creating a generic multi-business platform.

## Product design and Figma

Design will be created feature by feature using one consistent design system.

Initial design areas:

1. Tutor onboarding.
2. Student management.
3. Fee setup and due dates.
4. Collection dashboard.
5. Payment-request creation.
6. Payment-link and QR sharing.
7. Manual payment verification.
8. Reminders.
9. Digital receipts.
10. Reports.
11. Subscription and coupon states.

The design system should define typography, spacing, colors, status indicators, forms, cards, tables, empty states, loading states, error states, and responsive behavior.

Critical states must be designed, not treated as afterthoughts:

- Empty.
- Loading.
- Offline.
- Failed.
- Overdue.
- Partial payment.
- Refunded payment.
- Expired subscription.
- Pending manual verification.
- Duplicate or already-settled payment.

## Development sequence

### Phase 0: Product discovery

- Observe how tutors currently track fees.
- Identify the smallest workflow worth replacing.
- Identify how Bilado can be meaningfully different from existing products.
- Confirm terminology used by tutors and parents.
- Evaluate payment-provider feasibility.
- Define the initial subscription model.
- Maintain this project context document.
- Establish repository conventions.

### Phase 1: Product flows and design

- Finalize tutor and student user flows.
- Define the MVP screen inventory.
- Establish the visual design system.
- Create Figma designs.
- Review all critical states and failure paths.

### Phase 2: Engineering foundation

- Create Android and Go projects.
- Establish local development environments.
- Set up PostgreSQL and migrations.
- Add CI checks.
- Add deployment environments.
- Add baseline logging, monitoring, and health checks.

### Phase 3: Core tutor workflow

- Tutor onboarding.
- Student management.
- Monthly fee setup.
- Due-date tracking.
- Collection dashboard.
- Payment-request creation.
- Reminder workflows.

### Phase 4: Payment and subscription implementation

- Select and integrate one payment provider.
- Implement student-payment workflows.
- Implement reconciliation and manual-reference fallback.
- Implement tutor subscription access.
- Implement coupon-trial behavior.
- Add receipts and exports.

### Phase 5: Hardening and pilot

- Add failure handling and retry behavior.
- Test critical security and authorization paths.
- Test database backup and restore.
- Deploy to a production-like environment.
- Run Play internal testing.
- Onboard a small number of tutors.
- Measure one complete collection cycle.

## Backend learning documentation

Every backend feature must include supporting learning material covering:

- What was implemented.
- Why the approach was chosen.
- Common alternatives.
- Trade-offs and failure modes.
- Security considerations.
- Official documentation.
- High-quality lectures, articles, or technical references.

These materials should be maintained in `docs/learning/` and updated as implementation progresses. References should explain the reasoning behind the implementation rather than merely provide links.

## Completion criteria

The MVP is complete only when:

- The primary tutor workflow works end to end.
- Payment states are visible and understandable.
- Reminder and receipt flows work.
- Subscription access behaves correctly.
- Important failure states are handled.
- Backend and Android tests cover critical workflows.
- The application is deployable.
- Monitoring and basic operational documentation exist.
- Resume claims can be supported by implemented code and tests.

## Resume policy

Resume descriptions will be generated only from features that are actually implemented, tested, and deployed or demonstrably runnable.

The project must not claim:

- Production-scale usage.
- Product-market fit.
- Automatic reconciliation for arbitrary payment identifiers.
- Security certifications.
- Real traction.
- Features that exist only in Figma or planning documents.

## Working assumptions

- Tuition-fee collection is the first validated workflow.
- The broader product direction is payment collection.
- One payment provider will be integrated initially.
- Email/password and Google login are planned for the first release.
- Phone OTP is deferred.
- Push and email reminders are preferred before WhatsApp API integration.
- The project is both a learning project and a potentially usable product.
- The next product artifact is the finalized user-flow specification.
- Figma designs follow the user-flow specification.
- Engineering implementation follows the approved flows and designs.
