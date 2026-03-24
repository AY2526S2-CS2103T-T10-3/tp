package seedu.hireshell.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hireshell.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DetailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Detail(null));
    }

    @Test
    public void constructor_invalidDetail_throwsIllegalArgumentException() {
        String invalidDetail = "<script>";
        assertThrows(IllegalArgumentException.class, () -> new Detail(invalidDetail));
    }

    @Test
    public void isValidDetail() {
        // null Detail
        assertThrows(NullPointerException.class, () -> Detail.isValidDetail(null));

        // invalid Detail
        assertFalse(Detail.isValidDetail("<>")); // banned symbols
        assertFalse(Detail.isValidDetail("peter\\")); // contains banned symbols

        // valid Detail
        assertTrue(Detail.isValidDetail("Met at career fair")); // alphabets only
        assertTrue(Detail.isValidDetail("Last contacted on 12/02/26")); // numbers only
        assertTrue(Detail.isValidDetail("Part of summer 2026 intake, last contact on 26/01/26")); // long Details
    }

    @Test
    public void equals() {
        Detail detail = new Detail("Valid Detail");

        // same values -> returns true
        assertTrue(detail.equals(new Detail("Valid Detail")));

        // same object -> returns true
        assertTrue(detail.equals(detail));

        // null -> returns false
        assertFalse(detail.equals(null));

        // different types -> returns false
        assertFalse(detail.equals(5.0f));

        // different values -> returns false
        assertFalse(detail.equals(new Detail("Other Valid Detail")));
    }

    @Test
    public void validToString() {
        Detail detail = new Detail("Valid Detail");
        assertEquals(detail.toString(), "Valid Detail");
    }

}
