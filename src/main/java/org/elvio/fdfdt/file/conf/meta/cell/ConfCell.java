package org.elvio.fdfdt.file.conf.meta.cell;

public abstract class ConfCell <T> implements Cell{

	protected T value;
	protected String name = "";
	
	public void setValue(T value){
		this.value = value;
	}

	public T getValue() {
		return value;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	public abstract void extract(String value);
}
