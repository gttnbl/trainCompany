package traincompany.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import traincompany.dtos.Output;
import traincompany.dtos.Tap;
import traincompany.dtos.Taps;
import traincompany.dtos.TripsOutput;





public class TrainService {

	public void readFile() {

		String data = "";
		try {
			File inputFile = new File(getProp("file.input"));
			Scanner myReader = new Scanner(inputFile);

			while (myReader.hasNextLine()) {
				data += myReader.nextLine();

			}
			myReader.close();
			manipulateData(data);

		} catch (FileNotFoundException e) {
			// System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public List<Output> manipulateData(String data) {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		List<Output> outputs = new ArrayList<>();
		Taps tapss;
		try {
			tapss = objectMapper.readValue(data, Taps.class);
			Map<Long, List<Tap>> groupByPriceMap = tapss.getTaps().stream()
					.collect(Collectors.groupingBy(Tap::getCustomerId));

			for (Map.Entry<Long, List<Tap>> entry : groupByPriceMap.entrySet()) {

				Output out = prepareOutput(entry);
				outputs.add(out);

			}

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WriteExportFile(outputs);
		return outputs;
	}

	public Output prepareOutput(Entry<Long, List<Tap>> entry) {
		// TODO Auto-generated method stub

		long id = entry.getKey();
		List<Tap> taps = entry.getValue();

		// order list entry by date
		taps.sort(Comparator.comparing(Tap::getUnixTimestamp));
		// regrouper deux par deux
		long totalCosts = 0;
		List<TripsOutput> tripsOutput = new ArrayList<TripsOutput>();
		for (int i = 0; i < taps.size(); i += 2) {
			Tap start = taps.get(i);
			Tap end = taps.get(i + 1);

			TripsOutput tripOutput = new TripsOutput();
			tripOutput.setStationStart(start.getStation());
			tripOutput.setStationEnd(end.getStation());
			tripOutput.setStartedJourneyAt(start.getUnixTimestamp());

			// start
			List<Long> zonesStart = getZone(start.getStation());
			if (zonesStart.size() == 1) {
				tripOutput.setZoneFrom(zonesStart.get(0));
			} else {
				long zoneStart = getZoneByCosts(zonesStart, getZone(end.getStation()));
				tripOutput.setZoneFrom(zoneStart);
			}

			// end
			List<Long> zones = getZone(end.getStation());
			if (zones.size() == 1) {
				tripOutput.setZoneTo(zones.get(0));
			} else {
				long zoneEnd = getZoneByCosts(zones, getZone(start.getStation()));
				tripOutput.setZoneTo(zoneEnd);
			}

			long costs = calculateCosts(tripOutput.getZoneFrom(), tripOutput.getZoneTo());
			totalCosts += costs;
			tripOutput.setCostInCents(costs);
			tripsOutput.add(tripOutput);

		}

		Output output = new Output();
		output.setCustomerId(id);

		output.setTotalCostInCents(totalCosts);
		output.setTrips(tripsOutput);

		return output;
	}

	public long calculateCosts(long zoneFrom, long zoneTo) {
		// TODO Auto-generated method stub

		long cost = 0;
		String traget = Long.toString(zoneFrom) + Long.toString(zoneTo);

		try {
			String prop = getProp(traget);
			if (prop != null) {
				cost = Long.parseLong(prop);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cost;
	}

	public List<Long> getZone(String station) {
		List<Long> zone = new ArrayList();
		if (station.equals("A") || station.equals("B")) {
			zone.add(1l);
		}
		if (station.equals("D")) {
			zone.add(2l);
		}

		if (station.equals("H") || station.equals("I") || station.equals("G")) {
			zone.add(4l);
		}

		else if (station.equals("C") || station.equals("E")) {
			// 2 3
			zone.add(3l);
			zone.add(2l);

		}

		else if (station.equals("F")) {
			// 3 4
			zone.add(3l);
			zone.add(4l);
		}

		return zone;

	}

	public long getZoneByCosts(List<Long> zonesFrom, List<Long> zonesTo) {
		// TODO Auto-generated method stub
		Map<Long, String> map = new HashMap<Long, String>();
		long key = 0;
		for (long v : zonesFrom) {

			for (long c : zonesTo) {
				String m = Long.toString(c) + Long.toString(v);
				map.put(v, getProp(m));

			}

		}
		Optional<Entry<Long, String>> firstKey = map.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.findFirst();

		if (firstKey.isPresent()) {
			key = firstKey.get().getKey();
		}
		return key;

	}

	public String WriteExportFile(List<Output> outputs) {
		// TODO Auto-generated method stub
		String json = new Gson().toJson(outputs);
		try {
			FileWriter myWriter = new FileWriter(getProp("file.output"));
			myWriter.write(json);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return json;

	}

	private String getProp(String props) {
		Properties prop = new Properties();
		File configFile = new File("src/main/ressources/myProp.properties");
		InputStream stream;
		try {
			stream = new FileInputStream(configFile);
			prop.load(stream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(props);
	}

}
