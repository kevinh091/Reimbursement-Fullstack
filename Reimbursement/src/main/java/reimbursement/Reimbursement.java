package reimbursement;

import java.sql.Blob;
import java.util.Date;

public class Reimbursement {
	private int id;
	private float amount;
	private Date submitted;
	private Date resolved;
	private String description;
	private Blob receipt;
	private int author;
	private int resolver;
}
