# 🏛️ Club Module — API Documentation

> **Base URL:** `http://localhost:8080`
> **Auth:** All endpoints require `Authorization: Bearer <JWT_TOKEN>` header.
> **Content-Type:** `application/json` unless stated as `multipart/form-data`.

---

## 📑 Table of Contents

- [Common Response Wrapper](#common-response-wrapper)
- [Paginated Response Wrapper](#paginated-response-wrapper)
- [1. Club Management](#1-club-management)
  - [1.1 Create Club](#11-create-club)
  - [1.2 Get Club by ID](#12-get-club-by-id)
  - [1.3 Get Club by Code](#13-get-club-by-code)
  - [1.4 Get All Clubs](#14-get-all-clubs)
  - [1.5 Search Clubs](#15-search-clubs)
  - [1.6 Get Clubs by Faculty](#16-get-clubs-by-faculty)
  - [1.7 Get Clubs Open for Registration](#17-get-clubs-open-for-registration)
  - [1.8 Update Club](#18-update-club)
  - [1.9 Delete Club](#19-delete-club)
  - [1.10 Get Club Statistics](#110-get-club-statistics)
  - [1.11 Toggle Registration](#111-toggle-registration)
- [2. Membership Management](#2-membership-management)
  - [2.1 Join Club](#21-join-club)
  - [2.2 Approve Membership](#22-approve-membership)
  - [2.3 Reject Membership](#23-reject-membership)
  - [2.4 Suspend Membership](#24-suspend-membership)
  - [2.5 Get Club Members](#25-get-club-members)
  - [2.6 Get Active Members](#26-get-active-members)
  - [2.7 Get Pending Applications](#27-get-pending-applications)
  - [2.8 Get My Memberships](#28-get-my-memberships)
  - [2.9 Get Membership by ID](#29-get-membership-by-id)
  - [2.10 Leave Club](#210-leave-club)
- [3. Club Announcements](#3-club-announcements)
  - [3.1 Create Announcement](#31-create-announcement)
  - [3.2 Update Announcement](#32-update-announcement)
  - [3.3 Get Announcement by ID](#33-get-announcement-by-id)
  - [3.4 Delete Announcement](#34-delete-announcement)
  - [3.5 Get Announcements by Club](#35-get-announcements-by-club)
  - [3.6 Get Public Announcements](#36-get-public-announcements)
  - [3.7 Get Pinned Announcements](#37-get-pinned-announcements)
  - [3.8 Search Announcements](#38-search-announcements)
  - [3.9 Pin Announcement](#39-pin-announcement)
  - [3.10 Unpin Announcement](#310-unpin-announcement)
- [4. Club Admin](#4-club-admin)
  - [4.1 Get Club Statistics (Admin)](#41-get-club-statistics-admin)
  - [4.2 Get Club Statistics (Officer)](#42-get-club-statistics-officer)
  - [4.3 Get Activity Logs](#43-get-activity-logs)
  - [4.4 Get Activity Logs by Type](#44-get-activity-logs-by-type)
  - [4.5 Change Member Position](#45-change-member-position)
  - [4.6 Bulk Approve Memberships](#46-bulk-approve-memberships)
  - [4.7 Toggle Registration (Admin)](#47-toggle-registration-admin)
- [5. Club-Scoped Elections](#5-club-scoped-elections)
  - [5.1 Get All Elections for Club](#51-get-all-elections-for-club)
  - [5.2 Get Active Elections for Club](#52-get-active-elections-for-club)
  - [5.3 Get Upcoming Elections for Club](#53-get-upcoming-elections-for-club)
- [Enums Reference](#enums-reference)
- [Module Architecture](#module-architecture)

---

## Common Response Wrapper

Every API response is wrapped in:

```json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... },
  "timestamp": "2026-03-05T10:30:00"
}
```

| Field       | Type      | Description                    |
|-------------|-----------|--------------------------------|
| `success`   | `boolean` | `true` if operation succeeded  |
| `message`   | `string`  | Human-readable status message  |
| `data`      | `object`  | Response payload (nullable)    |
| `timestamp` | `string`  | ISO-8601 timestamp             |

---

## Paginated Response Wrapper

Paginated endpoints return `data` as:

```json
{
  "content": [ ... ],
  "pageNumber": 0,
  "pageSize": 10,
  "totalElements": 42,
  "totalPages": 5,
  "first": true,
  "last": false,
  "empty": false
}
```

| Field           | Type      | Description                         |
|-----------------|-----------|-------------------------------------|
| `content`       | `array`   | List of items on this page          |
| `pageNumber`    | `int`     | Current page number (0-based)       |
| `pageSize`      | `int`     | Number of items per page            |
| `totalElements` | `long`    | Total number of items               |
| `totalPages`    | `int`     | Total number of pages               |
| `first`         | `boolean` | Whether this is the first page      |
| `last`          | `boolean` | Whether this is the last page       |
| `empty`         | `boolean` | Whether this page has no items      |

---

## 1. Club Management

**Base Path:** `/api/v1/club`

### 1.1 Create Club

Create a new club with optional logo upload.

| | |
|---|---|
| **URL** | `POST /api/v1/club` |
| **Content-Type** | `multipart/form-data` |
| **Permission** | `CLUB:CREATE` |
| **Roles** | Admin, Super Admin, Non-Academic Staff |

**Request (form-data):**

| Field               | Type      | Required | Default | Description                       |
|---------------------|-----------|----------|---------|-----------------------------------|
| `name`              | `string`  | ✅        |         | Club name (max 100 chars)         |
| `clubCode`          | `string`  | ✅        |         | Unique code (max 20 chars)        |
| `description`       | `string`  | ❌        |         | Club description (max 1000 chars) |
| `email`             | `string`  | ❌        |         | Club email                        |
| `contactNumber`     | `string`  | ❌        |         | Contact number (max 15 chars)     |
| `faculty`           | `string`  | ❌        |         | Enum: see [FacultyType](#enums-reference) |
| `establishedDate`   | `string`  | ❌        |         | Format: `YYYY-MM-DD`             |
| `maxMembers`        | `int`     | ❌        | `100`   | Maximum members allowed           |
| `socialMediaLinks`  | `string`  | ❌        |         | Social media links                |
| `presidentId`       | `long`    | ❌        |         | Student ID for president          |
| `advisorId`         | `long`    | ❌        |         | AcademicStaff ID for advisor      |
| `isRegistrationOpen`| `boolean` | ❌        | `true`  | Open for member registration      |
| `logo`              | `file`    | ❌        |         | Club logo image file              |

**Response — `ClubResponse`:**

```json
{
  "success": true,
  "message": "Club created successfully",
  "data": {
    "id": 1,
    "clubCode": "CS-001",
    "name": "Computing Society",
    "description": "A club for computing enthusiasts",
    "logoUrl": "https://s3.amazonaws.com/bucket/clubs/logos/abc123.png",
    "faculty": "COMPUTING",
    "email": "computing@iit.ac.lk",
    "contactNumber": "0771234567",
    "establishedDate": "2024-01-15",
    "socialMediaLinks": "https://facebook.com/cs-iit",
    "presidentId": 10,
    "presidentName": "John Doe",
    "presidentEmail": "john@iit.ac.lk",
    "advisorId": 5,
    "advisorName": "Dr. Smith",
    "advisorEmail": "smith@iit.ac.lk",
    "advisorDepartment": "Computer Science",
    "maxMembers": 200,
    "isRegistrationOpen": true,
    "totalMembers": 0,
    "activeMembers": 0,
    "totalElections": 0,
    "activeElections": 0,
    "createdAt": "2026-03-05T10:30:00",
    "updatedAt": "2026-03-05T10:30:00",
    "isActive": true
  },
  "timestamp": "2026-03-05T10:30:00"
}
```

---

### 1.2 Get Club by ID

| | |
|---|---|
| **URL** | `GET /api/v1/club/{clubId}` |
| **Permission** | `CLUB:READ` |

**Path Parameters:**

| Parameter | Type   | Description |
|-----------|--------|-------------|
| `clubId`  | `long` | Club ID     |

**Response:** `ClubResponse` (same shape as [1.1](#11-create-club))

---

### 1.3 Get Club by Code

| | |
|---|---|
| **URL** | `GET /api/v1/club/code/{clubCode}` |
| **Permission** | `CLUB:READ` |

**Path Parameters:**

| Parameter  | Type     | Description |
|------------|----------|-------------|
| `clubCode` | `string` | Club code   |

**Response:** `ClubResponse`

---

### 1.4 Get All Clubs

| | |
|---|---|
| **URL** | `GET /api/v1/club` |
| **Permission** | `CLUB:READ` |

**Query Parameters:**

| Parameter       | Type     | Default | Description          |
|-----------------|----------|---------|----------------------|
| `page`          | `int`    | `0`     | Page number          |
| `size`          | `int`    | `10`    | Items per page       |
| `sortBy`        | `string` | `name`  | Sort field           |
| `sortDirection` | `string` | `ASC`   | `ASC` or `DESC`      |

**Response:** `PagedResponse<ClubResponse>`

---

### 1.5 Search Clubs

| | |
|---|---|
| **URL** | `GET /api/v1/club/search` |
| **Permission** | `CLUB:READ` |

**Query Parameters:**

| Parameter | Type     | Required | Description        |
|-----------|----------|----------|--------------------|
| `keyword` | `string` | ✅        | Search keyword     |
| `page`    | `int`    | ❌        | Default: `0`       |
| `size`    | `int`    | ❌        | Default: `10`      |

**Response:** `PagedResponse<ClubResponse>`

---

### 1.6 Get Clubs by Faculty

| | |
|---|---|
| **URL** | `GET /api/v1/club/faculty/{faculty}` |
| **Permission** | `CLUB:READ` |

**Path Parameters:**

| Parameter | Type     | Description                           |
|-----------|----------|---------------------------------------|
| `faculty` | `string` | Enum: see [FacultyType](#enums-reference) |

**Query Parameters:** `page` (default `0`), `size` (default `10`)

**Response:** `PagedResponse<ClubResponse>`

---

### 1.7 Get Clubs Open for Registration

| | |
|---|---|
| **URL** | `GET /api/v1/club/open-registration` |
| **Permission** | `CLUB:READ` |

**Query Parameters:** `page` (default `0`), `size` (default `10`)

**Response:** `PagedResponse<ClubResponse>`

---

### 1.8 Update Club

| | |
|---|---|
| **URL** | `PUT /api/v1/club/{clubId}` |
| **Content-Type** | `multipart/form-data` |
| **Permission** | `CLUB:UPDATE` |

**Path Parameters:** `clubId` (long)

**Request (form-data):** Same as [1.1](#11-create-club) but all fields are **optional**.

**Response:** `ClubResponse`

---

### 1.9 Delete Club

| | |
|---|---|
| **URL** | `DELETE /api/v1/club/{clubId}` |
| **Permission** | `CLUB:DELETE` |
| **Status** | `204 No Content` |

**Response:**
```json
{
  "success": true,
  "message": "Club deleted successfully",
  "data": null,
  "timestamp": "2026-03-05T10:30:00"
}
```

---

### 1.10 Get Club Statistics

| | |
|---|---|
| **URL** | `GET /api/v1/club/{clubId}/statistics` |
| **Permission** | `CLUB:READ` |

**Response — `ClubStatisticsResponse`:**

```json
{
  "success": true,
  "message": "Statistics generated successfully for club: Computing Society",
  "data": {
    "clubId": 1,
    "clubName": "Computing Society",
    "clubCode": "CS-001",
    "totalMembers": 150,
    "activeMembers": 150,
    "pendingApplications": 12,
    "suspendedMembers": 0,
    "expiredMemberships": 0,
    "membershipGrowthRate": null,
    "totalElections": 5,
    "activeElections": 1,
    "completedElections": 3,
    "cancelledElections": 1,
    "averageParticipationRate": null,
    "highestParticipationRate": null,
    "highestParticipationElectionTitle": null,
    "totalCandidatesAllTime": 0,
    "averageCandidatesPerElection": null,
    "totalApprovedCandidates": null,
    "totalRejectedCandidates": null,
    "totalVotesCastAllTime": 0,
    "averageVotesPerElection": null,
    "mostVotesInSingleElection": null,
    "mostVotedElectionTitle": null,
    "membershipByMonth": null,
    "electionsByMonth": null,
    "votesByMonth": null,
    "recentActivities": null,
    "mostActiveCandidates": null,
    "generatedAt": "2026-03-05T10:30:00",
    "message": "Statistics generated successfully for club: Computing Society"
  },
  "timestamp": "2026-03-05T10:30:00"
}
```

---

### 1.11 Toggle Registration

| | |
|---|---|
| **URL** | `PUT /api/v1/club/{clubId}/toggle-registration` |
| **Permission** | `CLUB:UPDATE` |

**Response:** `ClubResponse` (with updated `isRegistrationOpen` value)

---

## 2. Membership Management

**Base Path:** `/api/v1/club`

### 2.1 Join Club

| | |
|---|---|
| **URL** | `POST /api/v1/club/join` |
| **Permission** | `CLUB_MEMBERSHIP:VIEW` |

**Request Body:**

```json
{
  "clubId": 1,
  "position": null,
  "remarks": "I want to join this club"
}
```

| Field      | Type     | Required | Description                                |
|------------|----------|----------|--------------------------------------------|
| `clubId`   | `long`   | ✅        | Club to join                               |
| `position` | `string` | ❌        | Enum: see [ClubPositionsType](#enums-reference) |
| `remarks`  | `string` | ❌        | Application remarks                        |

**Response — `ClubMembershipResponse`:**

```json
{
  "success": true,
  "message": "Membership application submitted successfully",
  "data": {
    "id": 1,
    "membershipNumber": "CS-001-2026-00001",
    "clubId": 1,
    "clubCode": "CS-001",
    "clubName": "Computing Society",
    "memberId": 42,
    "memberName": "Jane Smith",
    "memberEmail": "jane@iit.ac.lk",
    "memberStudentId": "20220001",
    "memberBatch": "2022",
    "status": "PENDING",
    "position": "GENERAL_MEMBER",
    "joinDate": "2026-03-05",
    "expiryDate": "2027-03-05",
    "remarks": "I want to join this club",
    "approvedAt": null,
    "approvedById": null,
    "approvedByName": null,
    "canVote": false,
    "canNominate": false,
    "createdAt": "2026-03-05T10:30:00",
    "updatedAt": "2026-03-05T10:30:00",
    "isActive": true
  },
  "timestamp": "2026-03-05T10:30:00"
}
```

---

### 2.2 Approve Membership

| | |
|---|---|
| **URL** | `POST /api/v1/club/memberships/{membershipId}/approve` |
| **Permission** | `CLUB_MEMBERSHIP:MANAGE` |

**Response:** `ClubMembershipResponse` (status changes to `ACTIVE`)

---

### 2.3 Reject Membership

| | |
|---|---|
| **URL** | `POST /api/v1/club/memberships/{membershipId}/reject?reason=Does not meet requirements` |
| **Permission** | `CLUB_MEMBERSHIP:MANAGE` |

**Query Parameters:**

| Parameter | Type     | Required | Description       |
|-----------|----------|----------|-------------------|
| `reason`  | `string` | ✅        | Rejection reason  |

**Response:**
```json
{
  "success": true,
  "message": "Membership rejected successfully",
  "data": null,
  "timestamp": "2026-03-05T10:30:00"
}
```

---

### 2.4 Suspend Membership

| | |
|---|---|
| **URL** | `POST /api/v1/club/memberships/{membershipId}/suspend?reason=Violation of club rules` |
| **Permission** | `CLUB_MEMBERSHIP:MANAGE` |

**Query Parameters:**

| Parameter | Type     | Required | Description        |
|-----------|----------|----------|--------------------|
| `reason`  | `string` | ✅        | Suspension reason  |

**Response:** `{ "success": true, "message": "Membership suspended successfully", "data": null }`

---

### 2.5 Get Club Members

| | |
|---|---|
| **URL** | `GET /api/v1/club/{clubId}/members?page=0&size=10` |
| **Permission** | `CLUB_MEMBERSHIP:VIEW` |

**Response:** `PagedResponse<ClubMembershipResponse>`

---

### 2.6 Get Active Members

| | |
|---|---|
| **URL** | `GET /api/v1/club/{clubId}/members/active?page=0&size=10` |
| **Permission** | `CLUB_MEMBERSHIP:VIEW` |

**Response:** `PagedResponse<ClubMembershipResponse>`

---

### 2.7 Get Pending Applications

| | |
|---|---|
| **URL** | `GET /api/v1/club/{clubId}/members/pending?page=0&size=10` |
| **Permission** | `CLUB_MEMBERSHIP:MANAGE` |

**Response:** `PagedResponse<ClubMembershipResponse>`

---

### 2.8 Get My Memberships

| | |
|---|---|
| **URL** | `GET /api/v1/club/my-memberships?page=0&size=10` |
| **Permission** | `CLUB_MEMBERSHIP:VIEW` |

Returns all club memberships for the currently authenticated user.

**Response:** `PagedResponse<ClubMembershipResponse>`

---

### 2.9 Get Membership by ID

| | |
|---|---|
| **URL** | `GET /api/v1/club/memberships/{membershipId}` |
| **Permission** | `CLUB_MEMBERSHIP:VIEW` |

**Response:** `ClubMembershipResponse`

---

### 2.10 Leave Club

| | |
|---|---|
| **URL** | `DELETE /api/v1/club/{clubId}/leave` |
| **Permission** | `CLUB_MEMBERSHIP:VIEW` |
| **Status** | `204 No Content` |

**Response:** `{ "success": true, "message": "Left club successfully", "data": null }`

---

## 3. Club Announcements

**Base Path:** `/api/v1/club/announcements`

### 3.1 Create Announcement

| | |
|---|---|
| **URL** | `POST /api/v1/club/announcements` |
| **Content-Type** | `multipart/form-data` |
| **Permission** | `CLUB_ANNOUNCEMENT:CREATE` |

**Request (form-data):**

| Field           | Type      | Required | Default    | Description                   |
|-----------------|-----------|----------|------------|-------------------------------|
| `clubId`        | `long`    | ✅        |            | Club ID                       |
| `title`         | `string`  | ✅        |            | Title (max 200 chars)         |
| `content`       | `string`  | ✅        |            | Content (max 5000 chars)      |
| `priority`      | `string`  | ❌        | `NORMAL`   | `LOW`, `NORMAL`, `HIGH`, `URGENT` |
| `isPinned`      | `boolean` | ❌        | `false`    | Pin to top                    |
| `isMembersOnly` | `boolean` | ❌        | `false`    | Visible only to club members  |
| `attachment`    | `file`    | ❌        |            | File attachment               |

**Response — `ClubAnnouncementResponse`:**

```json
{
  "success": true,
  "message": "Announcement created successfully",
  "data": {
    "id": 1,
    "clubId": 1,
    "clubCode": "CS-001",
    "clubName": "Computing Society",
    "title": "Welcome New Members!",
    "content": "We are excited to welcome all new members...",
    "priority": "HIGH",
    "isPinned": true,
    "isMembersOnly": false,
    "authorId": 42,
    "authorName": "Jane Smith",
    "authorEmail": "jane@iit.ac.lk",
    "attachmentUrl": "https://s3.amazonaws.com/bucket/announcements/file.pdf",
    "attachmentName": "schedule.pdf",
    "viewCount": 0,
    "createdAt": "2026-03-05T10:30:00",
    "updatedAt": "2026-03-05T10:30:00",
    "isActive": true
  },
  "timestamp": "2026-03-05T10:30:00"
}
```

---

### 3.2 Update Announcement

| | |
|---|---|
| **URL** | `PUT /api/v1/club/announcements/{announcementId}` |
| **Content-Type** | `multipart/form-data` |
| **Permission** | `CLUB_ANNOUNCEMENT:UPDATE` |

**Request (form-data):** Same as [3.1](#31-create-announcement) but all fields are **optional** (except `clubId` not needed).

**Response:** `ClubAnnouncementResponse`

---

### 3.3 Get Announcement by ID

| | |
|---|---|
| **URL** | `GET /api/v1/club/announcements/{announcementId}` |
| **Permission** | `CLUB_ANNOUNCEMENT:READ` |

> ⚠️ **Note:** Increments the view count each time it's accessed.

**Response:** `ClubAnnouncementResponse`

---

### 3.4 Delete Announcement

| | |
|---|---|
| **URL** | `DELETE /api/v1/club/announcements/{announcementId}` |
| **Permission** | `CLUB_ANNOUNCEMENT:DELETE` |
| **Status** | `204 No Content` |

**Response:** `{ "success": true, "message": "Announcement deleted successfully", "data": null }`

---

### 3.5 Get Announcements by Club

| | |
|---|---|
| **URL** | `GET /api/v1/club/announcements/club/{clubId}?page=0&size=10` |
| **Permission** | `CLUB_ANNOUNCEMENT:READ` |

Returns announcements sorted: pinned first, then by creation date descending.

**Response:** `PagedResponse<ClubAnnouncementResponse>`

---

### 3.6 Get Public Announcements

| | |
|---|---|
| **URL** | `GET /api/v1/club/announcements/club/{clubId}/public?page=0&size=10` |
| **Permission** | `CLUB_ANNOUNCEMENT:READ` |

Returns only non-members-only announcements.

**Response:** `PagedResponse<ClubAnnouncementResponse>`

---

### 3.7 Get Pinned Announcements

| | |
|---|---|
| **URL** | `GET /api/v1/club/announcements/club/{clubId}/pinned?page=0&size=10` |
| **Permission** | `CLUB_ANNOUNCEMENT:READ` |

**Response:** `PagedResponse<ClubAnnouncementResponse>`

---

### 3.8 Search Announcements

| | |
|---|---|
| **URL** | `GET /api/v1/club/announcements/search?keyword=meeting&page=0&size=10` |
| **Permission** | `CLUB_ANNOUNCEMENT:READ` |

**Response:** `PagedResponse<ClubAnnouncementResponse>`

---

### 3.9 Pin Announcement

| | |
|---|---|
| **URL** | `PUT /api/v1/club/announcements/{announcementId}/pin` |
| **Permission** | `CLUB_ANNOUNCEMENT:UPDATE` |

**Response:** `ClubAnnouncementResponse` (with `isPinned: true`)

---

### 3.10 Unpin Announcement

| | |
|---|---|
| **URL** | `PUT /api/v1/club/announcements/{announcementId}/unpin` |
| **Permission** | `CLUB_ANNOUNCEMENT:UPDATE` |

**Response:** `ClubAnnouncementResponse` (with `isPinned: false`)

---

## 4. Club Admin

**Base Path:** `/api/v1/admin/club`

### 4.1 Get Club Statistics (Admin)

| | |
|---|---|
| **URL** | `GET /api/v1/admin/club/stats/{clubId}` |
| **Permission** | `CLUB:VIEW_STATS` |

**Response:** `ClubStatisticsResponse` (see [1.10](#110-get-club-statistics))

---

### 4.2 Get Club Statistics (Officer)

| | |
|---|---|
| **URL** | `GET /api/v1/admin/club/{clubId}/statistics` |
| **Permission** | `CLUB:READ` |

**Response:** `ClubStatisticsResponse`

---

### 4.3 Get Activity Logs

| | |
|---|---|
| **URL** | `GET /api/v1/admin/club/{clubId}/activity-log?page=0&size=20` |
| **Permission** | `CLUB:VIEW_ACTIVITY_LOG` |

**Response — `PagedResponse<ClubActivityLogResponse>`:**

```json
{
  "success": true,
  "message": "Activity logs retrieved successfully",
  "data": {
    "content": [
      {
        "id": 1,
        "clubId": 1,
        "clubName": "Computing Society",
        "activityType": "MEMBER_JOINED",
        "description": "Jane Smith joined the club",
        "performedByUserId": 42,
        "performedByName": "Jane Smith",
        "targetUserId": null,
        "targetUserName": null,
        "relatedEntityId": 10,
        "relatedEntityType": "ClubMembership",
        "metadata": null,
        "createdAt": "2026-03-05T10:30:00"
      }
    ],
    "pageNumber": 0,
    "pageSize": 20,
    "totalElements": 1,
    "totalPages": 1,
    "first": true,
    "last": true,
    "empty": false
  },
  "timestamp": "2026-03-05T10:30:00"
}
```

---

### 4.4 Get Activity Logs by Type

| | |
|---|---|
| **URL** | `GET /api/v1/admin/club/{clubId}/activity-log/type/{type}?page=0&size=20` |
| **Permission** | `CLUB:VIEW_ACTIVITY_LOG` |

**Path Parameters:**

| Parameter | Type     | Description                                      |
|-----------|----------|--------------------------------------------------|
| `type`    | `string` | Activity type enum (see [ActivityType](#activitytype)) |

**Response:** `PagedResponse<ClubActivityLogResponse>`

---

### 4.5 Change Member Position

| | |
|---|---|
| **URL** | `PUT /api/v1/admin/club/memberships/{membershipId}/position` |
| **Permission** | `CLUB:MANAGE_MEMBERS` |

**Request Body:**

```json
{
  "membershipId": 1,
  "newPosition": "VICE_PRESIDENT",
  "reason": "Elected as Vice President in the 2026 elections"
}
```

| Field         | Type     | Required | Description                                |
|---------------|----------|----------|--------------------------------------------|
| `membershipId`| `long`   | ✅        | Membership ID                              |
| `newPosition` | `string` | ✅        | Enum: see [ClubPositionsType](#clubpositionstype) |
| `reason`      | `string` | ❌        | Reason for position change                 |

**Response:** `ClubMembershipResponse` (with updated `position`)

---

### 4.6 Bulk Approve Memberships

| | |
|---|---|
| **URL** | `POST /api/v1/admin/club/{clubId}/memberships/bulk-approve` |
| **Permission** | `CLUB_MEMBERSHIP:MANAGE` |

**Request Body:**

```json
[1, 2, 3, 4, 5]
```

Array of membership IDs (`List<Long>`) to approve.

**Response:**

```json
{
  "success": true,
  "message": "Bulk approval completed: 5 memberships approved",
  "data": [
    { "id": 1, "status": "ACTIVE", "..." : "..." },
    { "id": 2, "status": "ACTIVE", "..." : "..." }
  ],
  "timestamp": "2026-03-05T10:30:00"
}
```

---

### 4.7 Toggle Registration (Admin)

| | |
|---|---|
| **URL** | `PUT /api/v1/admin/club/{clubId}/toggle-registration` |
| **Permission** | `CLUB:UPDATE` |

**Response:** `ClubResponse` (with toggled `isRegistrationOpen`)

---

## 5. Club-Scoped Elections

**Base Path:** `/api/v1/club`

These endpoints provide a club-centric view of elections. Full election CRUD is in the **Election Module** (`/api/v1/club/election`).

### 5.1 Get All Elections for Club

| | |
|---|---|
| **URL** | `GET /api/v1/club/{clubId}/elections?page=0&size=10&sortBy=createdAt&sortDirection=DESC` |
| **Permission** | `ELECTION:READ` |

**Query Parameters:**

| Parameter       | Type     | Default      | Description     |
|-----------------|----------|--------------|-----------------|
| `page`          | `int`    | `0`          | Page number     |
| `size`          | `int`    | `10`         | Items per page  |
| `sortBy`        | `string` | `createdAt`  | Sort field      |
| `sortDirection` | `string` | `DESC`       | `ASC` or `DESC` |

**Response — `PagedResponse<ElectionResponse>`:**

```json
{
  "success": true,
  "message": "Club elections retrieved successfully",
  "data": {
    "content": [
      {
        "id": 1,
        "title": "President Election 2026",
        "description": "Annual president election",
        "electionType": "PRESIDENT",
        "status": "VOTING_OPEN",
        "clubId": 1,
        "clubName": "Computing Society",
        "clubCode": "CS-001",
        "nominationStartTime": "2026-02-01T09:00:00",
        "nominationEndTime": "2026-02-10T17:00:00",
        "votingStartTime": "2026-02-15T09:00:00",
        "votingEndTime": "2026-02-20T17:00:00",
        "resultsPublishedAt": null,
        "maxCandidates": 10,
        "winnersCount": 1,
        "isAnonymousVoting": true,
        "requireManifesto": true,
        "eligibilityCriteria": "Must be active member for 3+ months",
        "createdById": 5,
        "createdByName": "Admin User",
        "totalCandidates": 5,
        "approvedCandidates": 4,
        "totalVotes": 120,
        "eligibleVoters": 150,
        "participationRate": 80.0,
        "canNominate": false,
        "canVote": true,
        "canViewResults": false,
        "isActive": true,
        "candidates": null,
        "createdAt": "2026-01-20T10:00:00",
        "updatedAt": "2026-02-15T09:00:00"
      }
    ],
    "pageNumber": 0,
    "pageSize": 10,
    "totalElements": 5,
    "totalPages": 1,
    "first": true,
    "last": true,
    "empty": false
  },
  "timestamp": "2026-03-05T10:30:00"
}
```

---

### 5.2 Get Active Elections for Club

| | |
|---|---|
| **URL** | `GET /api/v1/club/{clubId}/elections/active?page=0&size=10` |
| **Permission** | `ELECTION:READ` |

Returns elections with `VOTING_OPEN` or `NOMINATION_OPEN` status.

**Response:** `PagedResponse<ElectionResponse>`

---

### 5.3 Get Upcoming Elections for Club

| | |
|---|---|
| **URL** | `GET /api/v1/club/{clubId}/elections/upcoming?page=0&size=10` |
| **Permission** | `ELECTION:READ` |

Returns elections with voting start time in the future.

**Response:** `PagedResponse<ElectionResponse>`

---

## Enums Reference

### FacultyType

```
COMPUTING, ENGINEERING, BUSINESS, LAW, HUMANITIES, ARCHITECTURE, LIFE_SCIENCES, QUANTITY_SURVEYING
```

### ClubPositionsType

| Value              | Description         |
|--------------------|---------------------|
| `PRESIDENT`        | Club President      |
| `VICE_PRESIDENT`   | Vice President      |
| `SECRETARY`        | Secretary           |
| `TREASURER`        | Treasurer           |
| `COMMITTEE_MEMBER` | Committee Member    |
| `GENERAL_MEMBER`   | General Member      |

### ClubMembershipStatus

```
PENDING, ACTIVE, SUSPENDED, EXPIRED, REJECTED, LEFT
```

### AnnouncementPriority

```
LOW, NORMAL, HIGH, URGENT
```

### ActivityType

| Category       | Values |
|----------------|--------|
| **Club**       | `CLUB_CREATED`, `CLUB_UPDATED`, `CLUB_DELETED`, `CLUB_REGISTRATION_OPENED`, `CLUB_REGISTRATION_CLOSED` |
| **Membership** | `MEMBER_JOINED`, `MEMBER_APPROVED`, `MEMBER_REJECTED`, `MEMBER_SUSPENDED`, `MEMBER_LEFT`, `MEMBER_POSITION_CHANGED`, `MEMBER_REVOKED` |
| **Announcement** | `ANNOUNCEMENT_POSTED`, `ANNOUNCEMENT_UPDATED`, `ANNOUNCEMENT_DELETED`, `ANNOUNCEMENT_PINNED` |
| **Election**   | `ELECTION_CREATED`, `ELECTION_NOMINATIONS_OPENED`, `ELECTION_NOMINATIONS_CLOSED`, `ELECTION_VOTING_OPENED`, `ELECTION_VOTING_CLOSED`, `ELECTION_RESULTS_PUBLISHED`, `ELECTION_CANCELLED`, `ELECTION_PRESIDENT_AUTO_UPDATED` |
| **Admin**      | `BULK_MEMBER_APPROVED`, `ADMIN_OVERRIDE` |

### ElectionStatus

```
DRAFT, NOMINATION_OPEN, NOMINATION_CLOSED, VOTING_OPEN, VOTING_CLOSED, RESULTS_PUBLISHED, CANCELLED, ARCHIVED
```

### ElectionType

```
PRESIDENT, VICE_PRESIDENT, SECRETARY, TREASURER, GENERAL, POLL, REFERENDUM
```

---

## Module Architecture

```
club/
├── controller/
│   ├── ClubController.java            # Club CRUD + Membership + Stats + Club-scoped Elections
│   ├── ClubAdminController.java       # Admin: stats, activity logs, bulk ops, position change
│   └── ClubAnnouncementController.java # Announcement CRUD, pin/unpin, search
├── dto/
│   ├── request/
│   │   ├── CreateClubRequest.java
│   │   ├── JoinClubRequest.java
│   │   ├── CreateAnnouncementRequest.java
│   │   └── ChangeMemberPositionRequest.java
│   └── response/
│       ├── ClubResponse.java
│       ├── ClubMembershipResponse.java
│       ├── ClubAnnouncementResponse.java
│       ├── ClubStatisticsResponse.java
│       └── ClubActivityLogResponse.java
├── entity/
│   ├── Club.java                      # @OneToMany → Election (bidirectional)
│   ├── ClubMembership.java            # Member-club relationship
│   ├── ClubAnnouncement.java          # Announcements with priority/pinning
│   └── ClubActivityLog.java           # Audit trail (30+ activity types)
├── mapper/
│   └── ClubMapper.java                # MapStruct mapper for all entities
├── repository/
│   ├── ClubRepository.java
│   ├── ClubMembershipRepository.java
│   ├── ClubAnnouncementRepository.java
│   └── ClubActivityLogRepository.java
└── service/
    ├── ClubService.java               # Interface
    ├── ClubAnnouncementService.java   # Interface
    ├── ClubActivityLogService.java    # Interface
    └── impl/
        ├── ClubServiceImpl.java       # Injects ElectionRepository for real stats
        ├── ClubAnnouncementServiceImpl.java
        └── ClubActivityLogServiceImpl.java
```

### Key Integration Points with Election Module

| Feature | Description |
|---------|-------------|
| **`Club.elections`** | `@OneToMany` bidirectional relationship to `Election` entity |
| **Real Stats** | `ClubResponse.totalElections` / `activeElections` populated from `ElectionRepository` |
| **Activity Logging** | `ElectionServiceImpl` logs all lifecycle events to `ClubActivityLog` |
| **Auto-President Update** | When a `PRESIDENT` election's results are published, the club's president is automatically updated to the winner |
| **Club-Scoped Endpoints** | `/club/{clubId}/elections`, `/elections/active`, `/elections/upcoming` |

---

## Error Responses

All errors follow this format:

```json
{
  "success": false,
  "message": "Club not found with id: 999",
  "data": null,
  "timestamp": "2026-03-05T10:30:00"
}
```

| HTTP Status | Scenario |
|-------------|----------|
| `400 Bad Request` | Invalid input, business rule violation |
| `401 Unauthorized` | Missing/invalid JWT token |
| `403 Forbidden` | Insufficient permissions |
| `404 Not Found` | Resource not found |
| `409 Conflict` | Duplicate resource (e.g., already a member) |

---

> **Postman Collection:** Available at `docs/postman/Nextora_Club_Module.postman_collection.json`
> Import into Postman and set `{{baseUrl}}` and `{{token}}` variables.

