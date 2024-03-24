package teretana;

import java.time.LocalTime;

public class ClickDelay {
	
	private LocalTime when;
	
	public ClickDelay(int seconds) {
		this(LocalTime.now().plusSeconds(seconds));
	}
	
	public ClickDelay(double seconds) {
		this(LocalTime.now().plusNanos((long)(1000000000*seconds)));
	}
	
	public ClickDelay(LocalTime when) {
		this.when = when;
	}
	
	public boolean clickable() {
		return LocalTime.now().isAfter(when);
	}
}
