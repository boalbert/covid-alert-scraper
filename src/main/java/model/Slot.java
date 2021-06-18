package model;

public class Slot {
	public String heading;
	public String linkText;
	public String linkHref;
	public String openSlots;
	public String updated;

	public Slot() {
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public Slot(String heading, String linkText, String linkHref, String openSlots, String updated) {
		this.heading = heading;
		this.linkText = linkText;
		this.linkHref = linkHref;
		this.openSlots = openSlots;
		this.updated = updated;
	}

	public String getHeading() {
		return heading;
	}

	@Override
	public String toString() {
		return "Slot{" +
				"heading='" + heading + '\'' +
				", linkText='" + linkText + '\'' +
				", linkHref='" + linkHref + '\'' +
				", openSlots='" + openSlots + '\'' +
				", updated='" + updated + '\'' +
				'}';
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getLinkHref() {
		return linkHref;
	}

	public void setLinkHref(String linkHref) {
		this.linkHref = linkHref;
	}

	public String getOpenSlots() {
		return openSlots;
	}

	public void setOpenSlots(String openSlots) {
		this.openSlots = openSlots;
	}
}
