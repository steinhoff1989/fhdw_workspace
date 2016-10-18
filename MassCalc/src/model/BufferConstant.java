package model;

public class BufferConstant<E> extends AbstractBuffer<E> {

	public BufferConstant() {
		super();
	}

	@Override
	public E get() throws StoppException, DivideByZeroException {
		// eigentlich if, aber hier while, da das Betriebssystem manchmal das
		// wait aufwecken könnte
		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		E result = this.implementingList.get(0).getWrapped();
		return result;
	}

}
