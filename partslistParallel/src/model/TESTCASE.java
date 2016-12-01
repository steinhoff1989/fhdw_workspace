package model;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

public class TESTCASE {

	@Test
	public void testMaterialChangePrice() {
		Material var = Material.create("Material", 10);
		var.changePrice(20);
		assertEquals(20, var.getPrice());

		var = Material.create("Material", 10);
		var.changePrice(-22);
		assertEquals(-22, var.getPrice());

		var = Material.create("Material", 0);
		var.changePrice(20);
		assertEquals(20, var.getPrice());

		var = Material.create("Material", -2);
		var.changePrice(6);
		assertEquals(6, var.getPrice());
	}

	@Test
	public void testProduktChangePrice() {
		Product product = Product.create("Fahrrad", 2);
		product.changePrice(20);
		assertEquals(20, product.getPrice());

		product = Product.create("Fahrrad", 2);
		product.changePrice(-5);
		assertEquals(-5, product.getPrice());
	}

	@Test
	public void testGetMaterialList() throws Exception {
		Material schraube = Material.create("Schraube", 1);

		Material schutzblech = Material.create("schutzblech", 3);
		Material felge = Material.create("felge", 3);
		Material radschlauch = Material.create("radschlauch", 2);
		Material ventil = Material.create("ventil", 1);
		Material mantel = Material.create("mantel", 3);
		Material speiche = Material.create("Speiche", 5);
		Product rad = Product.create("rad", 3);
		rad.addPart(schutzblech, 1);
		rad.addPart(felge, 1);
		rad.addPart(radschlauch, 1);
		rad.addPart(ventil, 1);
		rad.addPart(mantel, 1);
		rad.addPart(speiche, 24);
		rad.addPart(schraube, 5);

		Material dynamo = Material.create("dynamo", 1);
		Material frontlicht = Material.create("frontlicht", 2);
		Material reucklicht = Material.create("reucklicht", 2);
		Material kabel = Material.create("kabel", 1);
		Product licht = Product.create("licht", 3);
		licht.addPart(dynamo, 1);
		licht.addPart(frontlicht, 1);
		licht.addPart(reucklicht, 1);
		licht.addPart(kabel, 4);
		licht.addPart(schraube, 8);

		Material lenkstange = Material.create("lenkstange", 11);
		Material griff = Material.create("griff", 2);
		Material klingel = Material.create("klingel", 1);
		Product lenker = Product.create("lenker", 6);
		lenker.addPart(lenkstange, 1);
		lenker.addPart(griff, 2);
		lenker.addPart(klingel, 1);
		lenker.addPart(schraube, 7);

		Material frontschaltung = Material.create("frontschaltung", 2);
		Material ruekcschaltung = Material.create("ruekcschaltung", 2);
		Product schaltung = Product.create("schaltung", 6);
		schaltung.addPart(frontschaltung, 1);
		schaltung.addPart(ruekcschaltung, 1);
		schaltung.addPart(schraube, 10);

		Material sattel = Material.create("Sattel", 20);

		Product fahrrad = Product.create("Fahrrad", 24); // 24
		fahrrad.addPart(rad, 2);
		fahrrad.addPart(licht, 1);
		fahrrad.addPart(lenker, 1);
		fahrrad.addPart(schaltung, 1);
		fahrrad.addPart(sattel, 1);
		fahrrad.addPart(schraube, 33);

		Vector<QuantifiedComponent> expected = new Vector<QuantifiedComponent>();
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(2, schutzblech));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(2, felge));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(2, radschlauch));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(2, ventil));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(2, mantel));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(48, speiche));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, dynamo));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, frontlicht));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, reucklicht));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(4, kabel));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, lenkstange));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(2, griff));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, klingel));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, frontschaltung));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, ruekcschaltung));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, sattel));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(68, schraube));

		Collections.sort(expected);

		List<QuantifiedComponent> fahrradML = fahrrad.getMaterialList();
		Collections.sort(fahrradML);

		assertEquals(expected, fahrradML);
	}

	@Test
	public void getOverallPrice() throws Exception {
		Material schraube = Material.create("Schraube", 1);

		// KOSTEN insgesamt: 140
		Material schutzblech = Material.create("schutzblech", 3);
		Material felge = Material.create("felge", 3);
		Material radschlauch = Material.create("radschlauch", 2);
		Material ventil = Material.create("ventil", 1);
		Material mantel = Material.create("mantel", 3);
		Material speiche = Material.create("Speiche", 5);
		Product rad = Product.create("rad", 3);
		rad.addPart(schutzblech, 1);
		rad.addPart(felge, 1);
		rad.addPart(radschlauch, 1);
		rad.addPart(ventil, 1);
		rad.addPart(mantel, 1);
		rad.addPart(speiche, 24);
		rad.addPart(schraube, 5);

		// KOSTEN insgesamt:20
		Material dynamo = Material.create("dynamo", 1);
		Material frontlicht = Material.create("frontlicht", 2);
		Material reucklicht = Material.create("reucklicht", 2);
		Material kabel = Material.create("kabel", 1);
		Product licht = Product.create("licht", 3);
		licht.addPart(dynamo, 1);
		licht.addPart(frontlicht, 1);
		licht.addPart(reucklicht, 1);
		licht.addPart(kabel, 4);
		licht.addPart(schraube, 8);

		// KOSTEN insgesamt: 29
		Material lenkstange = Material.create("lenkstange", 11);
		Material griff = Material.create("griff", 2);
		Material klingel = Material.create("klingel", 1);
		Product lenker = Product.create("lenker", 6);
		lenker.addPart(lenkstange, 1);
		lenker.addPart(griff, 2);
		lenker.addPart(klingel, 1);
		lenker.addPart(schraube, 7);

		// KOSTEN insgesamt: 20
		Material frontschaltung = Material.create("frontschaltung", 2);
		Material ruekcschaltung = Material.create("ruekcschaltung", 2);
		Product schaltung = Product.create("schaltung", 6);
		schaltung.addPart(frontschaltung, 1);
		schaltung.addPart(ruekcschaltung, 1);
		schaltung.addPart(schraube, 10);

		Material sattel = Material.create("Sattel", 20);

		Product fahrrad = Product.create("Fahrrad", 24);
		fahrrad.addPart(rad, 2);
		fahrrad.addPart(licht, 1);
		fahrrad.addPart(lenker, 1);
		fahrrad.addPart(schaltung, 1);
		fahrrad.addPart(sattel, 1);
		fahrrad.addPart(schraube, 33);

		assertEquals("426", fahrrad.getOverallPrice());
	}

	@Test
	public void structureChanged() throws Exception {
		Material schraube = Material.create("Schraube", 1);

		// KOSTEN insgesamt:20
		Material dynamo = Material.create("dynamo", 1);
		Material frontlicht = Material.create("frontlicht", 2);
		Material reucklicht = Material.create("reucklicht", 2);
		Material kabel = Material.create("kabel", 1);
		Product licht = Product.create("licht", 3);
		licht.addPart(dynamo, 1);
		licht.addPart(frontlicht, 1);
		licht.addPart(reucklicht, 1);
		licht.addPart(kabel, 4);
		licht.addPart(schraube, 8);

		Material sattel = Material.create("Sattel", 20);

		Product fahrrad = Product.create("Fahrrad", 24);
		fahrrad.addPart(sattel, 1);
		assertEquals("44", fahrrad.getOverallPrice());

		fahrrad.addPart(sattel, 1);
		assertEquals("64", fahrrad.getOverallPrice());
	}

	@Test
	public void quantityChanged() throws Exception {
		Material schraube = Material.create("Schraube", 1);

		// KOSTEN insgesamt:20
		Material dynamo = Material.create("dynamo", 1);
		Material frontlicht = Material.create("frontlicht", 2);
		Material reucklicht = Material.create("reucklicht", 2);
		Material kabel = Material.create("kabel", 1);
		Product licht = Product.create("licht", 3);
		licht.addPart(dynamo, 1);
		licht.addPart(frontlicht, 1);
		licht.addPart(reucklicht, 1);
		licht.addPart(kabel, 4);
		licht.addPart(schraube, 8);

		Material sattel = Material.create("Sattel", 20);

		Product fahrrad = Product.create("Fahrrad", 24);
		fahrrad.addPart(sattel, 1);
		fahrrad.addPart(licht, 1);

		Vector<QuantifiedComponent> expected = new Vector<QuantifiedComponent>();
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, dynamo));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, frontlicht));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, reucklicht));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(4, kabel));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(8, schraube));
		expected.addElement(QuantifiedComponent.createQuantifiedComponent(1, sattel));

		Collections.sort(expected);

		List<QuantifiedComponent> fahrradML = fahrrad.getMaterialList();
		Collections.sort(fahrradML);

		assertEquals(expected, fahrradML);
		assertEquals("64", fahrrad.getOverallPrice());

		Vector<QuantifiedComponent> directFahrradParts = fahrrad.getDirectParts();
		Iterator<QuantifiedComponent> iterator = directFahrradParts.iterator();
		while (iterator.hasNext()) {
			QuantifiedComponent current = iterator.next();
			current.addQuantity(2);
		}

		Vector<QuantifiedComponent> expectedAfter = new Vector<QuantifiedComponent>();
		expectedAfter.addElement(QuantifiedComponent.createQuantifiedComponent(3, dynamo));
		expectedAfter.addElement(QuantifiedComponent.createQuantifiedComponent(3, frontlicht));
		expectedAfter.addElement(QuantifiedComponent.createQuantifiedComponent(3, reucklicht));
		expectedAfter.addElement(QuantifiedComponent.createQuantifiedComponent(12, kabel));
		expectedAfter.addElement(QuantifiedComponent.createQuantifiedComponent(24, schraube));
		expectedAfter.addElement(QuantifiedComponent.createQuantifiedComponent(3, sattel));

		Collections.sort(expectedAfter);

		List<QuantifiedComponent> fahrradMLAfter = fahrrad.getMaterialList();
		Collections.sort(fahrradMLAfter);

		assertEquals(expectedAfter, fahrradMLAfter);
		assertEquals("144", fahrrad.getOverallPrice());
	}

	@Test
	public void priceChanged() throws Exception {
		Material schraube = Material.create("Schraube", 1);

		// KOSTEN insgesamt:20
		Material dynamo = Material.create("dynamo", 1);
		Material frontlicht = Material.create("frontlicht", 2);
		Material reucklicht = Material.create("reucklicht", 2);
		Material kabel = Material.create("kabel", 1);
		Product licht = Product.create("licht", 3);
		licht.addPart(dynamo, 1);
		licht.addPart(frontlicht, 1);
		licht.addPart(reucklicht, 1);
		licht.addPart(kabel, 4);
		licht.addPart(schraube, 8);

		Material sattel = Material.create("Sattel", 20);

		Product fahrrad = Product.create("Fahrrad", 24);
		fahrrad.addPart(sattel, 1);
		assertEquals("44", fahrrad.getOverallPrice());
		fahrrad.changePrice(100);
		assertEquals("120", fahrrad.getOverallPrice());
	}
}
