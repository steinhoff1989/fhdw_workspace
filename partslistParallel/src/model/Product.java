package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Product extends ComponentCommon implements Observer {

	private static final String CycleMessage = "Zyklen sind in der Aufbaustruktur nicht erlaubt!";

	public static Product create(final String name, final int price) {
		return new Product(name, price, new HashMap<String, QuantifiedComponent>());
	}

	private final HashMap<String, QuantifiedComponent> components;

	protected Product(final String name, final int price, final HashMap<String, QuantifiedComponent> components) {
		super(name, price);
		this.components = components;
	}

	@Override
	public void addPart(final ComponentCommon part, final int amount) throws Exception {
		if (part.contains(this))
			throw new Exception(CycleMessage);
		final String partName = part.getName();
		if (this.getComponents().containsKey(partName)) {
			final QuantifiedComponent oldQuantification = this.getComponents().get(partName);
			oldQuantification.addQuantity(amount);
		} else {
			final QuantifiedComponent quantifiedComponent = QuantifiedComponent.createQuantifiedComponent(amount, part);
			this.getComponents().put(partName, quantifiedComponent);
			quantifiedComponent.register(this);
		}
		this.notifyObserver(new StructureChangedEvent());
	}

	private HashMap<String, QuantifiedComponent> getComponents() {
		return this.components;
	}

	@Override
	public boolean contains(final Component component) {
		if (this.equals(component))
			return true;
		final Iterator<QuantifiedComponent> i = this.getComponents().values().iterator();
		while (i.hasNext()) {
			final QuantifiedComponent current = i.next();
			if (current.contains(component))
				return true;
		}
		return false;
	}

	@Override
	public Vector<QuantifiedComponent> getDirectParts() {
		return new Vector<QuantifiedComponent>(this.getComponents().values());
	}

	@Override
	public int getNumberOfMaterials() {
		int result = 0;
		final Iterator<QuantifiedComponent> i = this.getComponents().values().iterator();
		while (i.hasNext()) {
			final QuantifiedComponent current = i.next();
			result = result + current.getNumberOfMaterials();
		}
		return result;
	}

	@Override
	public List<QuantifiedComponent> getMaterialList2() {
		final MaterialList result = new MaterialList();

		final Collection<QuantifiedComponent> componentsList = this.components.values();
		final Iterator<QuantifiedComponent> componentIterator = componentsList.iterator();
		final List<GetMaterialListCommand> calls = new ArrayList<GetMaterialListCommand>();

		while (componentIterator.hasNext()) {
			final QuantifiedComponent current = componentIterator.next();

//			for (int i = 0; i < current.getQuantity(); i++) {
				final GetMaterialListCommand getMaterialListCommand = new GetMaterialListCommand(current);
				calls.add(getMaterialListCommand);

				new Thread(new Runnable() {
					@Override
					public void run() {
						getMaterialListCommand.execute();
					}
				}).start();
//			}
		}
		final Iterator<GetMaterialListCommand> callsIterator = calls.iterator();
		while (callsIterator.hasNext()) {
			final GetMaterialListCommand current = callsIterator.next();
			try {
					result.add(current.getResult());
			} catch (final InterruptedException e) {
				// TODO: throw or handle InterruptedException
			}
		}
		return result.toList();
	}

	// @Override
	// public List<QuantifiedComponent> getMaterialList2() {
	// final MaterialList result = new MaterialList();
	//
	// final Collection<QuantifiedComponent> componentsList =
	// this.components.values();
	// final Iterator<QuantifiedComponent> componentIterator =
	// componentsList.iterator();
	// while (componentIterator.hasNext()){
	// final QuantifiedComponent current = componentIterator.next();
	//// for(int i=0; i<current.getQuantity();i++){
	// result.add(current.getMaterialList2());
	//// }
	// }
	// return result.toList();
	// }

	@Override
	public String getOverallPrice2() {
		int result = 0;
		final List<QuantifiedComponent> quantifiedMaterials = this.getDirectParts();
		final Iterator<QuantifiedComponent> i = quantifiedMaterials.iterator();
		while (i.hasNext()) {
			result += Integer.valueOf(i.next().getOverallPrice());
		}
		result += this.getPrice();
		return Integer.toString(result);
	}

	@Override
	public void update(final ComponentEvent e) {
		e.accept(new EventVisitor() {

			@Override
			public void handlePriceChangedEvent(final PriceChangedEvent e) {
				Product.this.state.priceChanged(Product.this);
			}

			@Override
			public void handleStructureChangedEvent(final StructureChangedEvent e) {
				Product.this.state.structureChanged(Product.this);

			}

		});
		this.setState(NCState.getInstance());
	}
}
