package teretana;

import java.time.LocalTime;

public class ClickDelay {
	
	private LocalTime when;
	
	public ClickDelay(int seconds) {
		this(LocalTime.now().plusSeconds(seconds));
	}
	
	public ClickDelay(LocalTime when) {
		this.when = when;
	}
	
	public boolean clickable() {
		return LocalTime.now().isAfter(when);
	}
}
