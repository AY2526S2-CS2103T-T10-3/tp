package seedu.hireshell.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.hireshell.commons.util.AppUtil.checkArgument;

/**
 * Represents additional details in the address book.
 * Guarantees: is valid as declared in {@link #isValidDetail(String)}
 */
public class Detail {
    public static final String MESSAGE_CONSTRAINTS =
        "Description should only contain alphanumeric characters, spaces, and valid special symbols";

    /*
     * Sanitizes <> and \ from the input
     * Allows blank strings
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9\\s.,!@#$%^&*()\\-=_+\\[\\]{}|;:'\"/?]*$";
    public final String fullDetails;

    /**
     * Constructs a {@code Detail}.
     *
     * @param details A valid description.
     */
    public Detail(String details) {
        requireNonNull(details);
        checkArgument(isValidDetail(details), MESSAGE_CONSTRAINTS);
        fullDetails = details;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDetail(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullDetails;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Detail)) {
            return false;
        }

        Detail otherDetail = (Detail) other;
        return fullDetails.equals(otherDetail.fullDetails);
    }

    @Override
    public int hashCode() {
        return fullDetails.hashCode();
    }


}
