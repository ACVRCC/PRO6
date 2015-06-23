package projecto4.grupo1.albertoricardo.validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import projecto4.grupo1.albertoricardo.UserEJBLocal;

@Named
public class UserValidator implements Validator {

	@EJB
	private UserEJBLocal userejb;


	public UserValidator() {
	}



	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String email = (String) value;

		UIInput passwordComponent = (UIInput) component.getAttributes().get("pword");
		String pw = (String) passwordComponent.getSubmittedValue();

		if (email == null || pw == null) return;
		
		if (userejb.getName(email) == null) {
			FacesMessage msg = new FacesMessage("Login", 
					"Utilizador inexistente.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

		if (!userejb.verifyLogin(email, pw)) {
			FacesMessage msg = new FacesMessage("Login", 
					"E-mail ou Password incorrecta.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}



}
