import java.util.EventObject;

public class DetailEvent extends EventObject {
	
	private String tune;
	private String file;
	
	public DetailEvent(Object source, String tune, String file) {
		super(source);
		
		this.tune = tune;
		this.file = file;
	}
	
	public String getTune() {
		return tune;
	}
	
	public String getFile() {
		return file;
	}
}
