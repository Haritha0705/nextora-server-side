package lk.iit.nextora.common.enums;

/**
 * Sub-roles for students within the ROLE_STUDENT main role.
 * A student can have one of these sub-roles which determines
 * their additional permissions and capabilities.
 */
public enum StudentRoleType {
    NORMAL("Normal Student"),
    CLUB_MEMBER("Club Member"),
    SENIOR_KUPPI("Senior Kuppi Mentor"),
    BATCH_REP("Batch Representative");

    private final String displayName;

    StudentRoleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Get default student role type
     */
    public static StudentRoleType getDefault() {
        return NORMAL;
    }
}
