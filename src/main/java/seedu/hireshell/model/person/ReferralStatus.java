package seedu.hireshell.model.person;

public enum ReferralStatus {
    REFERRED,
    NOT_REFERRED, DEFAULT_REFERRAL_STATUS;

    public static final String MESSAGE_CONSTRAINTS = "Referral Status must be 'yes' or 'no', and it should not be blank";

    public static boolean isValidReferralStatus(String referralStatus) {
        String trimmedReferralStatus = referralStatus.trim().toLowerCase();
        return trimmedReferralStatus.equals("yes") || trimmedReferralStatus.equals("no");
    }

    public static ReferralStatus fromString(String referralStatus) {
        String trimmedReferralStatus = referralStatus.trim().toLowerCase();
        return trimmedReferralStatus.equals("yes") ? REFERRED : NOT_REFERRED;
    }

    @Override
    public String toString() {
        return switch (this) {
            case REFERRED -> "Yes";
            case NOT_REFERRED -> "No";
            default -> "";
        };
    }

    public boolean isReferred() {
        return this == REFERRED;
    }

}