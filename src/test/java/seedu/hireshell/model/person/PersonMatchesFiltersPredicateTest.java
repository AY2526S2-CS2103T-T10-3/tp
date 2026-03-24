package seedu.hireshell.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.hireshell.model.person.PersonMatchesFiltersPredicate.RatingFilter;

public class PersonMatchesFiltersPredicateTest {

    @Test
    public void test_matchesRating() {
        PersonMatchesFiltersPredicate predicate = new PersonMatchesFiltersPredicate(
                Optional.of(new RatingFilter(RatingFilter.Operator.GREATER_THAN_OR_EQUAL, 7.0)),
                Optional.empty());

        Person personWithRating7 = new Person(new Name("Alice"), new Phone("12345678"),
                new Email("alice@example.com"), new Rating("7.0"), new Status("Applied"),
                new HashSet<>(), ReferralStatus.fromString("yes"));
        assertTrue(predicate.test(personWithRating7));

        Person personWithRating8 = new Person(new Name("Bob"), new Phone("87654321"),
                new Email("bob@example.com"), new Rating("8.5"), new Status("Applied"),
                new HashSet<>(), ReferralStatus.fromString("yes"));
        assertTrue(predicate.test(personWithRating8));

        Person personWithRating6 = new Person(new Name("Charlie"), new Phone("11111111"),
                new Email("charlie@example.com"), new Rating("6.9"), new Status("Applied"),
                new HashSet<>(), ReferralStatus.fromString("yes"));
        assertFalse(predicate.test(personWithRating6));
    }

    @Test
    public void test_matchesStatus() {
        PersonMatchesFiltersPredicate predicate = new PersonMatchesFiltersPredicate(
                Optional.empty(),
                Optional.of("Interviewing"));

        Person personInterviewing = new Person(new Name("Alice"), new Phone("12345678"),
                new Email("alice@example.com"), new Rating("7.0"), new Status("Interviewing"),
                new HashSet<>(), ReferralStatus.fromString("yes"));
        assertTrue(predicate.test(personInterviewing));

        Person personApplied = new Person(new Name("Bob"), new Phone("87654321"),
                new Email("bob@example.com"), new Rating("8.5"), new Status("Applied"),
                new HashSet<>(), ReferralStatus.fromString("yes"));
        assertFalse(predicate.test(personApplied));
    }

    @Test
    public void test_matchesBoth() {
        PersonMatchesFiltersPredicate predicate = new PersonMatchesFiltersPredicate(
                Optional.of(new RatingFilter(RatingFilter.Operator.LESS_THAN, 5.0)),
                Optional.of("Rejected"));

        Person personRejectedPoorRating = new Person(new Name("Alice"), new Phone("12345678"),
                new Email("alice@example.com"), new Rating("4.5"), new Status("Rejected"),
                new HashSet<>(), ReferralStatus.fromString("yes"));
        assertTrue(predicate.test(personRejectedPoorRating));

        Person personRejectedGoodRating = new Person(new Name("Bob"), new Phone("87654321"),
                new Email("bob@example.com"), new Rating("8.5"), new Status("Rejected"),
                new HashSet<>(), ReferralStatus.fromString("yes"));
        assertFalse(predicate.test(personRejectedGoodRating));
    }
}
