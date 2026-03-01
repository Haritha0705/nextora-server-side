# Nextora Content API Documentation

**Base URL:** `http://localhost:8080/api/v1`  
**Version:** 1.0  
**Date:** March 1, 2026  
**All requests:** `GET`  
**Authentication:** Bearer Token (JWT) required in `Authorization` header  
**Content-Type:** `application/json`

---

## Common Response Wrapper

All responses follow the standard `ApiResponse<T>` format:

```json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... },
  "timestamp": "2026-03-01T10:30:00"
}
```

### Error Response

```json
{
  "success": false,
  "message": "Error description",
  "data": null,
  "timestamp": "2026-03-01T10:30:00"
}
```

### HTTP Status Codes

| Code | Description |
|------|------------|
| 200  | OK – Request succeeded |
| 401  | Unauthorized – Missing or invalid JWT |
| 403  | Forbidden – Insufficient permissions |
| 404  | Not Found – Resource not found |
| 500  | Internal Server Error |

---

## Table of Contents

1. [Student Complaints](#1-student-complaints)
2. [Academic Calendars](#2-academic-calendars)
3. [Undergraduate Programs](#3-undergraduate-programs)
4. [Postgraduate Programs](#4-postgraduate-programs)
5. [Foundation Program](#5-foundation-program)
6. [Students Relations Unit](#6-students-relations-unit)
7. [Student Policies](#7-student-policies)
8. [Mitigation Forms](#8-mitigation-forms)
9. [Staff](#9-staff)
10. [Info](#10-info)

---

## 1. Student Complaints

### 1.1 Get All Student Complaint Categories

**GET** `/api/v1/student-complaints`

Returns a list of all student complaint categories.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": [
    {
      "id": 1,
      "categoryName": "Academic Course Delivery",
      "categorySlug": "academic-course-delivery",
      "description": "Complaints related to academic course delivery including lectures, assignments, and assessments",
      "formUrl": "https://forms.example.com/academic-course-delivery",
      "contactEmail": "complaints.academic@iit.ac.lk",
      "isActive": true
    },
    {
      "id": 2,
      "categoryName": "Facility and Support System",
      "categorySlug": "facility-and-support-system",
      "description": "Complaints related to campus facilities, IT support, library, and other support systems",
      "formUrl": "https://forms.example.com/facility-support",
      "contactEmail": "complaints.facility@iit.ac.lk",
      "isActive": true
    }
  ],
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 1.2 Get Academic Course Delivery Complaints Info

**GET** `/api/v1/student-complaints/academic-course-delivery`

Returns details and instructions for academic course delivery complaints.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 1,
    "categoryName": "Academic Course Delivery",
    "categorySlug": "academic-course-delivery",
    "description": "Submit complaints regarding academic course delivery including lecture quality, module content, assessment fairness, lecturer availability, and academic support.",
    "formUrl": "https://forms.example.com/academic-course-delivery",
    "contactEmail": "complaints.academic@iit.ac.lk",
    "contactPhone": "+94 11 234 5678",
    "instructions": "Please describe your complaint in detail. Include the module code, lecturer name, and specific dates if applicable. All complaints are handled confidentially.",
    "responseTimeBusinessDays": 5,
    "isActive": true,
    "lastUpdated": "2026-02-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 1.3 Get Facility and Support System Complaints Info

**GET** `/api/v1/student-complaints/facility-and-support-system`

Returns details and instructions for facility and support system complaints.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 2,
    "categoryName": "Facility and Support System",
    "categorySlug": "facility-and-support-system",
    "description": "Submit complaints regarding campus facilities, IT infrastructure, library services, lab equipment, Wi-Fi, student support systems, and general campus maintenance.",
    "formUrl": "https://forms.example.com/facility-support",
    "contactEmail": "complaints.facility@iit.ac.lk",
    "contactPhone": "+94 11 234 5679",
    "instructions": "Please specify the facility/service involved, the issue encountered, and the location. Attach photos if applicable.",
    "responseTimeBusinessDays": 3,
    "isActive": true,
    "lastUpdated": "2026-02-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

## 2. Academic Calendars

### 2.1 Get All Academic Calendars

**GET** `/api/v1/academic-calendars`

Returns a list of all available academic calendars.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": [
    {
      "id": 1,
      "universityName": "University of Wolverhampton (UoW)",
      "universitySlug": "uow",
      "academicYear": "2025/2026",
      "calendarFileUrl": "https://storage.example.com/calendars/uow-2025-2026.pdf",
      "lastUpdated": "2026-01-10T08:00:00"
    },
    {
      "id": 2,
      "universityName": "Robert Gordon University (RGU)",
      "universitySlug": "rgu",
      "academicYear": "2025/2026",
      "calendarFileUrl": "https://storage.example.com/calendars/rgu-2025-2026.pdf",
      "lastUpdated": "2026-01-10T08:00:00"
    }
  ],
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 2.2 Get UoW Academic Calendar

**GET** `/api/v1/academic-calendars/uow`

Returns the University of Wolverhampton academic calendar details.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 1,
    "universityName": "University of Wolverhampton (UoW)",
    "universitySlug": "uow",
    "academicYear": "2025/2026",
    "calendarFileUrl": "https://storage.example.com/calendars/uow-2025-2026.pdf",
    "events": [
      {
        "eventName": "Semester 1 Start",
        "startDate": "2025-09-15",
        "endDate": "2025-09-15",
        "eventType": "SEMESTER_START"
      },
      {
        "eventName": "Mid-Semester Break",
        "startDate": "2025-11-03",
        "endDate": "2025-11-09",
        "eventType": "BREAK"
      },
      {
        "eventName": "Semester 1 Examinations",
        "startDate": "2026-01-12",
        "endDate": "2026-01-30",
        "eventType": "EXAM_PERIOD"
      },
      {
        "eventName": "Semester 2 Start",
        "startDate": "2026-02-10",
        "endDate": "2026-02-10",
        "eventType": "SEMESTER_START"
      },
      {
        "eventName": "Semester 2 Examinations",
        "startDate": "2026-06-01",
        "endDate": "2026-06-19",
        "eventType": "EXAM_PERIOD"
      }
    ],
    "lastUpdated": "2026-01-10T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 2.3 Get RGU Academic Calendar

**GET** `/api/v1/academic-calendars/rgu`

Returns the Robert Gordon University academic calendar details.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 2,
    "universityName": "Robert Gordon University (RGU)",
    "universitySlug": "rgu",
    "academicYear": "2025/2026",
    "calendarFileUrl": "https://storage.example.com/calendars/rgu-2025-2026.pdf",
    "events": [
      {
        "eventName": "Trimester 1 Start",
        "startDate": "2025-09-22",
        "endDate": "2025-09-22",
        "eventType": "SEMESTER_START"
      },
      {
        "eventName": "Winter Break",
        "startDate": "2025-12-20",
        "endDate": "2026-01-05",
        "eventType": "BREAK"
      },
      {
        "eventName": "Trimester 1 Examinations",
        "startDate": "2026-01-19",
        "endDate": "2026-02-06",
        "eventType": "EXAM_PERIOD"
      },
      {
        "eventName": "Trimester 2 Start",
        "startDate": "2026-02-17",
        "endDate": "2026-02-17",
        "eventType": "SEMESTER_START"
      },
      {
        "eventName": "Trimester 2 Examinations",
        "startDate": "2026-05-25",
        "endDate": "2026-06-12",
        "eventType": "EXAM_PERIOD"
      }
    ],
    "lastUpdated": "2026-01-10T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

## 3. Undergraduate Programs

### 3.1 Get All Undergraduate Programs

**GET** `/api/v1/undergraduate`

Returns a list of all undergraduate degree programs.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": [
    {
      "id": 1,
      "programCode": "BSC_AI_DS",
      "programName": "BSc (Hons) Artificial Intelligence and Data Science",
      "programSlug": "bsc-ai-ds",
      "awardingUniversity": "University of Wolverhampton",
      "duration": "3 years",
      "isActive": true
    },
    {
      "id": 2,
      "programCode": "BSC_CS",
      "programName": "BSc (Hons) Computer Science",
      "programSlug": "bsc-cs",
      "awardingUniversity": "University of Wolverhampton",
      "duration": "3 years",
      "isActive": true
    },
    {
      "id": 3,
      "programCode": "BENG_SE",
      "programName": "BEng (Hons) Software Engineering",
      "programSlug": "beng-se",
      "awardingUniversity": "University of Wolverhampton",
      "duration": "3 years",
      "isActive": true
    },
    {
      "id": 4,
      "programCode": "BSC_BDA",
      "programName": "BSc (Hons) Big Data Analytics",
      "programSlug": "bsc-bda",
      "awardingUniversity": "Robert Gordon University",
      "duration": "3 years",
      "isActive": true
    },
    {
      "id": 5,
      "programCode": "BSC_BIS",
      "programName": "BSc (Hons) Business Information Systems",
      "programSlug": "bsc-bis",
      "awardingUniversity": "Robert Gordon University",
      "duration": "3 years",
      "isActive": true
    },
    {
      "id": 6,
      "programCode": "BA_BM",
      "programName": "BA (Hons) Business Management",
      "programSlug": "ba-bm",
      "awardingUniversity": "Robert Gordon University",
      "duration": "3 years",
      "isActive": true
    },
    {
      "id": 7,
      "programCode": "BSC_BC",
      "programName": "BSc (Hons) Business Computing",
      "programSlug": "bsc-bc",
      "awardingUniversity": "Robert Gordon University",
      "duration": "3 years",
      "isActive": true
    }
  ],
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 3.2 Get BSc AI & Data Science Program Details

**GET** `/api/v1/undergraduate/bsc-ai-ds`

Returns full details of the BSc (Hons) Artificial Intelligence and Data Science program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 1,
    "programCode": "BSC_AI_DS",
    "programName": "BSc (Hons) Artificial Intelligence and Data Science",
    "programSlug": "bsc-ai-ds",
    "awardingUniversity": "University of Wolverhampton",
    "duration": "3 years",
    "totalCredits": 360,
    "description": "This programme provides a comprehensive foundation in Artificial Intelligence and Data Science, covering machine learning, deep learning, data mining, statistical analysis, and practical AI applications.",
    "entryRequirements": "Completion of IIT Foundation Programme or equivalent qualification. Minimum 3 A/L passes including Mathematics.",
    "careerProspects": [
      "AI Engineer",
      "Data Scientist",
      "Machine Learning Engineer",
      "Business Intelligence Analyst",
      "Research Scientist"
    ],
    "modules": [
      {
        "year": 1,
        "semester": 1,
        "moduleCode": "AI4001",
        "moduleName": "Introduction to Artificial Intelligence",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 1,
        "semester": 1,
        "moduleCode": "AI4002",
        "moduleName": "Programming Fundamentals with Python",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 1,
        "semester": 2,
        "moduleCode": "AI4003",
        "moduleName": "Data Structures and Algorithms",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 2,
        "semester": 1,
        "moduleCode": "AI5001",
        "moduleName": "Machine Learning",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 3,
        "semester": 1,
        "moduleCode": "AI6001",
        "moduleName": "Deep Learning and Neural Networks",
        "credits": 20,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/bsc-ai-ds-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/bsc-ai-ds-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 3.3 Get BSc Computer Science Program Details

**GET** `/api/v1/undergraduate/bsc-cs`

Returns full details of the BSc (Hons) Computer Science program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 2,
    "programCode": "BSC_CS",
    "programName": "BSc (Hons) Computer Science",
    "programSlug": "bsc-cs",
    "awardingUniversity": "University of Wolverhampton",
    "duration": "3 years",
    "totalCredits": 360,
    "description": "A comprehensive programme covering core computer science topics including software development, networking, databases, cybersecurity, and systems architecture.",
    "entryRequirements": "Completion of IIT Foundation Programme or equivalent qualification. Minimum 3 A/L passes including Mathematics.",
    "careerProspects": [
      "Software Developer",
      "Systems Architect",
      "Network Engineer",
      "Cybersecurity Analyst",
      "Full-Stack Developer"
    ],
    "modules": [
      {
        "year": 1,
        "semester": 1,
        "moduleCode": "CS4001",
        "moduleName": "Introduction to Computer Science",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 1,
        "semester": 1,
        "moduleCode": "CS4002",
        "moduleName": "Programming Fundamentals",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 2,
        "semester": 1,
        "moduleCode": "CS5001",
        "moduleName": "Database Systems",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 3,
        "semester": 1,
        "moduleCode": "CS6001",
        "moduleName": "Final Year Project",
        "credits": 40,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/bsc-cs-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/bsc-cs-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 3.4 Get BEng Software Engineering Program Details

**GET** `/api/v1/undergraduate/beng-se`

Returns full details of the BEng (Hons) Software Engineering program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 3,
    "programCode": "BENG_SE",
    "programName": "BEng (Hons) Software Engineering",
    "programSlug": "beng-se",
    "awardingUniversity": "University of Wolverhampton",
    "duration": "3 years",
    "totalCredits": 360,
    "description": "This engineering degree focuses on the design, development, testing, and maintenance of software systems. It covers software architecture, agile methodologies, DevOps, and project management.",
    "entryRequirements": "Completion of IIT Foundation Programme or equivalent qualification. Minimum 3 A/L passes including Mathematics.",
    "careerProspects": [
      "Software Engineer",
      "DevOps Engineer",
      "QA Engineer",
      "Technical Lead",
      "Project Manager"
    ],
    "modules": [
      {
        "year": 1,
        "semester": 1,
        "moduleCode": "SE4001",
        "moduleName": "Introduction to Software Engineering",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 1,
        "semester": 2,
        "moduleCode": "SE4002",
        "moduleName": "Object-Oriented Programming",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 2,
        "semester": 1,
        "moduleCode": "SE5001",
        "moduleName": "Software Design Patterns",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 3,
        "semester": 1,
        "moduleCode": "SE6001",
        "moduleName": "Software Engineering Group Project",
        "credits": 40,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/beng-se-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/beng-se-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 3.5 Get BSc Big Data Analytics Program Details

**GET** `/api/v1/undergraduate/bsc-bda`

Returns full details of the BSc (Hons) Big Data Analytics program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 4,
    "programCode": "BSC_BDA",
    "programName": "BSc (Hons) Big Data Analytics",
    "programSlug": "bsc-bda",
    "awardingUniversity": "Robert Gordon University",
    "duration": "3 years",
    "totalCredits": 360,
    "description": "This programme covers the theories, tools, and techniques for managing and analyzing large volumes of data including Hadoop, Spark, data warehousing, statistical analysis, and data visualization.",
    "entryRequirements": "Completion of IIT Foundation Programme or equivalent qualification. Minimum 3 A/L passes including Mathematics.",
    "careerProspects": [
      "Data Analyst",
      "Big Data Engineer",
      "Data Warehouse Developer",
      "Business Analyst",
      "Data Visualization Specialist"
    ],
    "modules": [
      {
        "year": 1,
        "semester": 1,
        "moduleCode": "BDA4001",
        "moduleName": "Introduction to Data Analytics",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 1,
        "semester": 2,
        "moduleCode": "BDA4002",
        "moduleName": "Statistics for Data Science",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 2,
        "semester": 1,
        "moduleCode": "BDA5001",
        "moduleName": "Big Data Technologies",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 3,
        "semester": 1,
        "moduleCode": "BDA6001",
        "moduleName": "Honours Project",
        "credits": 40,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/bsc-bda-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/bsc-bda-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 3.6 Get BSc Business Information Systems Program Details

**GET** `/api/v1/undergraduate/bsc-bis`

Returns full details of the BSc (Hons) Business Information Systems program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 5,
    "programCode": "BSC_BIS",
    "programName": "BSc (Hons) Business Information Systems",
    "programSlug": "bsc-bis",
    "awardingUniversity": "Robert Gordon University",
    "duration": "3 years",
    "totalCredits": 360,
    "description": "This programme combines business and IT skills, covering enterprise systems, information management, systems analysis, business process management, and IT strategy.",
    "entryRequirements": "Completion of IIT Foundation Programme or equivalent qualification. Minimum 3 A/L passes.",
    "careerProspects": [
      "Business Systems Analyst",
      "IT Consultant",
      "ERP Specialist",
      "Information Systems Manager",
      "Digital Transformation Analyst"
    ],
    "modules": [
      {
        "year": 1,
        "semester": 1,
        "moduleCode": "BIS4001",
        "moduleName": "Introduction to Business Information Systems",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 1,
        "semester": 2,
        "moduleCode": "BIS4002",
        "moduleName": "Systems Analysis and Design",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 2,
        "semester": 1,
        "moduleCode": "BIS5001",
        "moduleName": "Enterprise Resource Planning",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 3,
        "semester": 1,
        "moduleCode": "BIS6001",
        "moduleName": "Honours Project",
        "credits": 40,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/bsc-bis-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/bsc-bis-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 3.7 Get BA Business Management Program Details

**GET** `/api/v1/undergraduate/ba-bm`

Returns full details of the BA (Hons) Business Management program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 6,
    "programCode": "BA_BM",
    "programName": "BA (Hons) Business Management",
    "programSlug": "ba-bm",
    "awardingUniversity": "Robert Gordon University",
    "duration": "3 years",
    "totalCredits": 360,
    "description": "This programme provides a broad understanding of business management principles including marketing, finance, operations management, strategic management, and human resource management.",
    "entryRequirements": "Completion of IIT Foundation Programme or equivalent qualification. Minimum 3 A/L passes.",
    "careerProspects": [
      "Business Manager",
      "Marketing Executive",
      "Operations Manager",
      "HR Manager",
      "Entrepreneur"
    ],
    "modules": [
      {
        "year": 1,
        "semester": 1,
        "moduleCode": "BM4001",
        "moduleName": "Principles of Management",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 1,
        "semester": 2,
        "moduleCode": "BM4002",
        "moduleName": "Business Economics",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 2,
        "semester": 1,
        "moduleCode": "BM5001",
        "moduleName": "Strategic Management",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 3,
        "semester": 1,
        "moduleCode": "BM6001",
        "moduleName": "Business Management Project",
        "credits": 40,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/ba-bm-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/ba-bm-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 3.8 Get BSc Business Computing Program Details

**GET** `/api/v1/undergraduate/bsc-bc`

Returns full details of the BSc (Hons) Business Computing program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 7,
    "programCode": "BSC_BC",
    "programName": "BSc (Hons) Business Computing",
    "programSlug": "bsc-bc",
    "awardingUniversity": "Robert Gordon University",
    "duration": "3 years",
    "totalCredits": 360,
    "description": "This programme bridges business and computing, covering web development, database management, cloud computing, IT project management, and business process automation.",
    "entryRequirements": "Completion of IIT Foundation Programme or equivalent qualification. Minimum 3 A/L passes.",
    "careerProspects": [
      "Business Computing Analyst",
      "IT Project Manager",
      "Web Developer",
      "Cloud Solutions Architect",
      "Systems Administrator"
    ],
    "modules": [
      {
        "year": 1,
        "semester": 1,
        "moduleCode": "BC4001",
        "moduleName": "Introduction to Business Computing",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 1,
        "semester": 2,
        "moduleCode": "BC4002",
        "moduleName": "Web Development Fundamentals",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 2,
        "semester": 1,
        "moduleCode": "BC5001",
        "moduleName": "Cloud Computing",
        "credits": 20,
        "isCore": true
      },
      {
        "year": 3,
        "semester": 1,
        "moduleCode": "BC6001",
        "moduleName": "Honours Project",
        "credits": 40,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/bsc-bc-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/bsc-bc-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

## 4. Postgraduate Programs

### 4.1 Get All Postgraduate Programs

**GET** `/api/v1/postgraduate`

Returns a list of all postgraduate degree programs.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": [
    {
      "id": 1,
      "programCode": "MSC_ASE",
      "programName": "MSc Advanced Software Engineering",
      "programSlug": "msc-ase",
      "awardingUniversity": "University of Wolverhampton",
      "duration": "1 year",
      "isActive": true
    },
    {
      "id": 2,
      "programCode": "MSC_CS_F",
      "programName": "MSc Cyber Security and Forensics",
      "programSlug": "msc-cs-f",
      "awardingUniversity": "University of Wolverhampton",
      "duration": "1 year",
      "isActive": true
    },
    {
      "id": 3,
      "programCode": "MSC_IT",
      "programName": "MSc Information Technology",
      "programSlug": "msc-it",
      "awardingUniversity": "University of Wolverhampton",
      "duration": "1 year",
      "isActive": true
    },
    {
      "id": 4,
      "programCode": "MSC_BDA",
      "programName": "MSc Big Data Analytics",
      "programSlug": "msc-bda",
      "awardingUniversity": "Robert Gordon University",
      "duration": "1 year",
      "isActive": true
    },
    {
      "id": 5,
      "programCode": "MSC_BA",
      "programName": "MSc Business Analytics",
      "programSlug": "msc-ba",
      "awardingUniversity": "Robert Gordon University",
      "duration": "1 year",
      "isActive": true
    },
    {
      "id": 6,
      "programCode": "MSC_FBM",
      "programName": "MSc Finance and Business Management",
      "programSlug": "msc-fbm",
      "awardingUniversity": "Robert Gordon University",
      "duration": "1 year",
      "isActive": true
    }
  ],
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 4.2 Get MSc Advanced Software Engineering Program Details

**GET** `/api/v1/postgraduate/msc-ase`

Returns full details of the MSc Advanced Software Engineering program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 1,
    "programCode": "MSC_ASE",
    "programName": "MSc Advanced Software Engineering",
    "programSlug": "msc-ase",
    "awardingUniversity": "University of Wolverhampton",
    "duration": "1 year",
    "totalCredits": 180,
    "description": "This programme provides advanced knowledge in software engineering including cloud-native architecture, microservices, containerization, CI/CD pipelines, and agile project management.",
    "entryRequirements": "A recognised Bachelor's degree in Computing, IT, or related discipline with minimum 2:2 classification or equivalent.",
    "careerProspects": [
      "Senior Software Engineer",
      "Solutions Architect",
      "Technical Lead",
      "Cloud Architect",
      "Engineering Manager"
    ],
    "modules": [
      {
        "semester": 1,
        "moduleCode": "ASE7001",
        "moduleName": "Advanced Software Architecture",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 1,
        "moduleCode": "ASE7002",
        "moduleName": "Cloud-Native Development",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 2,
        "moduleCode": "ASE7003",
        "moduleName": "DevOps and Continuous Delivery",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 3,
        "moduleCode": "ASE7004",
        "moduleName": "Masters Dissertation",
        "credits": 60,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/msc-ase-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/msc-ase-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 4.3 Get MSc Cyber Security & Forensics Program Details

**GET** `/api/v1/postgraduate/msc-cs-f`

Returns full details of the MSc Cyber Security and Forensics program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 2,
    "programCode": "MSC_CS_F",
    "programName": "MSc Cyber Security and Forensics",
    "programSlug": "msc-cs-f",
    "awardingUniversity": "University of Wolverhampton",
    "duration": "1 year",
    "totalCredits": 180,
    "description": "This programme covers cybersecurity fundamentals, digital forensics, penetration testing, network security, incident response, and security governance. It prepares graduates for roles in cybersecurity and forensic investigation.",
    "entryRequirements": "A recognised Bachelor's degree in Computing, IT, or related discipline with minimum 2:2 classification or equivalent.",
    "careerProspects": [
      "Cybersecurity Analyst",
      "Digital Forensics Investigator",
      "Penetration Tester",
      "Security Consultant",
      "Chief Information Security Officer (CISO)"
    ],
    "modules": [
      {
        "semester": 1,
        "moduleCode": "CSF7001",
        "moduleName": "Cybersecurity Fundamentals",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 1,
        "moduleCode": "CSF7002",
        "moduleName": "Digital Forensics",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 2,
        "moduleCode": "CSF7003",
        "moduleName": "Ethical Hacking and Penetration Testing",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 3,
        "moduleCode": "CSF7004",
        "moduleName": "Masters Dissertation",
        "credits": 60,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/msc-cs-f-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/msc-cs-f-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 4.4 Get MSc Information Technology Program Details

**GET** `/api/v1/postgraduate/msc-it`

Returns full details of the MSc Information Technology program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 3,
    "programCode": "MSC_IT",
    "programName": "MSc Information Technology",
    "programSlug": "msc-it",
    "awardingUniversity": "University of Wolverhampton",
    "duration": "1 year",
    "totalCredits": 180,
    "description": "This programme covers advanced IT management, enterprise systems, IT strategy, emerging technologies, and research methodologies for the modern information technology landscape.",
    "entryRequirements": "A recognised Bachelor's degree in Computing, IT, Business, or related discipline with minimum 2:2 classification or equivalent.",
    "careerProspects": [
      "IT Manager",
      "Enterprise Architect",
      "Technology Consultant",
      "IT Strategy Director",
      "CTO"
    ],
    "modules": [
      {
        "semester": 1,
        "moduleCode": "IT7001",
        "moduleName": "Advanced IT Management",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 1,
        "moduleCode": "IT7002",
        "moduleName": "Enterprise Architecture",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 2,
        "moduleCode": "IT7003",
        "moduleName": "Emerging Technologies",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 3,
        "moduleCode": "IT7004",
        "moduleName": "Masters Dissertation",
        "credits": 60,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/msc-it-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/msc-it-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 4.5 Get MSc Big Data Analytics Program Details

**GET** `/api/v1/postgraduate/msc-bda`

Returns full details of the MSc Big Data Analytics program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 4,
    "programCode": "MSC_BDA",
    "programName": "MSc Big Data Analytics",
    "programSlug": "msc-bda",
    "awardingUniversity": "Robert Gordon University",
    "duration": "1 year",
    "totalCredits": 180,
    "description": "This programme provides advanced knowledge in big data technologies, advanced analytics, distributed computing, real-time data processing, and predictive modelling for enterprise decision-making.",
    "entryRequirements": "A recognised Bachelor's degree in Computing, IT, Mathematics, Statistics, or related discipline with minimum 2:2 classification or equivalent.",
    "careerProspects": [
      "Senior Data Engineer",
      "Big Data Architect",
      "Data Science Lead",
      "Analytics Manager",
      "Machine Learning Engineer"
    ],
    "modules": [
      {
        "semester": 1,
        "moduleCode": "MBDA7001",
        "moduleName": "Advanced Big Data Technologies",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 1,
        "moduleCode": "MBDA7002",
        "moduleName": "Distributed Computing",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 2,
        "moduleCode": "MBDA7003",
        "moduleName": "Predictive Analytics",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 3,
        "moduleCode": "MBDA7004",
        "moduleName": "Masters Dissertation",
        "credits": 60,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/msc-bda-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/msc-bda-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 4.6 Get MSc Business Analytics Program Details

**GET** `/api/v1/postgraduate/msc-ba`

Returns full details of the MSc Business Analytics program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 5,
    "programCode": "MSC_BA",
    "programName": "MSc Business Analytics",
    "programSlug": "msc-ba",
    "awardingUniversity": "Robert Gordon University",
    "duration": "1 year",
    "totalCredits": 180,
    "description": "This programme combines business knowledge with analytical skills, covering business intelligence, data-driven decision making, statistical modelling, and analytics strategy.",
    "entryRequirements": "A recognised Bachelor's degree in Business, IT, Mathematics, or related discipline with minimum 2:2 classification or equivalent.",
    "careerProspects": [
      "Business Analytics Manager",
      "BI Developer",
      "Data Strategy Consultant",
      "Product Analyst",
      "Decision Science Manager"
    ],
    "modules": [
      {
        "semester": 1,
        "moduleCode": "MBA7001",
        "moduleName": "Business Intelligence and Reporting",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 1,
        "moduleCode": "MBA7002",
        "moduleName": "Statistical Modelling for Business",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 2,
        "moduleCode": "MBA7003",
        "moduleName": "Data-Driven Decision Making",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 3,
        "moduleCode": "MBA7004",
        "moduleName": "Masters Dissertation",
        "credits": 60,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/msc-ba-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/msc-ba-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 4.7 Get MSc Finance and Business Management Program Details

**GET** `/api/v1/postgraduate/msc-fbm`

Returns full details of the MSc Finance and Business Management program.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 6,
    "programCode": "MSC_FBM",
    "programName": "MSc Finance and Business Management",
    "programSlug": "msc-fbm",
    "awardingUniversity": "Robert Gordon University",
    "duration": "1 year",
    "totalCredits": 180,
    "description": "This programme provides advanced knowledge in financial management, corporate finance, investment analysis, risk management, and strategic business management.",
    "entryRequirements": "A recognised Bachelor's degree in Business, Finance, Accounting, Economics, or related discipline with minimum 2:2 classification or equivalent.",
    "careerProspects": [
      "Financial Analyst",
      "Investment Banker",
      "Risk Manager",
      "Corporate Finance Manager",
      "Management Consultant"
    ],
    "modules": [
      {
        "semester": 1,
        "moduleCode": "FBM7001",
        "moduleName": "Corporate Finance",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 1,
        "moduleCode": "FBM7002",
        "moduleName": "Investment Analysis",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 2,
        "moduleCode": "FBM7003",
        "moduleName": "Risk Management",
        "credits": 20,
        "isCore": true
      },
      {
        "semester": 3,
        "moduleCode": "FBM7004",
        "moduleName": "Masters Dissertation",
        "credits": 60,
        "isCore": true
      }
    ],
    "programSpecificationUrl": "https://storage.example.com/specs/msc-fbm-spec.pdf",
    "handbookUrl": "https://storage.example.com/handbooks/msc-fbm-handbook.pdf",
    "isActive": true,
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

## 5. Foundation Program

### 5.1 Get All Foundation Program Information

**GET** `/api/v1/foundation-program`

Returns a list of all foundation program information categories.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": [
    {
      "id": 1,
      "categoryName": "Academic Calendar",
      "categorySlug": "academic-calendar",
      "description": "Foundation programme academic calendar and important dates"
    },
    {
      "id": 2,
      "categoryName": "Program Specification",
      "categorySlug": "program-specification",
      "description": "Foundation programme specification document"
    },
    {
      "id": 3,
      "categoryName": "Important Contact Details",
      "categorySlug": "important-contact-details",
      "description": "Key contacts for the foundation programme"
    },
    {
      "id": 4,
      "categoryName": "Time Table",
      "categorySlug": "time-table",
      "description": "Foundation programme weekly timetable"
    },
    {
      "id": 5,
      "categoryName": "Assessment Schedule",
      "categorySlug": "assessment-schedule",
      "description": "Assessment dates and submission deadlines"
    },
    {
      "id": 6,
      "categoryName": "LMS Login Details",
      "categorySlug": "lms-login-details",
      "description": "Learning Management System login information"
    },
    {
      "id": 7,
      "categoryName": "Foundation Mitigating Circumstances Form",
      "categorySlug": "mitigating-circumstances-form",
      "description": "Mitigating circumstances form for foundation students"
    }
  ],
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 5.2 Get Foundation Program Academic Calendar

**GET** `/api/v1/foundation-program/academic-calendar`

Returns the foundation programme academic calendar.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 1,
    "categoryName": "Academic Calendar",
    "academicYear": "2025/2026",
    "calendarFileUrl": "https://storage.example.com/foundation/academic-calendar-2025-2026.pdf",
    "events": [
      {
        "eventName": "Orientation Week",
        "startDate": "2025-09-08",
        "endDate": "2025-09-12",
        "eventType": "ORIENTATION"
      },
      {
        "eventName": "Semester 1 Lectures Begin",
        "startDate": "2025-09-15",
        "endDate": "2025-09-15",
        "eventType": "SEMESTER_START"
      },
      {
        "eventName": "Mid-Term Assessment Week",
        "startDate": "2025-11-10",
        "endDate": "2025-11-14",
        "eventType": "ASSESSMENT"
      },
      {
        "eventName": "Semester 1 Final Exams",
        "startDate": "2026-01-19",
        "endDate": "2026-01-30",
        "eventType": "EXAM_PERIOD"
      },
      {
        "eventName": "Semester 2 Lectures Begin",
        "startDate": "2026-02-17",
        "endDate": "2026-02-17",
        "eventType": "SEMESTER_START"
      },
      {
        "eventName": "Semester 2 Final Exams",
        "startDate": "2026-06-08",
        "endDate": "2026-06-19",
        "eventType": "EXAM_PERIOD"
      }
    ],
    "lastUpdated": "2026-01-10T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 5.3 Get Foundation Program Specification

**GET** `/api/v1/foundation-program/program-specification`

Returns the foundation programme specification document details.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 2,
    "categoryName": "Program Specification",
    "programName": "IIT Foundation Programme in Computing and Business",
    "duration": "1 year (2 semesters)",
    "description": "The IIT Foundation Programme prepares students for entry into undergraduate degree programmes offered by partner universities. It covers fundamental concepts in computing, mathematics, English, and business studies.",
    "specificationFileUrl": "https://storage.example.com/foundation/program-spec-2025-2026.pdf",
    "modules": [
      {
        "moduleCode": "FP001",
        "moduleName": "Mathematics for Computing",
        "credits": 15,
        "semester": 1
      },
      {
        "moduleCode": "FP002",
        "moduleName": "Introduction to Programming",
        "credits": 15,
        "semester": 1
      },
      {
        "moduleCode": "FP003",
        "moduleName": "English for Academic Purposes",
        "credits": 15,
        "semester": 1
      },
      {
        "moduleCode": "FP004",
        "moduleName": "Introduction to Business",
        "credits": 15,
        "semester": 2
      },
      {
        "moduleCode": "FP005",
        "moduleName": "Web Development Basics",
        "credits": 15,
        "semester": 2
      },
      {
        "moduleCode": "FP006",
        "moduleName": "Study Skills and Professional Development",
        "credits": 15,
        "semester": 2
      }
    ],
    "totalCredits": 90,
    "lastUpdated": "2026-01-10T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 5.4 Get Foundation Program Important Contact Details

**GET** `/api/v1/foundation-program/important-contact-details`

Returns important contact details for the foundation programme.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 3,
    "categoryName": "Important Contact Details",
    "contacts": [
      {
        "role": "Foundation Programme Director",
        "name": "Dr. Samantha Perera",
        "email": "samantha.perera@iit.ac.lk",
        "phone": "+94 11 234 5680",
        "officeHours": "Mon-Fri 9:00 AM - 4:00 PM",
        "office": "Room A-201, Main Building"
      },
      {
        "role": "Foundation Programme Coordinator",
        "name": "Ms. Nishadi Fernando",
        "email": "nishadi.fernando@iit.ac.lk",
        "phone": "+94 11 234 5681",
        "officeHours": "Mon-Fri 8:30 AM - 4:30 PM",
        "office": "Room A-202, Main Building"
      },
      {
        "role": "Student Counselor",
        "name": "Mr. Kasun Jayasinghe",
        "email": "kasun.jayasinghe@iit.ac.lk",
        "phone": "+94 11 234 5682",
        "officeHours": "Mon-Fri 9:00 AM - 5:00 PM",
        "office": "Room B-105, Student Services"
      },
      {
        "role": "IT Support",
        "name": "IT Help Desk",
        "email": "ithelpdesk@iit.ac.lk",
        "phone": "+94 11 234 5683",
        "officeHours": "Mon-Fri 8:00 AM - 6:00 PM",
        "office": "Room C-001, IT Building"
      },
      {
        "role": "Library",
        "name": "IIT Library",
        "email": "library@iit.ac.lk",
        "phone": "+94 11 234 5684",
        "officeHours": "Mon-Fri 8:00 AM - 8:00 PM, Sat 9:00 AM - 1:00 PM",
        "office": "Ground Floor, Main Building"
      }
    ],
    "lastUpdated": "2026-01-10T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 5.5 Get Foundation Program Time Table

**GET** `/api/v1/foundation-program/time-table`

Returns the current foundation programme timetable.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 4,
    "categoryName": "Time Table",
    "semester": "Semester 2",
    "academicYear": "2025/2026",
    "effectiveFrom": "2026-02-17",
    "timetableFileUrl": "https://storage.example.com/foundation/timetable-sem2-2025-2026.pdf",
    "schedule": [
      {
        "day": "Monday",
        "slots": [
          {
            "startTime": "09:00",
            "endTime": "11:00",
            "moduleCode": "FP004",
            "moduleName": "Introduction to Business",
            "lecturer": "Mr. Rohan Silva",
            "venue": "Lecture Hall A"
          },
          {
            "startTime": "13:00",
            "endTime": "15:00",
            "moduleCode": "FP005",
            "moduleName": "Web Development Basics",
            "lecturer": "Ms. Ayesha Rahim",
            "venue": "Lab 3"
          }
        ]
      },
      {
        "day": "Tuesday",
        "slots": [
          {
            "startTime": "09:00",
            "endTime": "11:00",
            "moduleCode": "FP006",
            "moduleName": "Study Skills and Professional Development",
            "lecturer": "Dr. Lakshmi Nair",
            "venue": "Lecture Hall B"
          },
          {
            "startTime": "14:00",
            "endTime": "16:00",
            "moduleCode": "FP004",
            "moduleName": "Introduction to Business (Tutorial)",
            "lecturer": "Mr. Rohan Silva",
            "venue": "Tutorial Room 2"
          }
        ]
      },
      {
        "day": "Wednesday",
        "slots": [
          {
            "startTime": "10:00",
            "endTime": "12:00",
            "moduleCode": "FP005",
            "moduleName": "Web Development Basics (Lab)",
            "lecturer": "Ms. Ayesha Rahim",
            "venue": "Lab 3"
          }
        ]
      },
      {
        "day": "Thursday",
        "slots": [
          {
            "startTime": "09:00",
            "endTime": "11:00",
            "moduleCode": "FP006",
            "moduleName": "Study Skills and Professional Development (Workshop)",
            "lecturer": "Dr. Lakshmi Nair",
            "venue": "Seminar Room 1"
          },
          {
            "startTime": "13:00",
            "endTime": "15:00",
            "moduleCode": "FP005",
            "moduleName": "Web Development Basics",
            "lecturer": "Ms. Ayesha Rahim",
            "venue": "Lecture Hall A"
          }
        ]
      },
      {
        "day": "Friday",
        "slots": [
          {
            "startTime": "09:00",
            "endTime": "11:00",
            "moduleCode": "FP004",
            "moduleName": "Introduction to Business",
            "lecturer": "Mr. Rohan Silva",
            "venue": "Lecture Hall B"
          }
        ]
      }
    ],
    "lastUpdated": "2026-02-10T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 5.6 Get Foundation Program Assessment Schedule

**GET** `/api/v1/foundation-program/assessment-schedule`

Returns the foundation programme assessment schedule.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 5,
    "categoryName": "Assessment Schedule",
    "semester": "Semester 2",
    "academicYear": "2025/2026",
    "scheduleFileUrl": "https://storage.example.com/foundation/assessment-schedule-sem2-2025-2026.pdf",
    "assessments": [
      {
        "moduleCode": "FP004",
        "moduleName": "Introduction to Business",
        "assessmentType": "Coursework 1",
        "description": "Business Case Study Analysis",
        "weightPercentage": 30,
        "releaseDate": "2026-03-03",
        "submissionDeadline": "2026-03-24T23:59:00",
        "feedbackDate": "2026-04-07"
      },
      {
        "moduleCode": "FP004",
        "moduleName": "Introduction to Business",
        "assessmentType": "Final Examination",
        "description": "Written Exam (2 hours)",
        "weightPercentage": 70,
        "releaseDate": null,
        "submissionDeadline": "2026-06-10T09:00:00",
        "feedbackDate": "2026-07-01"
      },
      {
        "moduleCode": "FP005",
        "moduleName": "Web Development Basics",
        "assessmentType": "Coursework 1",
        "description": "Personal Portfolio Website",
        "weightPercentage": 40,
        "releaseDate": "2026-02-24",
        "submissionDeadline": "2026-03-21T23:59:00",
        "feedbackDate": "2026-04-04"
      },
      {
        "moduleCode": "FP005",
        "moduleName": "Web Development Basics",
        "assessmentType": "Coursework 2",
        "description": "Dynamic Web Application Project",
        "weightPercentage": 60,
        "releaseDate": "2026-04-07",
        "submissionDeadline": "2026-05-16T23:59:00",
        "feedbackDate": "2026-05-30"
      },
      {
        "moduleCode": "FP006",
        "moduleName": "Study Skills and Professional Development",
        "assessmentType": "Coursework 1",
        "description": "Reflective Portfolio",
        "weightPercentage": 50,
        "releaseDate": "2026-02-24",
        "submissionDeadline": "2026-04-04T23:59:00",
        "feedbackDate": "2026-04-18"
      },
      {
        "moduleCode": "FP006",
        "moduleName": "Study Skills and Professional Development",
        "assessmentType": "Presentation",
        "description": "Professional Development Presentation",
        "weightPercentage": 50,
        "releaseDate": "2026-04-21",
        "submissionDeadline": "2026-05-23T23:59:00",
        "feedbackDate": "2026-06-06"
      }
    ],
    "lastUpdated": "2026-02-10T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 5.7 Get Foundation Program LMS Login Details

**GET** `/api/v1/foundation-program/lms-login-details`

Returns LMS login instructions for foundation programme students.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 6,
    "categoryName": "LMS Login Details",
    "lmsName": "Moodle LMS",
    "lmsUrl": "https://lms.iit.ac.lk",
    "loginInstructions": "Use your IIT student email as your username and the default password provided during orientation. You will be prompted to change your password on first login.",
    "usernameFormat": "studentid@students.iit.ac.lk",
    "defaultPasswordInfo": "Your default password was sent to your personal email during registration. If you have not received it, contact IT Help Desk.",
    "passwordResetUrl": "https://lms.iit.ac.lk/login/forgot_password.php",
    "supportContact": {
      "name": "IT Help Desk",
      "email": "ithelpdesk@iit.ac.lk",
      "phone": "+94 11 234 5683"
    },
    "browserRequirements": [
      "Google Chrome (latest version) - Recommended",
      "Mozilla Firefox (latest version)",
      "Microsoft Edge (latest version)",
      "Safari (latest version)"
    ],
    "additionalNotes": "Ensure your browser has JavaScript and cookies enabled. For the best experience, use a laptop or desktop computer for assessments and quizzes.",
    "lastUpdated": "2026-01-10T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 5.8 Get Foundation Mitigating Circumstances Form

**GET** `/api/v1/foundation-program/mitigating-circumstances-form`

Returns the foundation programme mitigating circumstances form details.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 7,
    "categoryName": "Foundation Mitigating Circumstances Form",
    "formName": "IIT Foundation Programme - Mitigating Circumstances Claim Form",
    "description": "This form should be completed if you have experienced circumstances beyond your control that have adversely affected your academic performance or ability to submit assessments on time.",
    "formFileUrl": "https://storage.example.com/foundation/mitigating-circumstances-form.pdf",
    "submissionEmail": "foundation.mitigation@iit.ac.lk",
    "submissionDeadline": "Within 5 working days of the affected assessment deadline",
    "eligibleCircumstances": [
      "Serious illness or injury (medical certificate required)",
      "Bereavement of a close family member",
      "Significant personal/family crisis",
      "Victim of crime",
      "Other exceptional circumstances beyond your control"
    ],
    "requiredEvidence": [
      "Completed Mitigating Circumstances Form",
      "Supporting evidence (e.g., medical certificate, death certificate, police report)",
      "Personal statement explaining the impact on your studies"
    ],
    "contactPerson": {
      "name": "Ms. Nishadi Fernando",
      "role": "Foundation Programme Coordinator",
      "email": "nishadi.fernando@iit.ac.lk",
      "phone": "+94 11 234 5681"
    },
    "lastUpdated": "2026-01-10T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

## 6. Students Relations Unit

### 6.1 Get Students Relations Unit Information

**GET** `/api/v1/students-relations-unit`

Returns information about the Students Relations Unit.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "unitName": "Students Relations Unit",
    "description": "The Students Relations Unit is dedicated to supporting students throughout their academic journey at IIT. We handle student queries, grievances, welfare, and facilitate communication between students and the institution.",
    "location": "Student Services Building, Ground Floor",
    "email": "sru@iit.ac.lk",
    "phone": "+94 11 234 5690",
    "officeHours": "Mon-Fri 8:30 AM - 5:00 PM",
    "categories": [
      {
        "categoryName": "Help Desk Video Series",
        "categorySlug": "help-desk-video-series",
        "description": "Video tutorials and guides to help students with common queries"
      }
    ]
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 6.2 Get Help Desk Video Series

**GET** `/api/v1/students-relations-unit/help-desk-video-series`

Returns the list of help desk video series.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "categoryName": "Help Desk Video Series",
    "description": "A collection of video tutorials created by the Students Relations Unit to guide students through common processes and queries.",
    "videos": [
      {
        "id": 1,
        "title": "How to Register for Modules",
        "description": "Step-by-step guide on module registration through the student portal.",
        "videoUrl": "https://storage.example.com/videos/module-registration.mp4",
        "thumbnailUrl": "https://storage.example.com/thumbnails/module-registration.jpg",
        "duration": "05:32",
        "publishedDate": "2026-01-15"
      },
      {
        "id": 2,
        "title": "Accessing the LMS",
        "description": "How to log in and navigate the Learning Management System.",
        "videoUrl": "https://storage.example.com/videos/lms-access.mp4",
        "thumbnailUrl": "https://storage.example.com/thumbnails/lms-access.jpg",
        "duration": "04:18",
        "publishedDate": "2026-01-15"
      },
      {
        "id": 3,
        "title": "How to Submit an Assignment",
        "description": "Guide on submitting assignments through the LMS.",
        "videoUrl": "https://storage.example.com/videos/assignment-submission.mp4",
        "thumbnailUrl": "https://storage.example.com/thumbnails/assignment-submission.jpg",
        "duration": "03:45",
        "publishedDate": "2026-01-20"
      },
      {
        "id": 4,
        "title": "Requesting a Transcript",
        "description": "How to request official transcripts and other academic documents.",
        "videoUrl": "https://storage.example.com/videos/transcript-request.mp4",
        "thumbnailUrl": "https://storage.example.com/thumbnails/transcript-request.jpg",
        "duration": "03:10",
        "publishedDate": "2026-01-25"
      },
      {
        "id": 5,
        "title": "Student ID Card Replacement",
        "description": "Process for replacing a lost or damaged student ID card.",
        "videoUrl": "https://storage.example.com/videos/id-card-replacement.mp4",
        "thumbnailUrl": "https://storage.example.com/thumbnails/id-card-replacement.jpg",
        "duration": "02:55",
        "publishedDate": "2026-02-01"
      },
      {
        "id": 6,
        "title": "Filing a Mitigating Circumstances Claim",
        "description": "How to complete and submit a mitigating circumstances form.",
        "videoUrl": "https://storage.example.com/videos/mitigation-claim.mp4",
        "thumbnailUrl": "https://storage.example.com/thumbnails/mitigation-claim.jpg",
        "duration": "06:20",
        "publishedDate": "2026-02-05"
      }
    ],
    "lastUpdated": "2026-02-05T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

## 7. Student Policies

### 7.1 Get All Student Policies

**GET** `/api/v1/student-policies`

Returns a list of all student policies.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": [
    {
      "id": 1,
      "policyName": "Participation at Conferences",
      "policySlug": "participation-at-conferences",
      "description": "Guidelines for student participation in academic and professional conferences"
    },
    {
      "id": 2,
      "policyName": "Participation at Competitions",
      "policySlug": "participation-at-competitions",
      "description": "Guidelines for student participation in competitions and hackathons"
    },
    {
      "id": 3,
      "policyName": "Code of Conduct",
      "policySlug": "code-of-conduct",
      "description": "Student code of conduct and behavioral expectations"
    },
    {
      "id": 4,
      "policyName": "Club Policy",
      "policySlug": "club-policy",
      "description": "Policies governing student clubs and societies"
    },
    {
      "id": 5,
      "policyName": "IT Policy",
      "policySlug": "it-policy",
      "description": "Information technology acceptable use policy"
    }
  ],
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 7.2 Get Participation at Conferences Policy

**GET** `/api/v1/student-policies/participation-at-conferences`

Returns the full participation at conferences policy.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 1,
    "policyName": "Participation at Conferences",
    "policySlug": "participation-at-conferences",
    "version": "2.0",
    "effectiveDate": "2025-09-01",
    "description": "This policy outlines the guidelines and procedures for IIT students who wish to participate in or present at academic and professional conferences.",
    "policyContent": "Students are encouraged to participate in academic conferences to enhance their learning experience. Students must obtain prior approval from their programme coordinator at least 2 weeks before the event. Conference participation during lecture hours will be considered an authorized absence if approved. Students may apply for financial support through the Student Development Fund for conference registration fees.",
    "policyFileUrl": "https://storage.example.com/policies/participation-at-conferences-v2.pdf",
    "keyPoints": [
      "Prior approval required from Programme Coordinator",
      "Application must be submitted at least 2 weeks before the event",
      "Financial support available through Student Development Fund",
      "Conference participation during lecture hours counts as authorized absence if approved",
      "Students must submit a brief report within 1 week after the conference"
    ],
    "contactPerson": {
      "name": "Dr. Amila Gunasekera",
      "role": "Head of Student Development",
      "email": "amila.gunasekera@iit.ac.lk"
    },
    "lastUpdated": "2025-08-20T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 7.3 Get Participation at Competitions Policy

**GET** `/api/v1/student-policies/participation-at-competitions`

Returns the full participation at competitions policy.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 2,
    "policyName": "Participation at Competitions",
    "policySlug": "participation-at-competitions",
    "version": "2.1",
    "effectiveDate": "2025-09-01",
    "description": "This policy outlines the guidelines and procedures for IIT students who wish to participate in competitions, hackathons, coding challenges, and other competitive events.",
    "policyContent": "IIT encourages students to participate in local and international competitions. Students must register their participation with the Students Relations Unit. Team-based competitions require a faculty advisor. Competition participation during lecture hours requires prior approval and is treated as an authorized absence.",
    "policyFileUrl": "https://storage.example.com/policies/participation-at-competitions-v2.1.pdf",
    "keyPoints": [
      "Register participation with Students Relations Unit",
      "Team competitions require a designated faculty advisor",
      "Prior approval needed for events during lecture hours",
      "Travel and registration sponsorship available for qualifying competitions",
      "Winners may be recognized at the Annual Awards Ceremony"
    ],
    "contactPerson": {
      "name": "Dr. Amila Gunasekera",
      "role": "Head of Student Development",
      "email": "amila.gunasekera@iit.ac.lk"
    },
    "lastUpdated": "2025-08-20T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 7.4 Get Code of Conduct Policy

**GET** `/api/v1/student-policies/code-of-conduct`

Returns the full student code of conduct.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 3,
    "policyName": "Code of Conduct",
    "policySlug": "code-of-conduct",
    "version": "3.0",
    "effectiveDate": "2025-09-01",
    "description": "The Student Code of Conduct outlines the standards of behavior expected of all IIT students both on campus and in any IIT-related activity.",
    "policyContent": "All students are expected to maintain high standards of academic integrity and personal conduct. This includes respecting fellow students, staff, and institutional property. Violations may result in disciplinary action including warnings, suspension, or expulsion.",
    "policyFileUrl": "https://storage.example.com/policies/code-of-conduct-v3.pdf",
    "keyPoints": [
      "Maintain academic integrity – plagiarism and cheating are strictly prohibited",
      "Respect all members of the IIT community",
      "Dress code must be followed on campus premises",
      "Harassment, bullying, and discrimination are not tolerated",
      "Responsible use of all campus facilities and equipment",
      "Compliance with Sri Lankan laws and regulations",
      "Disciplinary procedures apply for violations"
    ],
    "disciplinaryProcess": [
      "First offense: Written warning",
      "Second offense: Meeting with Disciplinary Committee",
      "Third offense: Suspension (duration determined by severity)",
      "Severe violations: Immediate expulsion possible"
    ],
    "contactPerson": {
      "name": "Mr. Dinesh Rathnayake",
      "role": "Dean of Students",
      "email": "dinesh.rathnayake@iit.ac.lk"
    },
    "lastUpdated": "2025-08-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 7.5 Get Club Policy

**GET** `/api/v1/student-policies/club-policy`

Returns the full club policy.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 4,
    "policyName": "Club Policy",
    "policySlug": "club-policy",
    "version": "2.0",
    "effectiveDate": "2025-09-01",
    "description": "This policy governs the establishment, operation, and management of student clubs and societies at IIT.",
    "policyContent": "Student clubs and societies play an important role in campus life. All clubs must be registered with the Students' Union and have an appointed faculty advisor. Club activities must align with the institution's values and must not interfere with academic commitments.",
    "policyFileUrl": "https://storage.example.com/policies/club-policy-v2.pdf",
    "keyPoints": [
      "All clubs must register with the Students' Union",
      "Minimum 15 members required to form a new club",
      "Each club must have a faculty advisor",
      "Annual activity plan must be submitted by October 15",
      "Club finances must be audited by the Students' Union Treasurer",
      "Club events require prior approval from the Events Committee",
      "Elections for club office bearers must follow the Election Policy"
    ],
    "contactPerson": {
      "name": "Ms. Thilini Weerasinghe",
      "role": "Students' Union Coordinator",
      "email": "thilini.weerasinghe@iit.ac.lk"
    },
    "lastUpdated": "2025-08-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 7.6 Get IT Policy

**GET** `/api/v1/student-policies/it-policy`

Returns the full IT policy.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 5,
    "policyName": "IT Policy",
    "policySlug": "it-policy",
    "version": "4.0",
    "effectiveDate": "2025-09-01",
    "description": "This policy governs the acceptable use of IT facilities, network, and digital services provided by IIT to students.",
    "policyContent": "Students are granted access to IIT IT resources for academic and research purposes. Use of these resources must comply with this policy. Unauthorized access, hacking, distribution of malware, and misuse of network resources are strictly prohibited.",
    "policyFileUrl": "https://storage.example.com/policies/it-policy-v4.pdf",
    "keyPoints": [
      "IT resources are for academic and authorized purposes only",
      "Do not share your login credentials with anyone",
      "Downloading pirated software is strictly prohibited",
      "Respect network bandwidth – avoid excessive streaming during peak hours",
      "Do not attempt to bypass network security or firewalls",
      "Report any security incidents to IT Help Desk immediately",
      "All activity on IIT networks may be monitored",
      "Violators may have their IT access suspended"
    ],
    "contactPerson": {
      "name": "Mr. Pradeep Wijesinghe",
      "role": "Head of IT Services",
      "email": "pradeep.wijesinghe@iit.ac.lk"
    },
    "lastUpdated": "2025-08-10T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

## 8. Mitigation Forms

### 8.1 Get All Mitigation Forms

**GET** `/api/v1/mitigation-forms`

Returns a list of all available mitigation forms.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": [
    {
      "id": 1,
      "formName": "UoW - Late Mitigation Circumstances Form",
      "formSlug": "uow-late-mitigation-circumstances-form",
      "university": "University of Wolverhampton",
      "description": "Form for late submission mitigation claims for UoW programmes"
    },
    {
      "id": 2,
      "formName": "UoW - Mitigating Circumstances Form",
      "formSlug": "uow-mitigating-circumstances-form",
      "university": "University of Wolverhampton",
      "description": "General mitigating circumstances claim form for UoW programmes"
    },
    {
      "id": 3,
      "formName": "UoW - Self Certification Claim Form",
      "formSlug": "uow-self-certification-claim-form",
      "university": "University of Wolverhampton",
      "description": "Self-certification form for minor illness claims for UoW programmes"
    },
    {
      "id": 4,
      "formName": "RGU - Coursework Extension Form (Self-Certification)",
      "formSlug": "rgu-coursework-extension-form-self-certification",
      "university": "Robert Gordon University",
      "description": "Coursework extension request form with self-certification for RGU programmes"
    },
    {
      "id": 5,
      "formName": "RGU - Deferral Request Form (Self-Certification)",
      "formSlug": "rgu-deferral-request-form-self-certification",
      "university": "Robert Gordon University",
      "description": "Assessment deferral request form with self-certification for RGU programmes"
    }
  ],
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 8.2 Get UoW Late Mitigation Circumstances Form

**GET** `/api/v1/mitigation-forms/uow-late-mitigation-circumstances-form`

Returns details of the UoW Late Mitigation Circumstances Form.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 1,
    "formName": "UoW - Late Mitigation Circumstances Form",
    "formSlug": "uow-late-mitigation-circumstances-form",
    "university": "University of Wolverhampton",
    "description": "This form should be completed if you are submitting a mitigating circumstances claim after the standard deadline. Late claims are only accepted in exceptional circumstances where the student was unable to submit on time.",
    "formFileUrl": "https://storage.example.com/mitigation/uow-late-mitigation-form.pdf",
    "submissionEmail": "mitigation.uow@iit.ac.lk",
    "submissionDeadline": "As soon as possible after the original deadline has passed",
    "eligibleCircumstances": [
      "Medical emergency requiring hospitalization",
      "Bereavement of immediate family member",
      "Serious accident or injury",
      "Circumstances that prevented timely claim submission"
    ],
    "requiredDocuments": [
      "Completed Late Mitigation Circumstances Form",
      "Original evidence (e.g., hospital records, medical certificates)",
      "Written explanation of why the claim could not be submitted on time"
    ],
    "processingTimeBusinessDays": 10,
    "contactPerson": {
      "name": "Academic Registry - UoW Programmes",
      "email": "mitigation.uow@iit.ac.lk",
      "phone": "+94 11 234 5691"
    },
    "lastUpdated": "2026-01-05T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 8.3 Get UoW Mitigating Circumstances Form

**GET** `/api/v1/mitigation-forms/uow-mitigating-circumstances-form`

Returns details of the UoW Mitigating Circumstances Form.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 2,
    "formName": "UoW - Mitigating Circumstances Form",
    "formSlug": "uow-mitigating-circumstances-form",
    "university": "University of Wolverhampton",
    "description": "This is the standard mitigating circumstances claim form for students on University of Wolverhampton programmes. Use this form if you have experienced circumstances that have adversely affected your academic performance.",
    "formFileUrl": "https://storage.example.com/mitigation/uow-mitigating-circumstances-form.pdf",
    "submissionEmail": "mitigation.uow@iit.ac.lk",
    "submissionDeadline": "Within 5 working days of the affected assessment deadline",
    "eligibleCircumstances": [
      "Serious illness or injury (with medical evidence)",
      "Bereavement of a close relative or friend",
      "Significant family or personal crisis",
      "Victim of crime",
      "Unforeseen work-related issues (part-time students)",
      "Other exceptional circumstances"
    ],
    "requiredDocuments": [
      "Completed Mitigating Circumstances Form",
      "Independent supporting evidence (medical certificate, etc.)",
      "Personal statement detailing the impact"
    ],
    "processingTimeBusinessDays": 7,
    "possibleOutcomes": [
      "Extension to assessment deadline",
      "Deferral to next assessment period",
      "Assessment marked as if first attempt",
      "Claim rejected – original mark stands"
    ],
    "contactPerson": {
      "name": "Academic Registry - UoW Programmes",
      "email": "mitigation.uow@iit.ac.lk",
      "phone": "+94 11 234 5691"
    },
    "lastUpdated": "2026-01-05T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 8.4 Get UoW Self Certification Claim Form

**GET** `/api/v1/mitigation-forms/uow-self-certification-claim-form`

Returns details of the UoW Self Certification Claim Form.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 3,
    "formName": "UoW - Self Certification Claim Form",
    "formSlug": "uow-self-certification-claim-form",
    "university": "University of Wolverhampton",
    "description": "This form allows students to self-certify for short-term illness or minor personal issues without requiring independent evidence. Limited to one self-certification per semester.",
    "formFileUrl": "https://storage.example.com/mitigation/uow-self-certification-form.pdf",
    "submissionEmail": "mitigation.uow@iit.ac.lk",
    "submissionDeadline": "Within 3 working days of the affected assessment deadline",
    "eligibleCircumstances": [
      "Short-term illness (up to 7 days)",
      "Minor personal or family issue",
      "Short-term mental health difficulty"
    ],
    "requiredDocuments": [
      "Completed Self Certification Claim Form",
      "No additional evidence required (self-certification only)"
    ],
    "limitations": [
      "Maximum 1 self-certification claim per semester",
      "Cannot be used for the same assessment more than once",
      "Cannot be combined with a full mitigating circumstances claim for the same assessment"
    ],
    "processingTimeBusinessDays": 5,
    "contactPerson": {
      "name": "Academic Registry - UoW Programmes",
      "email": "mitigation.uow@iit.ac.lk",
      "phone": "+94 11 234 5691"
    },
    "lastUpdated": "2026-01-05T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 8.5 Get RGU Coursework Extension Form (Self-Certification)

**GET** `/api/v1/mitigation-forms/rgu-coursework-extension-form-self-certification`

Returns details of the RGU Coursework Extension Form.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 4,
    "formName": "RGU - Coursework Extension Form (Self-Certification)",
    "formSlug": "rgu-coursework-extension-form-self-certification",
    "university": "Robert Gordon University",
    "description": "This form allows students on RGU programmes to request a short extension for coursework submissions through self-certification. The extension is typically 7 calendar days.",
    "formFileUrl": "https://storage.example.com/mitigation/rgu-coursework-extension-form.pdf",
    "submissionEmail": "mitigation.rgu@iit.ac.lk",
    "submissionDeadline": "Before the original coursework submission deadline",
    "eligibleCircumstances": [
      "Short-term illness",
      "Unexpected personal or family emergency",
      "Technical issues preventing submission (with evidence of attempt)",
      "Other short-term circumstances"
    ],
    "requiredDocuments": [
      "Completed Coursework Extension Form",
      "Self-certification declaration (included in form)"
    ],
    "extensionDuration": "7 calendar days from the original deadline",
    "limitations": [
      "Maximum 2 self-certification extension requests per trimester",
      "Cannot extend beyond 7 calendar days",
      "For extensions beyond 7 days, a full mitigation claim is required"
    ],
    "processingTimeBusinessDays": 3,
    "contactPerson": {
      "name": "Academic Registry - RGU Programmes",
      "email": "mitigation.rgu@iit.ac.lk",
      "phone": "+94 11 234 5692"
    },
    "lastUpdated": "2026-01-05T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 8.6 Get RGU Deferral Request Form (Self-Certification)

**GET** `/api/v1/mitigation-forms/rgu-deferral-request-form-self-certification`

Returns details of the RGU Deferral Request Form.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 5,
    "formName": "RGU - Deferral Request Form (Self-Certification)",
    "formSlug": "rgu-deferral-request-form-self-certification",
    "university": "Robert Gordon University",
    "description": "This form allows students on RGU programmes to request a deferral of an assessment to the next assessment period through self-certification.",
    "formFileUrl": "https://storage.example.com/mitigation/rgu-deferral-request-form.pdf",
    "submissionEmail": "mitigation.rgu@iit.ac.lk",
    "submissionDeadline": "Within 5 working days of the affected assessment date",
    "eligibleCircumstances": [
      "Significant illness during exam period",
      "Bereavement of close family member",
      "Serious personal emergency",
      "Circumstances preventing exam attendance"
    ],
    "requiredDocuments": [
      "Completed Deferral Request Form",
      "Self-certification declaration (included in form)",
      "Supporting evidence recommended but not mandatory for first claim"
    ],
    "deferralDetails": "Assessment will be deferred to the next available assessment period. The deferred assessment will be treated as a first attempt.",
    "limitations": [
      "Maximum 1 self-certification deferral per trimester",
      "Second deferral in same trimester requires full evidence",
      "Deferral is subject to approval by the Module Assessment Board"
    ],
    "processingTimeBusinessDays": 5,
    "contactPerson": {
      "name": "Academic Registry - RGU Programmes",
      "email": "mitigation.rgu@iit.ac.lk",
      "phone": "+94 11 234 5692"
    },
    "lastUpdated": "2026-01-05T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

## 9. Staff

### 9.1 Get All Staff Categories

**GET** `/api/v1/staff`

Returns a list of all staff information categories.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": [
    {
      "categoryName": "SOC",
      "categorySlug": "soc",
      "description": "School of Computing staff information"
    },
    {
      "categoryName": "Common Info",
      "categorySlug": "common-info",
      "description": "Common staff information and general guidelines"
    },
    {
      "categoryName": "Mail Groups",
      "categorySlug": "mail-groups",
      "description": "Staff and department email groups"
    },
    {
      "categoryName": "Doc Arch",
      "categorySlug": "doc-arch",
      "description": "Document archive and templates"
    },
    {
      "categoryName": "Contacts",
      "categorySlug": "contacts",
      "description": "Staff contact directory"
    }
  ],
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 9.2 Get SOC (School of Computing) Staff

**GET** `/api/v1/staff/soc`

Returns the School of Computing staff list.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "categoryName": "SOC",
    "departmentFullName": "School of Computing",
    "staffMembers": [
      {
        "id": 1,
        "name": "Prof. Malitha Wijesundara",
        "designation": "Head of School of Computing",
        "email": "malitha.wijesundara@iit.ac.lk",
        "phone": "+94 11 234 5700",
        "office": "Room D-301",
        "specialization": "Artificial Intelligence, Machine Learning",
        "officeHours": "Mon, Wed 2:00 PM - 4:00 PM"
      },
      {
        "id": 2,
        "name": "Dr. Kavinda Ratnayake",
        "designation": "Senior Lecturer",
        "email": "kavinda.ratnayake@iit.ac.lk",
        "phone": "+94 11 234 5701",
        "office": "Room D-302",
        "specialization": "Software Engineering, DevOps",
        "officeHours": "Tue, Thu 10:00 AM - 12:00 PM"
      },
      {
        "id": 3,
        "name": "Ms. Hasini Perera",
        "designation": "Lecturer",
        "email": "hasini.perera@iit.ac.lk",
        "phone": "+94 11 234 5702",
        "office": "Room D-303",
        "specialization": "Data Science, Big Data",
        "officeHours": "Mon, Fri 9:00 AM - 11:00 AM"
      },
      {
        "id": 4,
        "name": "Mr. Ravindu Dissanayake",
        "designation": "Lecturer",
        "email": "ravindu.dissanayake@iit.ac.lk",
        "phone": "+94 11 234 5703",
        "office": "Room D-304",
        "specialization": "Cybersecurity, Network Security",
        "officeHours": "Wed, Fri 1:00 PM - 3:00 PM"
      },
      {
        "id": 5,
        "name": "Dr. Sanduni Wickramasinghe",
        "designation": "Senior Lecturer",
        "email": "sanduni.wickramasinghe@iit.ac.lk",
        "phone": "+94 11 234 5704",
        "office": "Room D-305",
        "specialization": "Database Systems, Cloud Computing",
        "officeHours": "Tue, Thu 2:00 PM - 4:00 PM"
      }
    ],
    "lastUpdated": "2026-02-01T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 9.3 Get Common Staff Info

**GET** `/api/v1/staff/common-info`

Returns common staff information and general guidelines.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "categoryName": "Common Info",
    "generalInfo": {
      "institutionName": "Informatics Institute of Technology (IIT)",
      "mainAddress": "57, Ramakrishna Road, Colombo 06, Sri Lanka",
      "mainPhone": "+94 11 236 0212",
      "mainEmail": "info@iit.ac.lk",
      "website": "https://www.iit.ac.lk",
      "workingHours": "Mon-Fri 8:30 AM - 5:30 PM",
      "academicYear": "2025/2026"
    },
    "departments": [
      {
        "departmentName": "Academic Affairs",
        "headOfDepartment": "Dr. Nirmal Jayaratne",
        "email": "academic.affairs@iit.ac.lk",
        "phone": "+94 11 234 5710"
      },
      {
        "departmentName": "Student Services",
        "headOfDepartment": "Ms. Dilhani Jayawardena",
        "email": "student.services@iit.ac.lk",
        "phone": "+94 11 234 5711"
      },
      {
        "departmentName": "Finance Department",
        "headOfDepartment": "Mr. Chaminda Perera",
        "email": "finance@iit.ac.lk",
        "phone": "+94 11 234 5712"
      },
      {
        "departmentName": "IT Services",
        "headOfDepartment": "Mr. Pradeep Wijesinghe",
        "email": "it.services@iit.ac.lk",
        "phone": "+94 11 234 5713"
      },
      {
        "departmentName": "Human Resources",
        "headOfDepartment": "Ms. Yamuna Silva",
        "email": "hr@iit.ac.lk",
        "phone": "+94 11 234 5714"
      }
    ],
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 9.4 Get Staff Mail Groups

**GET** `/api/v1/staff/mail-groups`

Returns the list of staff and department email groups.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "categoryName": "Mail Groups",
    "description": "Official email groups for staff communication and student inquiries.",
    "mailGroups": [
      {
        "groupName": "All Staff",
        "email": "all-staff@iit.ac.lk",
        "description": "Email group for all IIT staff members",
        "accessLevel": "STAFF_ONLY"
      },
      {
        "groupName": "School of Computing Lecturers",
        "email": "soc-lecturers@iit.ac.lk",
        "description": "All lecturers in the School of Computing",
        "accessLevel": "STAFF_ONLY"
      },
      {
        "groupName": "Academic Registry",
        "email": "academic.registry@iit.ac.lk",
        "description": "Academic registry for student records and documentation",
        "accessLevel": "PUBLIC"
      },
      {
        "groupName": "UoW Programme Team",
        "email": "uow-team@iit.ac.lk",
        "description": "University of Wolverhampton programme coordinators and lecturers",
        "accessLevel": "STAFF_ONLY"
      },
      {
        "groupName": "RGU Programme Team",
        "email": "rgu-team@iit.ac.lk",
        "description": "Robert Gordon University programme coordinators and lecturers",
        "accessLevel": "STAFF_ONLY"
      },
      {
        "groupName": "Foundation Programme Team",
        "email": "foundation-team@iit.ac.lk",
        "description": "Foundation programme lecturers and coordinators",
        "accessLevel": "STAFF_ONLY"
      },
      {
        "groupName": "Examination Board",
        "email": "exam.board@iit.ac.lk",
        "description": "Examination board members",
        "accessLevel": "STAFF_ONLY"
      },
      {
        "groupName": "Student Complaints",
        "email": "complaints@iit.ac.lk",
        "description": "Student complaints handling team",
        "accessLevel": "PUBLIC"
      }
    ],
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 9.5 Get Staff Document Archive

**GET** `/api/v1/staff/doc-arch`

Returns the document archive listing.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "categoryName": "Doc Arch",
    "description": "Archive of important documents, templates, and forms for staff use.",
    "documents": [
      {
        "id": 1,
        "documentName": "Lecture Plan Template",
        "category": "Teaching",
        "fileUrl": "https://storage.example.com/staff-docs/lecture-plan-template.docx",
        "fileType": "DOCX",
        "fileSizeKb": 45,
        "uploadedDate": "2025-09-01"
      },
      {
        "id": 2,
        "documentName": "Assessment Brief Template",
        "category": "Assessment",
        "fileUrl": "https://storage.example.com/staff-docs/assessment-brief-template.docx",
        "fileType": "DOCX",
        "fileSizeKb": 52,
        "uploadedDate": "2025-09-01"
      },
      {
        "id": 3,
        "documentName": "Module Specification Template",
        "category": "Academic",
        "fileUrl": "https://storage.example.com/staff-docs/module-spec-template.docx",
        "fileType": "DOCX",
        "fileSizeKb": 68,
        "uploadedDate": "2025-09-01"
      },
      {
        "id": 4,
        "documentName": "Staff Leave Application Form",
        "category": "HR",
        "fileUrl": "https://storage.example.com/staff-docs/leave-application-form.pdf",
        "fileType": "PDF",
        "fileSizeKb": 120,
        "uploadedDate": "2025-09-01"
      },
      {
        "id": 5,
        "documentName": "Student Attendance Record Template",
        "category": "Teaching",
        "fileUrl": "https://storage.example.com/staff-docs/attendance-record-template.xlsx",
        "fileType": "XLSX",
        "fileSizeKb": 35,
        "uploadedDate": "2025-09-01"
      },
      {
        "id": 6,
        "documentName": "External Examiner Report Template",
        "category": "Assessment",
        "fileUrl": "https://storage.example.com/staff-docs/external-examiner-report.docx",
        "fileType": "DOCX",
        "fileSizeKb": 58,
        "uploadedDate": "2025-09-01"
      }
    ],
    "lastUpdated": "2025-09-01T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 9.6 Get Staff Contacts

**GET** `/api/v1/staff/contacts`

Returns the full staff contact directory.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "categoryName": "Contacts",
    "description": "Complete staff contact directory organized by department.",
    "departments": [
      {
        "departmentName": "School of Computing",
        "contacts": [
          {
            "name": "Prof. Malitha Wijesundara",
            "designation": "Head of School",
            "email": "malitha.wijesundara@iit.ac.lk",
            "phone": "+94 11 234 5700",
            "extension": "5700"
          },
          {
            "name": "Dr. Kavinda Ratnayake",
            "designation": "Senior Lecturer",
            "email": "kavinda.ratnayake@iit.ac.lk",
            "phone": "+94 11 234 5701",
            "extension": "5701"
          }
        ]
      },
      {
        "departmentName": "Academic Affairs",
        "contacts": [
          {
            "name": "Dr. Nirmal Jayaratne",
            "designation": "Head of Academic Affairs",
            "email": "nirmal.jayaratne@iit.ac.lk",
            "phone": "+94 11 234 5710",
            "extension": "5710"
          }
        ]
      },
      {
        "departmentName": "Student Services",
        "contacts": [
          {
            "name": "Ms. Dilhani Jayawardena",
            "designation": "Head of Student Services",
            "email": "dilhani.jayawardena@iit.ac.lk",
            "phone": "+94 11 234 5711",
            "extension": "5711"
          },
          {
            "name": "Mr. Kasun Jayasinghe",
            "designation": "Student Counselor",
            "email": "kasun.jayasinghe@iit.ac.lk",
            "phone": "+94 11 234 5682",
            "extension": "5682"
          }
        ]
      },
      {
        "departmentName": "Finance",
        "contacts": [
          {
            "name": "Mr. Chaminda Perera",
            "designation": "Head of Finance",
            "email": "chaminda.perera@iit.ac.lk",
            "phone": "+94 11 234 5712",
            "extension": "5712"
          }
        ]
      },
      {
        "departmentName": "IT Services",
        "contacts": [
          {
            "name": "Mr. Pradeep Wijesinghe",
            "designation": "Head of IT Services",
            "email": "pradeep.wijesinghe@iit.ac.lk",
            "phone": "+94 11 234 5713",
            "extension": "5713"
          },
          {
            "name": "IT Help Desk",
            "designation": "Technical Support",
            "email": "ithelpdesk@iit.ac.lk",
            "phone": "+94 11 234 5683",
            "extension": "5683"
          }
        ]
      },
      {
        "departmentName": "Library",
        "contacts": [
          {
            "name": "Ms. Ranudi Fernando",
            "designation": "Head Librarian",
            "email": "library@iit.ac.lk",
            "phone": "+94 11 234 5684",
            "extension": "5684"
          }
        ]
      }
    ],
    "emergencyContacts": {
      "security": "+94 11 234 5799",
      "medicalEmergency": "+94 11 234 5798",
      "fireEmergency": "119"
    },
    "lastUpdated": "2026-02-01T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

## 10. Info

### 10.1 Get All Info Categories

**GET** `/api/v1/info`

Returns a list of all general information categories.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": [
    {
      "categoryName": "Course Details",
      "categorySlug": "course-details",
      "description": "Detailed information about all courses offered"
    },
    {
      "categoryName": "Houses",
      "categorySlug": "houses",
      "description": "Information about the IIT house system"
    },
    {
      "categoryName": "Students' Union",
      "categorySlug": "students-union",
      "description": "Information about the IIT Students' Union"
    },
    {
      "categoryName": "Clubs and Societies",
      "categorySlug": "clubs-and-societies",
      "description": "List of all student clubs and societies"
    }
  ],
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 10.2 Get Course Details

**GET** `/api/v1/info/course-details`

Returns comprehensive course details for all programmes.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "categoryName": "Course Details",
    "programmeCategories": [
      {
        "category": "Foundation",
        "programmes": [
          {
            "programName": "IIT Foundation Programme",
            "duration": "1 year",
            "awardingBody": "IIT",
            "intake": "September & January",
            "fee": "Contact Admissions"
          }
        ]
      },
      {
        "category": "Undergraduate",
        "programmes": [
          {
            "programName": "BSc (Hons) Artificial Intelligence and Data Science",
            "duration": "3 years",
            "awardingBody": "University of Wolverhampton",
            "intake": "September",
            "fee": "Contact Admissions"
          },
          {
            "programName": "BSc (Hons) Computer Science",
            "duration": "3 years",
            "awardingBody": "University of Wolverhampton",
            "intake": "September",
            "fee": "Contact Admissions"
          },
          {
            "programName": "BEng (Hons) Software Engineering",
            "duration": "3 years",
            "awardingBody": "University of Wolverhampton",
            "intake": "September",
            "fee": "Contact Admissions"
          },
          {
            "programName": "BSc (Hons) Big Data Analytics",
            "duration": "3 years",
            "awardingBody": "Robert Gordon University",
            "intake": "September",
            "fee": "Contact Admissions"
          },
          {
            "programName": "BSc (Hons) Business Information Systems",
            "duration": "3 years",
            "awardingBody": "Robert Gordon University",
            "intake": "September",
            "fee": "Contact Admissions"
          },
          {
            "programName": "BA (Hons) Business Management",
            "duration": "3 years",
            "awardingBody": "Robert Gordon University",
            "intake": "September",
            "fee": "Contact Admissions"
          },
          {
            "programName": "BSc (Hons) Business Computing",
            "duration": "3 years",
            "awardingBody": "Robert Gordon University",
            "intake": "September",
            "fee": "Contact Admissions"
          }
        ]
      },
      {
        "category": "Postgraduate",
        "programmes": [
          {
            "programName": "MSc Advanced Software Engineering",
            "duration": "1 year",
            "awardingBody": "University of Wolverhampton",
            "intake": "September & January",
            "fee": "Contact Admissions"
          },
          {
            "programName": "MSc Cyber Security and Forensics",
            "duration": "1 year",
            "awardingBody": "University of Wolverhampton",
            "intake": "September & January",
            "fee": "Contact Admissions"
          },
          {
            "programName": "MSc Information Technology",
            "duration": "1 year",
            "awardingBody": "University of Wolverhampton",
            "intake": "September & January",
            "fee": "Contact Admissions"
          },
          {
            "programName": "MSc Big Data Analytics",
            "duration": "1 year",
            "awardingBody": "Robert Gordon University",
            "intake": "September",
            "fee": "Contact Admissions"
          },
          {
            "programName": "MSc Business Analytics",
            "duration": "1 year",
            "awardingBody": "Robert Gordon University",
            "intake": "September",
            "fee": "Contact Admissions"
          },
          {
            "programName": "MSc Finance and Business Management",
            "duration": "1 year",
            "awardingBody": "Robert Gordon University",
            "intake": "September",
            "fee": "Contact Admissions"
          }
        ]
      }
    ],
    "admissionsContact": {
      "email": "admissions@iit.ac.lk",
      "phone": "+94 11 236 0212",
      "whatsapp": "+94 77 123 4567"
    },
    "lastUpdated": "2026-01-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 10.3 Get Houses Information

**GET** `/api/v1/info/houses`

Returns information about the IIT house system.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "categoryName": "Houses",
    "description": "IIT follows a house system to foster a sense of community, teamwork, and healthy competition among students. Every student is assigned to a house upon enrollment.",
    "houses": [
      {
        "id": 1,
        "houseName": "Phoenix",
        "color": "#FF4500",
        "motto": "Rise from the Ashes",
        "description": "Phoenix house represents resilience, renewal, and the drive to overcome challenges.",
        "housemaster": "Dr. Kavinda Ratnayake",
        "captainName": "Nethmi Jayasuriya",
        "logoUrl": "https://storage.example.com/houses/phoenix-logo.png",
        "totalPoints": 1250,
        "rank": 2
      },
      {
        "id": 2,
        "houseName": "Titan",
        "color": "#1E90FF",
        "motto": "Strength in Unity",
        "description": "Titan house represents strength, determination, and collective power.",
        "housemaster": "Ms. Hasini Perera",
        "captainName": "Ashan Fernando",
        "logoUrl": "https://storage.example.com/houses/titan-logo.png",
        "totalPoints": 1380,
        "rank": 1
      },
      {
        "id": 3,
        "houseName": "Draco",
        "color": "#32CD32",
        "motto": "Courage and Wisdom",
        "description": "Draco house represents courage, wisdom, and the pursuit of excellence.",
        "housemaster": "Mr. Ravindu Dissanayake",
        "captainName": "Sithumi Perera",
        "logoUrl": "https://storage.example.com/houses/draco-logo.png",
        "totalPoints": 1180,
        "rank": 3
      },
      {
        "id": 4,
        "houseName": "Orion",
        "color": "#FFD700",
        "motto": "Reach for the Stars",
        "description": "Orion house represents ambition, exploration, and the quest for knowledge.",
        "housemaster": "Dr. Sanduni Wickramasinghe",
        "captainName": "Kavindu Silva",
        "logoUrl": "https://storage.example.com/houses/orion-logo.png",
        "totalPoints": 1100,
        "rank": 4
      }
    ],
    "currentAcademicYear": "2025/2026",
    "lastUpdated": "2026-02-28T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 10.4 Get Students' Union Information

**GET** `/api/v1/info/students-union`

Returns information about the IIT Students' Union.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "categoryName": "Students' Union",
    "description": "The IIT Students' Union is the official representative body of all students at the Informatics Institute of Technology. It organizes events, manages clubs, advocates for student welfare, and bridges communication between the student body and the administration.",
    "office": "Student Services Building, Room SS-101",
    "email": "students.union@iit.ac.lk",
    "phone": "+94 11 234 5720",
    "socialMedia": {
      "facebook": "https://facebook.com/IITStudentsUnion",
      "instagram": "https://instagram.com/iit_su",
      "linkedin": "https://linkedin.com/company/iit-students-union"
    },
    "currentOffice": {
      "academicYear": "2025/2026",
      "officeBearers": [
        {
          "position": "President",
          "name": "Dinusha Lakmal",
          "email": "president.su@iit.ac.lk",
          "programme": "BSc (Hons) Computer Science",
          "year": 3
        },
        {
          "position": "Vice President",
          "name": "Sachini Mendis",
          "email": "vp.su@iit.ac.lk",
          "programme": "BEng (Hons) Software Engineering",
          "year": 3
        },
        {
          "position": "Secretary",
          "name": "Pathum Weerasinghe",
          "email": "secretary.su@iit.ac.lk",
          "programme": "BSc (Hons) Big Data Analytics",
          "year": 2
        },
        {
          "position": "Treasurer",
          "name": "Ishara De Silva",
          "email": "treasurer.su@iit.ac.lk",
          "programme": "BA (Hons) Business Management",
          "year": 2
        },
        {
          "position": "Events Coordinator",
          "name": "Nimesh Abeykoon",
          "email": "events.su@iit.ac.lk",
          "programme": "BSc (Hons) Artificial Intelligence and Data Science",
          "year": 2
        }
      ]
    },
    "upcomingEvents": [
      {
        "eventName": "IIT Hackathon 2026",
        "date": "2026-03-15",
        "venue": "IIT Main Auditorium",
        "description": "Annual 24-hour hackathon open to all IIT students"
      },
      {
        "eventName": "Career Fair 2026",
        "date": "2026-04-10",
        "venue": "IIT Sports Complex",
        "description": "Annual career fair with top IT companies in Sri Lanka"
      }
    ],
    "lastUpdated": "2026-02-15T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

### 10.5 Get Clubs and Societies Information

**GET** `/api/v1/info/clubs-and-societies`

Returns information about all student clubs and societies.

**Request Headers:**
| Header | Value | Required |
|--------|-------|----------|
| Authorization | Bearer `<token>` | Yes |

**Request Parameters:** None

**Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "categoryName": "Clubs and Societies",
    "description": "IIT has a vibrant club culture with various technical, cultural, and recreational clubs. Students are encouraged to join clubs to develop skills and build networks.",
    "totalClubs": 12,
    "clubs": [
      {
        "id": 1,
        "clubName": "IIT Coding Club",
        "clubCode": "ICC",
        "category": "Technical",
        "description": "A community for coding enthusiasts. We organize coding competitions, workshops, and study groups.",
        "logoUrl": "https://storage.example.com/clubs/iit-coding-club.png",
        "president": "Kavindu Sandaruwan",
        "email": "coding.club@students.iit.ac.lk",
        "memberCount": 85,
        "isOpenForRegistration": true,
        "socialMedia": {
          "instagram": "https://instagram.com/iit_codingclub"
        }
      },
      {
        "id": 2,
        "clubName": "IIT Robotics Society",
        "clubCode": "IRS",
        "category": "Technical",
        "description": "Explore robotics, IoT, and hardware projects. Participate in national robotics competitions.",
        "logoUrl": "https://storage.example.com/clubs/iit-robotics.png",
        "president": "Nethmi Weerasinghe",
        "email": "robotics@students.iit.ac.lk",
        "memberCount": 45,
        "isOpenForRegistration": true,
        "socialMedia": {
          "instagram": "https://instagram.com/iit_robotics"
        }
      },
      {
        "id": 3,
        "clubName": "IIT Cybersecurity Club",
        "clubCode": "ICSC",
        "category": "Technical",
        "description": "Learn about cybersecurity, participate in CTF competitions, and attend security workshops.",
        "logoUrl": "https://storage.example.com/clubs/iit-cybersec.png",
        "president": "Amaya Jayasuriya",
        "email": "cybersec.club@students.iit.ac.lk",
        "memberCount": 60,
        "isOpenForRegistration": true,
        "socialMedia": {
          "instagram": "https://instagram.com/iit_cybersec"
        }
      },
      {
        "id": 4,
        "clubName": "IIT Drama and Arts Society",
        "clubCode": "IDAS",
        "category": "Cultural",
        "description": "Express your creativity through drama, theater, and visual arts.",
        "logoUrl": "https://storage.example.com/clubs/iit-drama-arts.png",
        "president": "Hiruni Fernando",
        "email": "drama.arts@students.iit.ac.lk",
        "memberCount": 35,
        "isOpenForRegistration": true,
        "socialMedia": {
          "instagram": "https://instagram.com/iit_dramaarts"
        }
      },
      {
        "id": 5,
        "clubName": "IIT Sports Club",
        "clubCode": "ISC",
        "category": "Sports",
        "description": "Organize and participate in inter-university sports competitions and fitness events.",
        "logoUrl": "https://storage.example.com/clubs/iit-sports.png",
        "president": "Ravindu Perera",
        "email": "sports.club@students.iit.ac.lk",
        "memberCount": 120,
        "isOpenForRegistration": true,
        "socialMedia": {
          "instagram": "https://instagram.com/iit_sports"
        }
      },
      {
        "id": 6,
        "clubName": "IIT Photography Club",
        "clubCode": "IPC",
        "category": "Creative",
        "description": "For photography enthusiasts. Organize photo walks, workshops, and exhibitions.",
        "logoUrl": "https://storage.example.com/clubs/iit-photography.png",
        "president": "Sanduni Rathnayake",
        "email": "photography@students.iit.ac.lk",
        "memberCount": 40,
        "isOpenForRegistration": false,
        "socialMedia": {
          "instagram": "https://instagram.com/iit_photography"
        }
      },
      {
        "id": 7,
        "clubName": "IIT Entrepreneurship Society",
        "clubCode": "IES",
        "category": "Business",
        "description": "Foster entrepreneurial thinking through startup workshops, pitch nights, and mentorship programs.",
        "logoUrl": "https://storage.example.com/clubs/iit-entrepreneurship.png",
        "president": "Chamara Wickrama",
        "email": "entrepreneurship@students.iit.ac.lk",
        "memberCount": 55,
        "isOpenForRegistration": true,
        "socialMedia": {
          "instagram": "https://instagram.com/iit_entrepreneur"
        }
      },
      {
        "id": 8,
        "clubName": "IIT Music Club",
        "clubCode": "IMC",
        "category": "Cultural",
        "description": "For music lovers. Band practice, jam sessions, and performances at IIT events.",
        "logoUrl": "https://storage.example.com/clubs/iit-music.png",
        "president": "Maneesha De Alwis",
        "email": "music.club@students.iit.ac.lk",
        "memberCount": 30,
        "isOpenForRegistration": true,
        "socialMedia": {
          "instagram": "https://instagram.com/iit_music"
        }
      }
    ],
    "joinInstructions": "To join a club, visit the Clubs section in the Nextora app or contact the club directly via email. Some clubs have open registration while others may require an application.",
    "lastUpdated": "2026-02-20T08:00:00"
  },
  "timestamp": "2026-03-01T10:30:00"
}
```

---

## API Endpoints Summary Table

| # | Method | Endpoint | Description |
|---|--------|----------|-------------|
| 1 | GET | `/api/v1/student-complaints` | Get all complaint categories |
| 2 | GET | `/api/v1/student-complaints/academic-course-delivery` | Academic course delivery complaints info |
| 3 | GET | `/api/v1/student-complaints/facility-and-support-system` | Facility & support system complaints info |
| 4 | GET | `/api/v1/academic-calendars` | Get all academic calendars |
| 5 | GET | `/api/v1/academic-calendars/uow` | UoW academic calendar |
| 6 | GET | `/api/v1/academic-calendars/rgu` | RGU academic calendar |
| 7 | GET | `/api/v1/undergraduate` | Get all undergraduate programs |
| 8 | GET | `/api/v1/undergraduate/bsc-ai-ds` | BSc AI & Data Science details |
| 9 | GET | `/api/v1/undergraduate/bsc-cs` | BSc Computer Science details |
| 10 | GET | `/api/v1/undergraduate/beng-se` | BEng Software Engineering details |
| 11 | GET | `/api/v1/undergraduate/bsc-bda` | BSc Big Data Analytics details |
| 12 | GET | `/api/v1/undergraduate/bsc-bis` | BSc Business Information Systems details |
| 13 | GET | `/api/v1/undergraduate/ba-bm` | BA Business Management details |
| 14 | GET | `/api/v1/undergraduate/bsc-bc` | BSc Business Computing details |
| 15 | GET | `/api/v1/postgraduate` | Get all postgraduate programs |
| 16 | GET | `/api/v1/postgraduate/msc-ase` | MSc Advanced Software Engineering details |
| 17 | GET | `/api/v1/postgraduate/msc-cs-f` | MSc Cyber Security & Forensics details |
| 18 | GET | `/api/v1/postgraduate/msc-it` | MSc Information Technology details |
| 19 | GET | `/api/v1/postgraduate/msc-bda` | MSc Big Data Analytics details |
| 20 | GET | `/api/v1/postgraduate/msc-ba` | MSc Business Analytics details |
| 21 | GET | `/api/v1/postgraduate/msc-fbm` | MSc Finance & Business Management details |
| 22 | GET | `/api/v1/foundation-program` | Get all foundation program categories |
| 23 | GET | `/api/v1/foundation-program/academic-calendar` | Foundation academic calendar |
| 24 | GET | `/api/v1/foundation-program/program-specification` | Foundation program specification |
| 25 | GET | `/api/v1/foundation-program/important-contact-details` | Foundation important contacts |
| 26 | GET | `/api/v1/foundation-program/time-table` | Foundation timetable |
| 27 | GET | `/api/v1/foundation-program/assessment-schedule` | Foundation assessment schedule |
| 28 | GET | `/api/v1/foundation-program/lms-login-details` | Foundation LMS login details |
| 29 | GET | `/api/v1/foundation-program/mitigating-circumstances-form` | Foundation mitigation form |
| 30 | GET | `/api/v1/students-relations-unit` | Students Relations Unit info |
| 31 | GET | `/api/v1/students-relations-unit/help-desk-video-series` | Help desk video series |
| 32 | GET | `/api/v1/student-policies` | Get all student policies |
| 33 | GET | `/api/v1/student-policies/participation-at-conferences` | Conference participation policy |
| 34 | GET | `/api/v1/student-policies/participation-at-competitions` | Competition participation policy |
| 35 | GET | `/api/v1/student-policies/code-of-conduct` | Code of conduct |
| 36 | GET | `/api/v1/student-policies/club-policy` | Club policy |
| 37 | GET | `/api/v1/student-policies/it-policy` | IT policy |
| 38 | GET | `/api/v1/mitigation-forms` | Get all mitigation forms |
| 39 | GET | `/api/v1/mitigation-forms/uow-late-mitigation-circumstances-form` | UoW late mitigation form |
| 40 | GET | `/api/v1/mitigation-forms/uow-mitigating-circumstances-form` | UoW mitigating circumstances form |
| 41 | GET | `/api/v1/mitigation-forms/uow-self-certification-claim-form` | UoW self certification form |
| 42 | GET | `/api/v1/mitigation-forms/rgu-coursework-extension-form-self-certification` | RGU coursework extension form |
| 43 | GET | `/api/v1/mitigation-forms/rgu-deferral-request-form-self-certification` | RGU deferral request form |
| 44 | GET | `/api/v1/staff` | Get all staff categories |
| 45 | GET | `/api/v1/staff/soc` | School of Computing staff |
| 46 | GET | `/api/v1/staff/common-info` | Common staff info |
| 47 | GET | `/api/v1/staff/mail-groups` | Staff mail groups |
| 48 | GET | `/api/v1/staff/doc-arch` | Document archive |
| 49 | GET | `/api/v1/staff/contacts` | Staff contacts |
| 50 | GET | `/api/v1/info` | Get all info categories |
| 51 | GET | `/api/v1/info/course-details` | Course details |
| 52 | GET | `/api/v1/info/houses` | Houses information |
| 53 | GET | `/api/v1/info/students-union` | Students' Union info |
| 54 | GET | `/api/v1/info/clubs-and-societies` | Clubs and societies |

---

*Document generated on: March 1, 2026*  
*Nextora API v1.0*

