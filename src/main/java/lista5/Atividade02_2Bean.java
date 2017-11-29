package lista5;


import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean()
@SessionScoped
public class Atividade02_2Bean implements Serializable {

	private static final long serialVersionUID = -8363296722786327504L;

	private Date date1;
	private String option;

	public Atividade02_2Bean() {

	}

	public void proxima(ActionEvent event) throws IOException {
		date1 = (Date) event.getComponent().getAttributes().get("date1");
		option = (String) event.getComponent().getAttributes().get("option");
		if (!option.equals("F")) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("ex2_2.xhtml");
		} else {
			FacesContext.getCurrentInstance().getExternalContext().dispatch("ex2_2.xhtml");
		}
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