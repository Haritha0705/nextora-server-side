package lk.iit.nextora.common.enums;

public enum NotificationType {
    GENERAL("General"),
    ANNOUNCEMENT("Announcement"),
    EVENT("Event"),
    VOTING_ALERT("Voting Alert"),
    SYSTEM("System"),
    SYSTEM_MESSAGE("System Message"),
    REMINDER("Reminder"),
    ALERT("Alert"),
    ASSIGNMENT("Assignment"),
    GRADE("Grade"),
    ATTENDANCE("Attendance"),
    MESSAGE("Message");

    private final String displayName;

    NotificationType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
