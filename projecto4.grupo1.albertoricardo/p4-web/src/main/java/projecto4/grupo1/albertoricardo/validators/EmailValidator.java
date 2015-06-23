package projecto4.grupo1.albertoricardo.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import projecto4.grupo1.albertoricardo.UserEJBLocal;

@Named
public class EmailValidator implements Validator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";
	

	
	@EJB
	private UserEJBLocal userejb;

	private Pattern pattern;
	private Matcher matcher;


	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}



	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		matcher = pattern.matcher(value.toString());

		String email = (String) value;

		if (matcher == null) return;
	

		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("E-mail", 
					"Formato inválido e-mail");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

		if (userejb.getUserEntity(email) != null) {
			FacesMessage msg = new FacesMessage("E-mail", 
					"Utilizador já existente.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}



}
