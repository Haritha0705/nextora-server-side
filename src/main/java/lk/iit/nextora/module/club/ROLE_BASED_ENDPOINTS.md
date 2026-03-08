# 🔐 Club Module — Role-Based Endpoint Guide for Frontend Dashboards

> Use this guide to integrate the correct API endpoints into each user type's dashboard.
> **Base URL:** `http://localhost:8080`
> **Auth Header:** `Authorization: Bearer <JWT_TOKEN>`

---

## 📋 Quick Summary — Who Can Do What

| Feature Area | Student (Normal) | Student (Club Member) | Non-Academic Staff | Academic Staff | Admin | Super Admin |
|---|:---:|:---:|:---:|:---:|:---:|:---:|
| View clubs | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| Create club | ❌ | ❌ | ✅ | ❌ | ✅ | ✅ |
| Update/Delete club | ❌ | ✅* | ✅ | ❌ | ✅ | ✅ |
| Join club | ✅ | ✅ | ❌ | ❌ | ❌ | ❌ |
| Approve/Reject members | ❌ | ✅* | ✅ | ❌ | ✅ | ✅ |
| View announcements | ✅ | ✅ | ✅ | ❌ | ✅ | ✅ |
| Create/Edit announcements | ❌ | ✅* | ✅ | ❌ | ✅ | ✅ |
| View elections | ✅ | ✅ | ✅ | ❌ | ✅ | ✅ |
| Vote in elections | ✅ | ✅ | ❌ | ❌ | ❌ | ❌ |
| Nominate self | ❌ | ✅ | ❌ | ❌ | ❌ | ❌ |
| Manage elections | ❌ | ❌ | ✅ | ❌ | ✅ | ✅ |
| View stats & logs | ❌ | ✅* | ✅ | ❌ | ✅ | ✅ |
| Admin operations | ❌ | ❌ | ✅ | ❌ | ✅ | ✅ |

> `✅*` = Only club officers (President, VP, Secretary, Treasurer, Committee Member)

---

## 1️⃣ ROLE_STUDENT (Normal — Not a club member)

### Dashboard: "Explore Clubs"

```
┌─────────────────────────────────────────────────────┐
│  Student Dashboard — Club Section                    │
│                                                      │
│  📋 Browse Clubs    🔍 Search    📝 My Memberships  │
│  📢 Public Announcements    🗳️ Vote in Elections    │
└─────────────────────────────────────────────────────┘
```

#### Endpoints Available:

**Browse & Search Clubs**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club?page=0&size=10&sortBy=name&sortDirection=ASC` | Browse all clubs |
| `GET` | `/api/v1/club/{clubId}` | View club details |
| `GET` | `/api/v1/club/code/{clubCode}` | View club by code |
| `GET` | `/api/v1/club/search?keyword=computing&page=0&size=10` | Search clubs |
| `GET` | `/api/v1/club/faculty/{faculty}?page=0&size=10` | Filter by faculty |
| `GET` | `/api/v1/club/open-registration?page=0&size=10` | Clubs accepting members |

**Join a Club**
| Method | Endpoint | Body | Description |
|--------|----------|------|-------------|
| `POST` | `/api/v1/club/join` | `{"clubId": 1, "remarks": "I want to join"}` | Apply to join |

**My Memberships**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club/my-memberships?page=0&size=10` | View my memberships |
| `GET` | `/api/v1/club/memberships/{membershipId}` | Membership details |
| `DELETE` | `/api/v1/club/{clubId}/leave` | Leave a club |

**View Public Announcements**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club/announcements/club/{clubId}/public?page=0&size=10` | Public announcements only |
| `GET` | `/api/v1/club/announcements/{announcementId}` | View announcement detail |

**Elections (View + Vote)**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club/{clubId}/elections?page=0&size=10` | View club elections |
| `GET` | `/api/v1/club/{clubId}/elections/upcoming?page=0&size=10` | Upcoming elections |

---

## 2️⃣ ROLE_STUDENT + CLUB_MEMBER Sub-Role

### Dashboard: "My Club Dashboard"

> A student gets `CLUB_MEMBER` sub-role when their membership is **ACTIVE** in any club.
> Officers (President, VP, Secretary, Treasurer, Committee) get **elevated** permissions within their club.

```
┌──────────────────────────────────────────────────────────┐
│  Club Member Dashboard                                    │
│                                                           │
│  👥 Members    📢 Announcements    🗳️ Elections          │
│  📊 Statistics    📋 Activity Log    ⚙️ Club Settings    │
│                                                           │
│  [Only officers see: Settings, Approve, Manage sections]  │
└──────────────────────────────────────────────────────────┘
```

#### All Club Members (General + Officers):

**Club Info**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club/{clubId}` | View club details |
| `GET` | `/api/v1/club/{clubId}/statistics` | Club stats (real election data!) |

**Members**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club/{clubId}/members?page=0&size=10` | All members |
| `GET` | `/api/v1/club/{clubId}/members/active?page=0&size=10` | Active members |
| `GET` | `/api/v1/club/my-memberships?page=0&size=10` | My memberships |
| `DELETE` | `/api/v1/club/{clubId}/leave` | Leave club |

**Announcements (Read + Create + Edit)**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club/announcements/club/{clubId}?page=0&size=10` | All club announcements |
| `GET` | `/api/v1/club/announcements/club/{clubId}/pinned?page=0&size=10` | Pinned announcements |
| `GET` | `/api/v1/club/announcements/club/{clubId}/public?page=0&size=10` | Public announcements |
| `GET` | `/api/v1/club/announcements/search?keyword=meeting&page=0&size=10` | Search |
| `GET` | `/api/v1/club/announcements/{announcementId}` | View detail |
| `POST` | `/api/v1/club/announcements` | Create announcement (form-data) |
| `PUT` | `/api/v1/club/announcements/{announcementId}` | Update announcement (form-data) |

**Elections (View + Vote + Nominate)**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club/{clubId}/elections?page=0&size=10` | All club elections |
| `GET` | `/api/v1/club/{clubId}/elections/active?page=0&size=10` | Active elections |
| `GET` | `/api/v1/club/{clubId}/elections/upcoming?page=0&size=10` | Upcoming elections |
| `GET` | `/api/v1/club/election/{electionId}` | Election details |
| `GET` | `/api/v1/club/election/{electionId}/details` | Election with candidates |

**Stats & Activity (Club Officers only)**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club/{clubId}/statistics` | Club statistics |
| `GET` | `/api/v1/admin/club/{clubId}/activity-log?page=0&size=20` | Activity audit log |
| `GET` | `/api/v1/admin/club/{clubId}/activity-log/type/{type}?page=0&size=20` | Logs filtered by type |

---

#### Officers Only (President, VP, Secretary, Treasurer, Committee):

**Membership Management**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club/{clubId}/members/pending?page=0&size=10` | Pending applications |
| `POST` | `/api/v1/club/memberships/{membershipId}/approve` | Approve member |
| `POST` | `/api/v1/club/memberships/{membershipId}/reject?reason=...` | Reject member |
| `POST` | `/api/v1/club/memberships/{membershipId}/suspend?reason=...` | Suspend member |

**Club Settings (President only)**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `PUT` | `/api/v1/club/{clubId}` | Update club (form-data) |
| `PUT` | `/api/v1/club/{clubId}/toggle-registration` | Toggle registration open/closed |

---

## 3️⃣ ROLE_NON_ACADEMIC_STAFF

### Dashboard: "Club & Election Management Console"

> Non-academic staff have **full management** over clubs, announcements, elections, and candidates.

```
┌─────────────────────────────────────────────────────────────┐
│  Non-Academic Staff Dashboard — Club Management             │
│                                                              │
│  🏛️ Manage Clubs    👥 Manage Members    📢 Announcements  │
│  🗳️ Manage Elections    📊 Statistics    📋 Activity Logs  │
│  ⚡ Bulk Operations    🔄 Position Changes                  │
└─────────────────────────────────────────────────────────────┘
```

#### Club Management (Full CRUD)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/club` | Create new club (form-data with logo) |
| `GET` | `/api/v1/club?page=0&size=10` | List all clubs |
| `GET` | `/api/v1/club/{clubId}` | View club details |
| `GET` | `/api/v1/club/code/{clubCode}` | View club by code |
| `GET` | `/api/v1/club/search?keyword=...` | Search clubs |
| `GET` | `/api/v1/club/faculty/{faculty}?page=0&size=10` | Filter by faculty |
| `GET` | `/api/v1/club/open-registration?page=0&size=10` | Open registration clubs |
| `PUT` | `/api/v1/club/{clubId}` | Update club (form-data) |
| `DELETE` | `/api/v1/club/{clubId}` | Delete club |

#### Membership Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club/{clubId}/members?page=0&size=10` | All members |
| `GET` | `/api/v1/club/{clubId}/members/active?page=0&size=10` | Active members |
| `GET` | `/api/v1/club/{clubId}/members/pending?page=0&size=10` | Pending applications |
| `POST` | `/api/v1/club/memberships/{membershipId}/approve` | Approve |
| `POST` | `/api/v1/club/memberships/{membershipId}/reject?reason=...` | Reject |
| `POST` | `/api/v1/club/memberships/{membershipId}/suspend?reason=...` | Suspend |

#### Announcements (Full CRUD)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/club/announcements` | Create (form-data) |
| `GET` | `/api/v1/club/announcements/club/{clubId}?page=0&size=10` | List by club |
| `GET` | `/api/v1/club/announcements/club/{clubId}/public?page=0&size=10` | Public only |
| `GET` | `/api/v1/club/announcements/club/{clubId}/pinned?page=0&size=10` | Pinned only |
| `GET` | `/api/v1/club/announcements/search?keyword=...` | Search |
| `GET` | `/api/v1/club/announcements/{announcementId}` | View detail |
| `PUT` | `/api/v1/club/announcements/{announcementId}` | Update (form-data) |
| `DELETE` | `/api/v1/club/announcements/{announcementId}` | Delete |
| `PUT` | `/api/v1/club/announcements/{announcementId}/pin` | Pin |
| `PUT` | `/api/v1/club/announcements/{announcementId}/unpin` | Unpin |

#### Elections (View from Club + Full Management via Election module)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club/{clubId}/elections?page=0&size=10` | Club elections |
| `GET` | `/api/v1/club/{clubId}/elections/active?page=0&size=10` | Active elections |
| `GET` | `/api/v1/club/{clubId}/elections/upcoming?page=0&size=10` | Upcoming elections |

#### Admin Operations
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/admin/club/stats/{clubId}` | Detailed statistics |
| `GET` | `/api/v1/admin/club/{clubId}/statistics` | Statistics (officer view) |
| `GET` | `/api/v1/admin/club/{clubId}/activity-log?page=0&size=20` | Activity logs |
| `GET` | `/api/v1/admin/club/{clubId}/activity-log/type/{type}?page=0&size=20` | Logs by type |
| `PUT` | `/api/v1/admin/club/memberships/{membershipId}/position` | Change position |
| `POST` | `/api/v1/admin/club/{clubId}/memberships/bulk-approve` | Bulk approve |
| `PUT` | `/api/v1/admin/club/{clubId}/toggle-registration` | Toggle registration |

**Change Position Request Body:**
```json
{
  "membershipId": 1,
  "newPosition": "VICE_PRESIDENT",
  "reason": "Elected as VP"
}
```

**Bulk Approve Request Body:**
```json
[1, 2, 3, 4, 5]
```

---

## 4️⃣ ROLE_ACADEMIC_STAFF

### Dashboard: "Club Overview (Read-only)"

> Academic staff can **only view** clubs. They cannot manage clubs, memberships, or elections.

```
┌────────────────────────────────────────────────┐
│  Academic Staff Dashboard — Club Section        │
│                                                  │
│  📋 Browse Clubs (Read Only)                    │
└────────────────────────────────────────────────┘
```

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/club?page=0&size=10` | Browse clubs |
| `GET` | `/api/v1/club/{clubId}` | View club details |
| `GET` | `/api/v1/club/code/{clubCode}` | View by code |
| `GET` | `/api/v1/club/search?keyword=...` | Search clubs |
| `GET` | `/api/v1/club/faculty/{faculty}` | Filter by faculty |

> ⚠️ **No write access.** Academic staff cannot join, manage, or modify clubs.

---

## 5️⃣ ROLE_ADMIN

### Dashboard: "Full Club Administration"

> Admins have **all permissions** except super-admin-exclusive ones. Same as Non-Academic Staff + more.

```
┌──────────────────────────────────────────────────────────────┐
│  Admin Dashboard — Club Management                            │
│                                                               │
│  Everything from Non-Academic Staff PLUS:                     │
│  🗑️ Delete Clubs    ⚡ All Admin Operations                  │
└──────────────────────────────────────────────────────────────┘
```

**All endpoints from [Non-Academic Staff](#3️⃣-role_non_academic_staff) PLUS:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `DELETE` | `/api/v1/club/{clubId}` | Delete club |

> Admin has every Club Module endpoint available.

---

## 6️⃣ ROLE_SUPER_ADMIN

### Dashboard: "Platform-wide Club Control"

> Super Admin has **unrestricted access** — all endpoints work.

**All endpoints from [Admin](#5️⃣-role_admin) — no exceptions.**

---

## 🧩 Frontend Integration Cheat Sheet

### API Constants by Dashboard Section

```javascript
// === CLUB BASE URLS ===
const CLUB_BASE = '/api/v1/club';
const CLUB_ANNOUNCEMENTS_BASE = '/api/v1/club/announcements';
const CLUB_ADMIN_BASE = '/api/v1/admin/club';
const ELECTION_BASE = '/api/v1/club/election';

// === CLUB MANAGEMENT ===
const CLUBS = {
  LIST:              `${CLUB_BASE}`,                              // GET
  BY_ID:             (id) => `${CLUB_BASE}/${id}`,                // GET, PUT, DELETE
  BY_CODE:           (code) => `${CLUB_BASE}/code/${code}`,       // GET
  SEARCH:            `${CLUB_BASE}/search`,                       // GET ?keyword=
  BY_FACULTY:        (fac) => `${CLUB_BASE}/faculty/${fac}`,      // GET
  OPEN_REGISTRATION: `${CLUB_BASE}/open-registration`,            // GET
  STATISTICS:        (id) => `${CLUB_BASE}/${id}/statistics`,     // GET
  TOGGLE_REG:        (id) => `${CLUB_BASE}/${id}/toggle-registration`, // PUT
};

// === MEMBERSHIP ===
const MEMBERSHIP = {
  JOIN:              `${CLUB_BASE}/join`,                                         // POST
  MY_MEMBERSHIPS:    `${CLUB_BASE}/my-memberships`,                              // GET
  BY_ID:             (id) => `${CLUB_BASE}/memberships/${id}`,                   // GET
  LEAVE:             (clubId) => `${CLUB_BASE}/${clubId}/leave`,                 // DELETE
  MEMBERS:           (clubId) => `${CLUB_BASE}/${clubId}/members`,               // GET
  ACTIVE_MEMBERS:    (clubId) => `${CLUB_BASE}/${clubId}/members/active`,        // GET
  PENDING_MEMBERS:   (clubId) => `${CLUB_BASE}/${clubId}/members/pending`,       // GET
  APPROVE:           (id) => `${CLUB_BASE}/memberships/${id}/approve`,           // POST
  REJECT:            (id) => `${CLUB_BASE}/memberships/${id}/reject`,            // POST ?reason=
  SUSPEND:           (id) => `${CLUB_BASE}/memberships/${id}/suspend`,           // POST ?reason=
};

// === ANNOUNCEMENTS ===
const ANNOUNCEMENTS = {
  CREATE:            `${CLUB_ANNOUNCEMENTS_BASE}`,                               // POST form-data
  BY_ID:             (id) => `${CLUB_ANNOUNCEMENTS_BASE}/${id}`,                 // GET, PUT, DELETE
  BY_CLUB:           (clubId) => `${CLUB_ANNOUNCEMENTS_BASE}/club/${clubId}`,    // GET
  PUBLIC:            (clubId) => `${CLUB_ANNOUNCEMENTS_BASE}/club/${clubId}/public`,  // GET
  PINNED:            (clubId) => `${CLUB_ANNOUNCEMENTS_BASE}/club/${clubId}/pinned`,  // GET
  SEARCH:            `${CLUB_ANNOUNCEMENTS_BASE}/search`,                        // GET ?keyword=
  PIN:               (id) => `${CLUB_ANNOUNCEMENTS_BASE}/${id}/pin`,             // PUT
  UNPIN:             (id) => `${CLUB_ANNOUNCEMENTS_BASE}/${id}/unpin`,           // PUT
};

// === CLUB-SCOPED ELECTIONS ===
const CLUB_ELECTIONS = {
  ALL:               (clubId) => `${CLUB_BASE}/${clubId}/elections`,             // GET
  ACTIVE:            (clubId) => `${CLUB_BASE}/${clubId}/elections/active`,      // GET
  UPCOMING:          (clubId) => `${CLUB_BASE}/${clubId}/elections/upcoming`,    // GET
};

// === ADMIN ===
const CLUB_ADMIN = {
  STATS:             (clubId) => `${CLUB_ADMIN_BASE}/stats/${clubId}`,           // GET
  STATS_OFFICER:     (clubId) => `${CLUB_ADMIN_BASE}/${clubId}/statistics`,      // GET
  ACTIVITY_LOGS:     (clubId) => `${CLUB_ADMIN_BASE}/${clubId}/activity-log`,    // GET
  ACTIVITY_BY_TYPE:  (clubId, type) => `${CLUB_ADMIN_BASE}/${clubId}/activity-log/type/${type}`, // GET
  CHANGE_POSITION:   (memId) => `${CLUB_ADMIN_BASE}/memberships/${memId}/position`,  // PUT
  BULK_APPROVE:      (clubId) => `${CLUB_ADMIN_BASE}/${clubId}/memberships/bulk-approve`, // POST
  TOGGLE_REG:        (clubId) => `${CLUB_ADMIN_BASE}/${clubId}/toggle-registration`, // PUT
};
```

### Feature Flags by Role

```javascript
const CLUB_FEATURES = {
  // role = user's primary role, subroles = student sub-roles array
  canViewClubs: (role) =>
    ['ROLE_STUDENT', 'ROLE_NON_ACADEMIC_STAFF', 'ROLE_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role),

  canJoinClub: (role) =>
    role === 'ROLE_STUDENT',

  canCreateClub: (role) =>
    ['ROLE_NON_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role),

  canUpdateClub: (role, isOfficer) =>
    ['ROLE_NON_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role) || isOfficer,

  canDeleteClub: (role) =>
    ['ROLE_NON_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role),

  canManageMembers: (role, isOfficer) =>
    ['ROLE_NON_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role) || isOfficer,

  canCreateAnnouncement: (role, isClubMember) =>
    ['ROLE_NON_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role) || isClubMember,

  canViewAnnouncements: (role) =>
    ['ROLE_STUDENT', 'ROLE_NON_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role),

  canManageElections: (role) =>
    ['ROLE_NON_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role),

  canVote: (role) =>
    role === 'ROLE_STUDENT',

  canNominate: (role, isClubMember) =>
    role === 'ROLE_STUDENT' && isClubMember,

  canViewStats: (role, isOfficer) =>
    ['ROLE_NON_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role) || isOfficer,

  canViewActivityLogs: (role, isOfficer) =>
    ['ROLE_NON_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role) || isOfficer,

  canBulkApprove: (role) =>
    ['ROLE_NON_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role),

  canChangePosition: (role) =>
    ['ROLE_NON_ACADEMIC_STAFF', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN'].includes(role),
};
```

---

## 📊 Activity Log Types for Filtering

When calling `GET /api/v1/admin/club/{clubId}/activity-log/type/{type}`, use these values:

| Category | Type Values |
|----------|-------------|
| **Club** | `CLUB_CREATED`, `CLUB_UPDATED`, `CLUB_DELETED`, `CLUB_REGISTRATION_OPENED`, `CLUB_REGISTRATION_CLOSED` |
| **Members** | `MEMBER_JOINED`, `MEMBER_APPROVED`, `MEMBER_REJECTED`, `MEMBER_SUSPENDED`, `MEMBER_LEFT`, `MEMBER_POSITION_CHANGED`, `MEMBER_REVOKED` |
| **Announcements** | `ANNOUNCEMENT_POSTED`, `ANNOUNCEMENT_UPDATED`, `ANNOUNCEMENT_DELETED`, `ANNOUNCEMENT_PINNED` |
| **Elections** | `ELECTION_CREATED`, `ELECTION_NOMINATIONS_OPENED`, `ELECTION_NOMINATIONS_CLOSED`, `ELECTION_VOTING_OPENED`, `ELECTION_VOTING_CLOSED`, `ELECTION_RESULTS_PUBLISHED`, `ELECTION_CANCELLED`, `ELECTION_PRESIDENT_AUTO_UPDATED` |
| **Admin** | `BULK_MEMBER_APPROVED`, `ADMIN_OVERRIDE` |

---

## 🔑 Permission → Endpoint Mapping

| Permission | Endpoints It Unlocks |
|---|---|
| `CLUB:READ` | GET all club endpoints, GET statistics |
| `CLUB:CREATE` | POST `/api/v1/club` |
| `CLUB:UPDATE` | PUT `/api/v1/club/{id}`, PUT toggle-registration |
| `CLUB:DELETE` | DELETE `/api/v1/club/{id}` |
| `CLUB:VIEW_STATS` | GET `/api/v1/admin/club/stats/{id}` |
| `CLUB:VIEW_ACTIVITY_LOG` | GET `/api/v1/admin/club/{id}/activity-log` |
| `CLUB:MANAGE_MEMBERS` | PUT `/api/v1/admin/club/memberships/{id}/position` |
| `CLUB_MEMBERSHIP:VIEW` | GET members, GET my-memberships, POST join, DELETE leave |
| `CLUB_MEMBERSHIP:MANAGE` | POST approve/reject/suspend, POST bulk-approve, GET pending |
| `CLUB_ANNOUNCEMENT:CREATE` | POST announcements |
| `CLUB_ANNOUNCEMENT:READ` | GET all announcement endpoints |
| `CLUB_ANNOUNCEMENT:UPDATE` | PUT announcements, PUT pin/unpin |
| `CLUB_ANNOUNCEMENT:DELETE` | DELETE announcements |
| `ELECTION:READ` | GET club-scoped elections |

---

## 🚀 Dashboard API Endpoints (Single-Call Aggregated)

> **One API call per role** — returns a pre-aggregated dashboard response.
> Frontend renders the dashboard directly from the response without multiple API calls.

| # | Endpoint | Role | Permission | Description |
|---|----------|------|------------|-------------|
| 1 | `GET /api/v1/club/dashboard/student` | `ROLE_STUDENT` | `CLUB:READ` | Browseable clubs, open registrations, my memberships, featured clubs |
| 2 | `GET /api/v1/club/dashboard/club-member?clubId={id}` | `ROLE_STUDENT + CLUB_MEMBER` | `CLUB:READ` | Club detail, my position, announcements, elections, activity log, permission flags |
| 3 | `GET /api/v1/club/dashboard/staff` | `ROLE_NON_ACADEMIC_STAFF` | `CLUB:VIEW_STATS` | Platform-wide clubs, members, pending requests, elections, activity |
| 4 | `GET /api/v1/club/dashboard/admin` | `ROLE_ADMIN / ROLE_SUPER_ADMIN` | `CLUB:VIEW_STATS` | Full analytics: completed/cancelled elections, growth metrics |
| 5 | `GET /api/v1/club/dashboard/academic` | `ROLE_ACADEMIC_STAFF` | `CLUB:READ` | Read-only clubs overview + clubs they advise |

### Dashboard Response Shapes

**Student Dashboard** (`StudentClubDashboardResponse`):
```json
{
  "totalActiveClubs": 12,
  "openForRegistrationCount": 8,
  "myMembershipsCount": 3,
  "myPendingApplicationsCount": 1,
  "featuredClubs": [ ... ],
  "latestPublicAnnouncements": [ ... ],
  "myMemberships": [
    { "clubId": 1, "clubName": "CS Club", "clubCode": "CS-001", "position": "GENERAL_MEMBER", "status": "ACTIVE", "logoUrl": "..." }
  ],
  "generatedAt": "2026-03-06T10:30:00"
}
```

**Club Member Dashboard** (`ClubMemberDashboardResponse`):
```json
{
  "club": { ... },
  "myMembershipId": 42,
  "myPosition": "PRESIDENT",
  "myStatus": "ACTIVE",
  "totalMembers": 150,
  "activeMembers": 150,
  "pendingApplicationsCount": 5,
  "latestAnnouncements": [ ... ],
  "activeElections": [ ... ],
  "upcomingElections": [ ... ],
  "recentActivity": [ ... ],
  "canManageMembers": true,
  "canCreateAnnouncements": true,
  "canManageElections": false,
  "canViewStats": true,
  "canUpdateClub": true,
  "isOfficer": true,
  "generatedAt": "2026-03-06T10:30:00"
}
```

**Staff Dashboard** (`StaffClubDashboardResponse`):
```json
{
  "totalClubs": 15,
  "activeClubs": 12,
  "openForRegistrationCount": 8,
  "totalMembersAcrossClubs": 500,
  "pendingRequestsAcrossClubs": 23,
  "activeElectionsCount": 3,
  "totalElections": 10,
  "recentlyCreatedClubs": [ ... ],
  "clubsWithPendingApplications": [
    { "clubId": 1, "clubName": "CS Club", "clubCode": "CS-001", "pendingCount": 12 }
  ],
  "recentActivity": [ ... ],
  "generatedAt": "2026-03-06T10:30:00"
}
```

**Admin Dashboard** (`AdminClubDashboardResponse`):
```json
{
  "totalClubs": 15,
  "activeClubs": 12,
  "openForRegistrationCount": 8,
  "totalMembersAcrossClubs": 500,
  "pendingRequestsAcrossClubs": 23,
  "totalElections": 10,
  "activeElectionsCount": 3,
  "completedElections": 5,
  "cancelledElections": 2,
  "recentlyCreatedClubs": [ ... ],
  "clubsWithPendingApplications": [ ... ],
  "recentActivity": [ ... ],
  "clubsCreatedByMonth": null,
  "generatedAt": "2026-03-06T10:30:00"
}
```

**Academic Staff Dashboard** (`AcademicStaffClubDashboardResponse`):
```json
{
  "totalActiveClubs": 12,
  "openForRegistrationCount": 8,
  "clubs": [ ... ],
  "advisingClubs": [ ... ],
  "generatedAt": "2026-03-06T10:30:00"
}
```
