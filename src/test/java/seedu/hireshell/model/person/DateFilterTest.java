package seedu.hireshell.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.hireshell.model.person.PersonMatchesFiltersPredicate.DateFilter;

public class DateFilterTest {

    @Test
    public void test_before() {
        LocalDate filterDate = LocalDate.of(2026, 4, 1);
        DateFilter filter = new DateFilter(DateFilter.Operator.BEFORE, filterDate);

        // Date before
        assertTrue(filter.test(LocalDateTime.of(2026, 3, 31, 23, 59)));
        // Same date, different time
        assertFalse(filter.test(LocalDateTime.of(2026, 4, 1, 0, 0)));
        // Date after
        assertFalse(filter.test(LocalDateTime.of(2026, 4, 2, 0, 0)));
    }

    @Test
    public void test_after() {
        LocalDate filterDate = LocalDate.of(2026, 4, 1);
        DateFilter filter = new DateFilter(DateFilter.Operator.AFTER, filterDate);

        // Date before
        assertFalse(filter.test(LocalDateTime.of(2026, 3, 31, 23, 59)));
        // Same date, different time
        assertFalse(filter.test(LocalDateTime.of(2026, 4, 1, 0, 0)));
        // Date after
        assertTrue(filter.test(LocalDateTime.of(2026, 4, 2, 0, 0)));
    }
}
