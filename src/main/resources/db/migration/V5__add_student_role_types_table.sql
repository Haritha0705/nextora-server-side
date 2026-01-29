-- ===================================================================================
-- V5: Add Student Role Types Table
-- Allows students to have multiple role types (NORMAL, CLUB_MEMBER, SENIOR_KUPPI, BATCH_REP)
-- ===================================================================================

-- ==================== STUDENT_ROLE_TYPES TABLE (ElementCollection) ====================
CREATE TABLE IF NOT EXISTS student_role_types (
    student_id BIGINT NOT NULL REFERENCES students(id) ON DELETE CASCADE,
    role_type VARCHAR(30) NOT NULL,
    PRIMARY KEY (student_id, role_type)
);

CREATE INDEX IF NOT EXISTS idx_student_role_types_student ON student_role_types(student_id);
CREATE INDEX IF NOT EXISTS idx_student_role_types_role ON student_role_types(role_type);

COMMENT ON TABLE student_role_types IS 'Multiple role types per student - allows a student to be CLUB_MEMBER and SENIOR_KUPPI simultaneously';

-- ==================== MIGRATE EXISTING DATA ====================
-- Copy existing student_role_type values to the new table
INSERT INTO student_role_types (student_id, role_type)
SELECT id, COALESCE(student_role_type, 'NORMAL')
FROM students
WHERE NOT EXISTS (
    SELECT 1 FROM student_role_types WHERE student_role_types.student_id = students.id
);

-- Ensure all students have at least NORMAL role type
INSERT INTO student_role_types (student_id, role_type)
SELECT id, 'NORMAL'
FROM students
WHERE NOT EXISTS (
    SELECT 1 FROM student_role_types
    WHERE student_role_types.student_id = students.id
    AND student_role_types.role_type = 'NORMAL'
);

-- Note: The old student_role_type column in students table is kept for backward compatibility
-- It will be deprecated and can be removed in a future migration
