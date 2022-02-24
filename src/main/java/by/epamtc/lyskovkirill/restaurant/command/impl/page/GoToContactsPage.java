package by.epamtc.lyskovkirill.restaurant.command.impl.page;

import by.epamtc.lyskovkirill.restaurant.bean.contact.Contact;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.Page;
import by.epamtc.lyskovkirill.restaurant.service.ContactService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for redirecting to Contacts page.
 *
 * @author k1ly
 */
public class GoToContactsPage implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.CONTACTS_PAGE.getPath());

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ContactService contactService = serviceProvider.getContactService();
        try {
            Optional<Contact> contact = contactService.findContact();
            contact.ifPresent(value -> request.setAttribute(CommandConstants.CONTACTS, value));
        } catch (ServiceException e) {
            throw new CommandException("Error during loading Contacts page", e);
        }
        return commandResult;
    }
}