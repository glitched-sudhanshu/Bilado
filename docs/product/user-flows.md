# Bilado MVP User Flows

## Purpose

This document defines the first product behavior and screen-level flows for Bilado. It is the source of truth for the first Figma designs.

The first workflow is tuition-fee collection, implemented on top of a company-based account model so the product can later support other payment-collection use cases.

## Actors and roles

### Company

A company is the tenant that owns students, cycles, payments, reports, users, and the subscription.

### `master_admin`

The first admin created during company onboarding.

Responsibilities:

- Manage the company account.
- Manage the subscription and coupon access.
- Manage students, cycles, fees, payment requests, reminders, receipts, and reports.
- Approve or reject user-access requests.
- Assign the final role to approved users.

### `admin`

An approved operational administrator.

Responsibilities in the initial product:

- Manage students, cycles, fees, payment requests, reminders, receipts, and reports.
- Approve or reject user-access requests if permitted by the company.
- Assign the final role to approved users.

The exact boundary between `master_admin` and `admin` can be tightened during authorization design, but an `admin` must never control ownership transfer or bypass company subscription rules.

### `editor`

Can manage operational collection data, including students, cycles, fee amounts, and payment requests. Cannot approve user access or manage the company subscription.

### `viewer`

Read-only access to the company dashboard, cycles, student payment status, and reports. Cannot modify collection data or approve users.

### Student or parent

Pays a fee through a payment request without creating a Bilado account.

## Global subscription rule

The subscription belongs to the company, not an individual user.

If the company subscription is expired, cancelled, or otherwise inactive:

- The `master_admin` and all approved company users are blocked from protected product functionality.
- The user can still access the subscription screen and account-recovery functionality.
- Existing company data must remain retained and recoverable after renewal.
- Subscription status must be visible and understandable; users must not see a generic authorization error.

For a new company, the first `master_admin` is sent to the subscription screen after registration and login. A user with an active company subscription proceeds to the home screen.

## Flow 1: Tutor login and company signup

### Login screen

The initial screen contains:

- Email field.
- Password field.
- Login button.
- Sign up button.
- Password-recovery entry point.

The signup entry point presents two separate options:

- Create a new company.
- Request access to an existing company.

### Company signup

1. User selects `Create a new company`.
2. User enters:
   - Company name.
   - Company username, which must be unique.
3. User continues to admin registration.
4. User enters:
   - Admin username.
   - Email address.
   - Password.
   - Confirm password.
5. The role is displayed as `master_admin` and cannot be changed.
6. User submits registration.
7. Bilado creates the company and its first `master_admin` together.
8. User is sent to login or directly authenticated, depending on the final authentication design.
9. After login, the company enters the subscription flow.

Validation:

- Company username must be unique.
- Admin username must be unique within the company.
- Email must be valid and not already linked to an incompatible account.
- Password and confirmation must match.
- Company creation must not leave an orphan company without an admin.

### Existing-company access request

1. User selects `Request access to an existing company`.
2. User enters:
   - Company username.
   - Requested username.
   - Email address.
   - Password.
   - Requested role: `viewer`, `editor`, or `admin`.
3. Bilado creates a pending access request.
4. The requester sees a confirmation that approval is required.
5. A `master_admin` or approved `admin` reviews the request.
6. The approver assigns the final role; the requester cannot grant themselves a role.
7. The request is approved or rejected.
8. The requester is notified of the result.
9. Approved users can log in only when the company subscription is active.

Rejected requests must not create an active company user. A rejected requester may submit a new request only according to the eventual retry policy.

## Flow 2: Subscription trial and access

### Subscription screen

The subscription screen shows:

- Available plan and price.
- Billing period.
- Benefits of the plan.
- Current company subscription state.
- Coupon-code input.
- Apply-coupon action.
- Updated price after a valid coupon.
- Buy subscription call to action.

The screen must clearly distinguish:

- Full price.
- Discount amount.
- Final amount.
- Coupon validity.
- Trial or promotional access.
- Subscription expiry.

### Coupon and payment behavior

1. User enters a coupon code.
2. Bilado validates the coupon.
3. The displayed price updates only after successful validation.
4. For the special development coupon, tapping `Pay` grants the company subscription directly without external payment.
5. For a paid plan without that coupon, the user proceeds to the payment screen.
6. The final amount is shown before payment confirmation.
7. A successful subscription payment activates company access.
8. A failed, cancelled, or pending payment does not activate protected access.

The development coupon must not be treated as a permanent production billing strategy. It is a controlled test path for building the entitlement flow.

## Flow 3: Home dashboard

After successful login with an active company subscription, the user enters the home screen.

The home screen contains:

- A primary `Create new cycle` call to action.
- A cycle list.
- Company-wide collection summary.
- Subscription status.
- Relevant pending actions, such as access requests or failed payment requests.

### Company-wide collection summary

The home screen shows aggregated values across cycles:

- Total amount expected.
- Total amount successfully collected.
- Total outstanding amount.
- Number of paid fee obligations.
- Number of partial fee obligations.
- Number of pending or failed payments.
- Number of overdue fee obligations.

The summary must define the reporting period used. The initial default should be the current active collection period, with later support for explicit date filtering.

## Flow 4: Create a new collection cycle

The `Create new cycle` action opens a form containing:

- Cycle name, such as `11th Class` or `12th Class`.
- Student list.
- Default amount to collect.
- Collection start date.
- Collection due date.
- Fine setting.
- Fine type: none, per day, or per month.
- Fine amount when a fine type is selected.

Rules:

- Cycle name is required.
- At least one student must be selected before activation.
- Default amount must be a valid positive amount.
- Due date cannot be earlier than the collection start date.
- Fine settings are optional.
- A student can have an individual amount override when their fee differs from the cycle default.
- A cycle can contain a new student or an existing student from the company.

On submission:

1. Bilado validates the cycle.
2. The cycle is created.
3. Fee obligations are created for the selected students.
4. Payment requests are generated automatically by the backend for eligible obligations.
5. The user is taken to the cycle detail screen.

The product should distinguish payment-request generation from message delivery. The backend can generate a request and link; actual delivery depends on the configured channel. WhatsApp API delivery remains out of scope for the MVP.

## Flow 5: Cycle list and cycle detail

### Cycle list

Each cycle card shows:

- Cycle name.
- Collection dates.
- Number of students.
- Expected amount.
- Collected amount.
- Outstanding amount.
- Paid, partial, pending, failed, and overdue counts.

Selecting a cycle opens its detail screen.

### Cycle detail

The cycle detail screen shows:

- Cycle information and dates.
- Default amount and fine settings.
- Total expected amount.
- Total collected amount.
- Total outstanding amount.
- Paid, partial, pending, failed, and overdue counts.
- Student/payment-obligation list.
- `Add student` action.
- Student edit action.
- `Force send payment requests` action.
- Reminder actions where applicable.

The user can add a student to an existing cycle. Adding a student creates a new fee obligation using the cycle default unless an individual amount is entered.

The user can edit student details and fee amounts. Once a payment exists, changing the fee amount must show the effect on the outstanding balance and be recorded for later audit design.

## Flow 6: Payment request generation and sharing

### Automatic generation

When a cycle is activated, Bilado automatically generates payment requests for its eligible student obligations.

Each request must be associated with:

- Company.
- Cycle.
- Student.
- Amount due.
- Current outstanding amount.
- Due date.
- Payment status.

### Force send

The cycle detail screen contains a force-send action.

Force-send must:

- Reissue or resend requests only for eligible unpaid or outstanding obligations.
- Avoid creating duplicate financial obligations.
- Clearly report how many requests were generated or sent.
- Show failures separately from successful requests.
- Require confirmation before sending to the entire cycle.

The actual delivery channel must be finalized before Figma and backend implementation. The MVP should support a hosted payment link and Android sharing; automated WhatsApp delivery remains excluded.

## Flow 7: Student or parent payment

1. Student or parent opens the payment request link.
2. No Bilado account is required.
3. The payment page shows:
   - Tutor/company name.
   - Student name or safe identifier.
   - Cycle name.
   - Amount due.
   - Outstanding balance.
   - Applicable fine, if any.
4. Student or parent completes payment through the supported provider.
5. Bilado shows a payment result page.
6. The tutor dashboard updates when the payment is confirmed.
7. A receipt becomes available after successful confirmation.

The payment page must not expose unrelated company data or other students.

## Flow 8: Multiple partial payments

A fee obligation supports multiple successful partial payments.

Example for a ₹2,000 obligation:

| Event | Paid | Outstanding | Status |
|---|---:|---:|---|
| Initial obligation | ₹0 | ₹2,000 | Pending |
| First payment | ₹800 | ₹1,200 | Partial |
| Second payment | ₹700 | ₹500 | Partial |
| Final payment | ₹500 | ₹0 | Paid |

Rules:

- Every payment is tracked separately.
- Successful payments increase the paid total.
- Failed or rejected payments do not increase the paid total.
- The paid total cannot exceed the obligation amount.
- The obligation becomes `paid` only when the successful total equals the amount due.
- Reminders use the outstanding balance.
- Each successful partial payment can have its own receipt or receipt history.
- Refunds reduce the successful paid total and may return the obligation to `partial` or `pending`.

## Flow 9: Payment statuses

The user-facing statuses are:

- `pending`: no confirmed successful payment yet, or payment confirmation is still awaited.
- `partial`: one or more successful payments exist, but the full obligation remains outstanding.
- `paid`: the full obligation has been collected.
- `overdue`: outstanding amount remains after the due date.
- `failed`: the latest payment attempt failed; the obligation may still be unpaid or partially paid.
- `refunded`: a successful payment was refunded; the remaining balance must be recalculated.

The final backend state machine will define exact transitions. The UI must never imply that a payment is successful based only on a client-side redirect.

## Flow 10: Reminders

Reminders are driven by the dates of the individual cycle:

- Collection start date.
- Due date.
- Overdue period.

Initial reminder behavior should be finalized before design. The minimum required behavior is:

- Remind eligible unpaid or partially-paid obligations.
- Show the current outstanding amount.
- Stop reminders after the obligation is fully paid.
- Continue overdue reminders only while an outstanding balance remains.
- Do not send reminders for refunded or rejected obligations without a new eligible balance.

## Flow 11: Receipts

After a successful payment confirmation:

- The student or parent can view a receipt.
- The tutor can access the receipt from the cycle and student payment history.
- A partial payment receipt must clearly show the amount received and remaining balance.
- A final receipt must show that the obligation is fully paid.

## Flow 12: Reports

### Home report

The home screen provides company-wide totals across the selected reporting period:

- Expected.
- Collected.
- Outstanding.
- Paid.
- Partial.
- Pending.
- Failed.
- Overdue.

### Cycle report

Each cycle provides the same totals scoped to that cycle, along with student-level status and amount details.

CSV export is available from the report context and must use the currently selected scope and reporting period.

## Flow 13: Access-request approval

The master admin or approved admin opens pending access requests and sees:

- Requester username.
- Email.
- Company requested.
- Requested role.
- Request date.
- Approve action.
- Reject action.

Approval requires selecting the final role. The requester’s proposed role is only a request, not an authorization.

After approval:

- The user becomes an approved company member.
- The final role controls available actions.
- The user can log in when the company subscription is active.

## Product decisions locked

- Subscription is owned by the company.
- Subscription expiry blocks the master admin and approved company users from protected functionality.
- Multiple partial payments are supported for one fee obligation.
- Only `master_admin` or an approved `admin` can approve access requests.
- The requester cannot grant themselves `admin`.
- Students and parents do not need Bilado accounts to pay.
- Tuition collection is the first workflow; other payment-collection use cases remain deferred.

## Decisions still required before Figma sign-off

- Exact subscription plans, prices, and billing period.
- Exact reminder schedule relative to start date, due date, and overdue days.
- First payment provider and supported payment methods.
- Initial delivery channel for automatically generated payment requests.
- Whether cycle reports default to the current month or the active cycle period.
- Whether an admin can approve another admin or only viewer/editor requests.
