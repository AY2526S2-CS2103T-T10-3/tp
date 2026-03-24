package seedu.hireshell.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.hireshell.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hireshell.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.hireshell.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.hireshell.logic.commands.FilterCommand;
import seedu.hireshell.logic.parser.exceptions.ParseException;
import seedu.hireshell.model.person.PersonMatchesFiltersPredicate;
import seedu.hireshell.model.person.PersonMatchesFiltersPredicate.RatingFilter;
import seedu.hireshell.model.person.Rating;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    private static final Pattern RATING_FILTER_PATTERN = Pattern.compile("(?<operator>[><]=?|==)?\\s*(?<value>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RATING, PREFIX_STATUS);

        if (!argMultimap.getValue(PREFIX_RATING).isPresent() && !argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_RATING, PREFIX_STATUS);

        Optional<RatingFilter> ratingFilter = Optional.empty();
        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            ratingFilter = Optional.of(parseRatingFilter(argMultimap.getValue(PREFIX_RATING).get()));
        }

        Optional<String> statusFilter = argMultimap.getValue(PREFIX_STATUS);

        return new FilterCommand(new PersonMatchesFiltersPredicate(ratingFilter, statusFilter));
    }

    private RatingFilter parseRatingFilter(String ratingArg) throws ParseException {
        Matcher matcher = RATING_FILTER_PATTERN.matcher(ratingArg.trim());
        if (!matcher.matches()) {
            throw new ParseException(Rating.MESSAGE_CONSTRAINTS);
        }

        String operatorStr = matcher.group("operator");
        String valueStr = matcher.group("value");

        if (!Rating.isValidRating(valueStr)) {
            throw new ParseException(Rating.MESSAGE_CONSTRAINTS);
        }

        double value = Double.parseDouble(valueStr);
        RatingFilter.Operator operator = parseOperator(operatorStr);

        return new RatingFilter(operator, value);
    }

    private RatingFilter.Operator parseOperator(String operatorStr) {
        if (operatorStr == null || operatorStr.equals("==")) {
            return RatingFilter.Operator.EQUAL;
        }
        switch (operatorStr) {
        case ">":
            return RatingFilter.Operator.GREATER_THAN;
        case ">=":
            return RatingFilter.Operator.GREATER_THAN_OR_EQUAL;
        case "<":
            return RatingFilter.Operator.LESS_THAN;
        case "<=":
            return RatingFilter.Operator.LESS_THAN_OR_EQUAL;
        default:
            return RatingFilter.Operator.EQUAL;
        }
    }
}
