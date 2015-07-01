package projecto4.grupo1.albertoricardo.validators;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator("filevalidate")
public class FileValidator implements Validator {

	public FileValidator() {
	}



	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Part file = (Part) value;
		String filename = file.getSubmittedFileName();
		
		String[] splt = filename.split("\\.");
		
		String extension = splt[splt.length-1];
		
		if (extension == null) return;
	

		if (!extension.equalsIgnoreCase("mp3")) {
			FacesMessage msg = new FacesMessage("Música", 
					"Formato não suportado.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}



}
