package projecto4.grupo1.albertoricardo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "ROLES")
public class RoleEntity implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    int id;


    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}