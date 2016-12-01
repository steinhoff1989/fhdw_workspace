package model;

import java.util.List;

public class GetMaterialListCommand implements Command {

	private List<QuantifiedComponent> result;
	private Throwable t;
	private final QuantifiedComponent receiver;

	public GetMaterialListCommand(final QuantifiedComponent current) {
		this.receiver = current;
	}

	@Override
	synchronized public void execute() {
		try {
			this.result = this.receiver.getMaterialList2();
		} catch (final Throwable t) {
			this.t = t;
		} finally {
			this.notify();
		}
	}

	@Override
	synchronized public List<QuantifiedComponent> getResult() throws InterruptedException {
		while (this.result == null && this.t == null) {
			this.wait();
		}
		return this.result;
	}
}
