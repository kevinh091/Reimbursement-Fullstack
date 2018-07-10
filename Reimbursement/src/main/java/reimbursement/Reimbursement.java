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
	private int status;
	private int type;
	
	public Reimbursement(int id,float amount,Date submitted,Date resolved,String description,
			Blob receipt,int author,int resolver,int status, int type) 
	{
		this.id = id;
		this.amount = amount;
		this.submitted =submitted;
		this.resolved=resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver=resolver;
		this.status =status;
		this.type = type;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Date getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}
	public Date getResolved() {
		return resolved;
	}
	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Blob getReceipt() {
		return receipt;
	}
	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
