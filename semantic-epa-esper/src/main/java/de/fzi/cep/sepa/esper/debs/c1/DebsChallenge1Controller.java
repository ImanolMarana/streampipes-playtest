package de.fzi.cep.sepa.esper.debs.c1;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import de.fzi.cep.sepa.commons.Utils;
import de.fzi.cep.sepa.desc.EpDeclarer;
import de.fzi.cep.sepa.model.impl.Domain;
import de.fzi.cep.sepa.model.impl.EventGrounding;
import de.fzi.cep.sepa.model.impl.EventProperty;
import de.fzi.cep.sepa.model.impl.EventPropertyList;
import de.fzi.cep.sepa.model.impl.EventPropertyNested;
import de.fzi.cep.sepa.model.impl.EventPropertyPrimitive;
import de.fzi.cep.sepa.model.impl.EventSchema;
import de.fzi.cep.sepa.model.impl.EventStream;
import de.fzi.cep.sepa.model.impl.FreeTextStaticProperty;
import de.fzi.cep.sepa.model.impl.MappingPropertyUnary;
import de.fzi.cep.sepa.model.impl.StaticProperty;
import de.fzi.cep.sepa.model.impl.graph.SepaDescription;
import de.fzi.cep.sepa.model.impl.graph.SepaInvocation;
import de.fzi.cep.sepa.model.impl.output.FixedOutputStrategy;
import de.fzi.cep.sepa.model.impl.output.OutputStrategy;
import de.fzi.cep.sepa.model.util.SepaUtils;
import de.fzi.cep.sepa.model.vocabulary.XSD;

public class DebsChallenge1Controller extends EpDeclarer<DebsChallenge1Parameters>{

	private EventProperty xCellProperty;
	private EventProperty yCellProperty;
	
	@Override
	public SepaDescription declareModel() {
		List<String> domains = new ArrayList<String>();
		domains.add(Domain.DOMAIN_PERSONAL_ASSISTANT.toString());
		SepaDescription desc = new SepaDescription("/sepa/debs/c1", "DEBS Challenge v1",
				"Solves query 1 of the 2015 Debs Challenge", "", "/sepa/debs/c1", domains);
		try {
			EventStream stream1 = new EventStream();

			EventSchema schema1 = new EventSchema();
			List<EventProperty> eventProperties = new ArrayList<EventProperty>();
			EventProperty e1 = new EventPropertyPrimitive(de.fzi.cep.sepa.commons.Utils.createURI(
					"http://test.de/latitude"));
			EventProperty e2 = new EventPropertyPrimitive(de.fzi.cep.sepa.commons.Utils.createURI(
					"http://test.de/longitude"));
			EventProperty e3 = new EventPropertyPrimitive(de.fzi.cep.sepa.commons.Utils.createURI(
					"http://test.de/latitude"));
			EventProperty e4 = new EventPropertyPrimitive(de.fzi.cep.sepa.commons.Utils.createURI(
					"http://test.de/longitude"));
			eventProperties.add(e1);
			eventProperties.add(e2);
			eventProperties.add(e3);
			eventProperties.add(e4);
			
			schema1.setEventProperties(eventProperties);
			stream1.setEventSchema(schema1);
			stream1.setUri("http://localhost:8090/" + desc.getElementId());
			desc.addEventStream(stream1);

			List<OutputStrategy> outputStrategies = new ArrayList<OutputStrategy>();
			
			FixedOutputStrategy outputStrategy = new FixedOutputStrategy();

			EventPropertyList list = new EventPropertyList();
			list.setRuntimeName("list");
			
			
			List<EventProperty> appendProperties = new ArrayList<EventProperty>();			
			List<EventProperty> nestedProperties = new ArrayList<>();
			
			xCellProperty = new EventPropertyPrimitive(XSD._integer.toString(),
					"cellX", "", de.fzi.cep.sepa.commons.Utils.createURI("http://schema.org/Number"));
			yCellProperty = new EventPropertyPrimitive(XSD._integer.toString(),
					"cellY", "", de.fzi.cep.sepa.commons.Utils.createURI("http://schema.org/Number"));
			
			EventProperty latitudeNW = new EventPropertyPrimitive(XSD._double.toString(),
					"latitudeNW", "", de.fzi.cep.sepa.commons.Utils.createURI("http://test.de/latitude"));
			EventProperty longitudeNW = new EventPropertyPrimitive(XSD._double.toString(),
					"longitudeNW", "", de.fzi.cep.sepa.commons.Utils.createURI("http://test.de/longitude"));
			
			EventProperty latitudeSE = new EventPropertyPrimitive(XSD._double.toString(),
					"latitudeSE", "", de.fzi.cep.sepa.commons.Utils.createURI("http://test.de/latitude"));
			EventProperty longitudeSE = new EventPropertyPrimitive(XSD._double.toString(),
					"longitudeSE", "", de.fzi.cep.sepa.commons.Utils.createURI("http://test.de/longitude"));
			
			EventProperty cellSize = new EventPropertyPrimitive(XSD._integer.toString(),
					"cellSize", "", de.fzi.cep.sepa.commons.Utils.createURI("http://schema.org/Number"));
			
			nestedProperties.add(xCellProperty);
			nestedProperties.add(yCellProperty);
			nestedProperties.add(latitudeNW);
			nestedProperties.add(longitudeNW);
			nestedProperties.add(latitudeSE);
			nestedProperties.add(longitudeSE);
			nestedProperties.add(cellSize);
			
			EventProperty cellProperties = new EventPropertyNested("cellOptions", nestedProperties);
			EventProperty cell1Properties = new EventPropertyNested("cellOptions1", nestedProperties);
			EventProperty countValue = new EventPropertyPrimitive(XSD._integer.toString(),
					"countValue", "", de.fzi.cep.sepa.commons.Utils.createURI("http://schema.org/Number"));
			
			appendProperties.add(cellProperties);
			appendProperties.add(cell1Properties);
			appendProperties.add(countValue);

			list.setEventProperties(appendProperties);
			
			outputStrategy.setEventProperties(Utils.createList(list));
			outputStrategies.add(outputStrategy);
			desc.setOutputStrategies(outputStrategies);
			
			List<StaticProperty> staticProperties = new ArrayList<StaticProperty>();
			
			staticProperties.add(new FreeTextStaticProperty("cellSize", "The size of a cell in meters"));
			staticProperties.add(new FreeTextStaticProperty("startingLatitude", "The latitude value of the center of the first cell"));
			staticProperties.add(new FreeTextStaticProperty("startingLongitude", "The longitude value of the center of the first cell"));
			
			// Mapping properties
			staticProperties.add(new MappingPropertyUnary(new URI(e1.getElementName()), "latitude", "Select Latitude Mapping (Pickup)"));
			staticProperties.add(new MappingPropertyUnary(new URI(e2.getElementName()), "longitude", "Select Longitude Mapping (Pickup)"));
			staticProperties.add(new MappingPropertyUnary(new URI(e3.getElementName()), "latitude2", "Select Latitude Mapping (Dropoff)"));
			staticProperties.add(new MappingPropertyUnary(new URI(e4.getElementName()), "longitude2", "Select Longitude Mapping (Dropoff)"));
			desc.setStaticProperties(staticProperties);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return desc;
	}

	@Override
	public boolean invokeRuntime(SepaInvocation sepa) {
		
		int cellSize = Integer.parseInt(((FreeTextStaticProperty) (SepaUtils
				.getStaticPropertyByName(sepa, "cellSize"))).getValue());
		
		double startingLatitude = Double.parseDouble(((FreeTextStaticProperty) (SepaUtils
				.getStaticPropertyByName(sepa, "startingLatitude"))).getValue());
		
		double startingLongitude = Double.parseDouble(((FreeTextStaticProperty) (SepaUtils
				.getStaticPropertyByName(sepa, "startingLongitude"))).getValue());
		
		String latPropertyName = SepaUtils.getMappingPropertyName(sepa,
				"latitude");
		
		String lngPropertyName = SepaUtils.getMappingPropertyName(sepa,
				"longitude");	
		
		String latProperty2Name = SepaUtils.getMappingPropertyName(sepa,
				"latitude2");
		
		String lngProperty2Name = SepaUtils.getMappingPropertyName(sepa,
				"longitude2");	

		List<String> selectProperties = new ArrayList<>();
		for(EventProperty p : sepa.getInputStreams().get(0).getEventSchema().getEventProperties())
		{
			selectProperties.add(p.getRuntimeName());
		}
		
		DebsChallenge1Parameters staticParam = new DebsChallenge1Parameters(
				sepa, 
				startingLatitude, startingLongitude, 
				cellSize, 
				latPropertyName, 
				lngPropertyName,
				latProperty2Name, 
				lngProperty2Name,
				selectProperties);
	
		try {
			invokeEPRuntime(staticParam, DebsChallenge1::new, sepa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(new TaxiDataInputProvider(staticParam.getInputStreamParams().get(0).getInName())).start();
		return false;
	}
}
