package model;

//TODO Remove in final project!!!
public class FakeEntry implements Entry {

	public static Entry create(String name, int i) {
		return new FakeEntry(name + " : " + i);
	}
	private String fake;
	
	public FakeEntry(String fake) {
		this.fake = fake;
	}

	@Override
	public String toString() {
		return this.fake;
	}

	@Override
	public <T> T acceptEntryVisitor(EntryVisitor<T> entryVisitor) {
		return entryVisitor.handleFakeEntry(this);
	}

	public String getFake() {
		return this.fake;
	}

}
