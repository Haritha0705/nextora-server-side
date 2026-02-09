-- ===================================================================================
-- V8: Fix status column size (ensure column is VARCHAR(50))
-- This migration ensures the status column can accommodate all status values
-- ===================================================================================

-- Use DO block to check and alter column if needed
DO $$
BEGIN
    -- Check if the column type needs to be changed
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'users'
        AND column_name = 'status'
        AND character_maximum_length < 50
    ) THEN
        ALTER TABLE users ALTER COLUMN status TYPE VARCHAR(50);
        RAISE NOTICE 'Status column updated to VARCHAR(50)';
    END IF;
END $$;

-- Ensure phone_number is adequate size
DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'users'
        AND column_name = 'phone_number'
        AND character_maximum_length < 20
    ) THEN
        ALTER TABLE users ALTER COLUMN phone_number TYPE VARCHAR(20);
        RAISE NOTICE 'Phone number column updated to VARCHAR(20)';
    END IF;
END $$;
