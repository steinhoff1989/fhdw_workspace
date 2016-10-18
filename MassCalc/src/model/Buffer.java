package model;

public class Buffer<E> extends AbstractBuffer<E> {

	public Buffer() {
		super();
	}

	// vorne raus
	synchronized public E get() throws StoppException, DivideByZeroException {
		// eigentlich if, aber hier while, da das Betriebssystem manchmal das
		// wait aufwekcen könnte
		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		E result = this.implementingList.get(0).getWrapped();

		this.implementingList.remove(0);
		return result;
	}

}
