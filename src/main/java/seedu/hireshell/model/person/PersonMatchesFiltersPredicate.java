package seedu.hireshell.model.person;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.hireshell.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Rating} and {@code Status} matches the filters given.
 */
public class PersonMatchesFiltersPredicate implements Predicate<Person> {
    private final Optional<RatingFilter> ratingFilter;
    private final Optional<String> statusFilter;

    /**
     * Represents a rating filter with an operator and a value.
     */
    public static class RatingFilter {
        public enum Operator {
            GREATER_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN, LESS_THAN_OR_EQUAL, EQUAL
        }

        private final Operator operator;
        private final double value;

        public RatingFilter(Operator operator, double value) {
            this.operator = operator;
            this.value = value;
        }

        public boolean test(double ratingValue) {
            switch (operator) {
            case GREATER_THAN:
                return ratingValue > value;
            case GREATER_THAN_OR_EQUAL:
                return ratingValue >= value;
            case LESS_THAN:
                return ratingValue < value;
            case LESS_THAN_OR_EQUAL:
                return ratingValue <= value;
            case EQUAL:
                return Math.abs(ratingValue - value) < 0.0001;
            default:
                return false;
            }
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof RatingFilter)) {
                return false;
            }
            RatingFilter otherFilter = (RatingFilter) other;
            return operator == otherFilter.operator && value == otherFilter.value;
        }

        @Override
        public String toString() {
            return operator + " " + value;
        }
    }

    public PersonMatchesFiltersPredicate(Optional<RatingFilter> ratingFilter, Optional<String> statusFilter) {
        this.ratingFilter = ratingFilter;
        this.statusFilter = statusFilter;
    }

    @Override
    public boolean test(Person person) {
        boolean matchesRating = ratingFilter.map(filter -> filter.test(person.getRating().value)).orElse(true);
        boolean matchesStatus = statusFilter.map(status ->
                person.getStatus().value.equalsIgnoreCase(status)).orElse(true);
        return matchesRating && matchesStatus;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonMatchesFiltersPredicate)) {
            return false;
        }

        PersonMatchesFiltersPredicate otherPredicate = (PersonMatchesFiltersPredicate) other;
        return ratingFilter.equals(otherPredicate.ratingFilter)
                && statusFilter.equals(otherPredicate.statusFilter);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("ratingFilter", ratingFilter)
                .add("statusFilter", statusFilter)
                .toString();
    }
}
