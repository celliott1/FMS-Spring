package com.fms.facility.maintenance;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Inspection {
	public String toString();
	public String getDescription();
	public void setDescription(String description);
	public Date getDate();
	public void setDate(Date date);
}
