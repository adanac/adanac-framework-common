package com.adanac.framework.comm;

public class TreeNode implements Comparable<TreeNode> {

	private String id;
	private String pId;
	private String name;
	private String action_url;
	private String isParent;
	private String open;
	private String flag;
	private Boolean checked = false;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction_url() {
		return action_url;
	}

	public void setAction_url(String actionUrl) {
		action_url = actionUrl;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public int compareTo(TreeNode o) {
		return Long.valueOf(this.getId()).intValue() - Long.valueOf(o.getId()).intValue();
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
