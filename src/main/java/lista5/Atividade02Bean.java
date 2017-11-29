package lista5;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean()
public class Atividade02Bean implements Serializable {

	private static final long serialVersionUID = -8363296722786327504L;

	private Date date1;
	private String option;

	public Atividade02Bean() {

	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

}