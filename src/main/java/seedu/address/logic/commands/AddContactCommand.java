package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.person.Person;

/**
 * Adds a contact to an application identified using it's displayed index from the list of internship applications.
 */
public class AddContactCommand extends Command {

    public static final String COMMAND_WORD = "add_contact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds contact details to the specified application from the list of internships applied.\n"
            + "Parameters: "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com ";

    public static final String MESSAGE_ADD_CONTACT_SUCCESS = "Contact details added to application: %1$s";

    private final Index targetIndex;

    private final Contact toAdd;

    public AddContactCommand(Index targetIndex, Contact contact) {
        this.targetIndex = targetIndex;
        toAdd = contact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Person internshipToAddContact = lastShownList.get(targetIndex.getZeroBased());
        model.addContactToInternship(internshipToAddContact, toAdd);
        return new CommandResult(String.format(MESSAGE_ADD_CONTACT_SUCCESS, internshipToAddContact));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddContactCommand // instanceof handles nulls
                && targetIndex.equals(((AddContactCommand) other).targetIndex)
                && toAdd.equals(((AddContactCommand) other).toAdd)); // state check
    }
}
