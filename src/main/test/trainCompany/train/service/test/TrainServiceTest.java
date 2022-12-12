package trainCompany.train.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import traincompany.dtos.Output;
import traincompany.dtos.TripsOutput;
import traincompany.service.TrainService;


public class TrainServiceTest {

	TrainService trainService = new TrainService();

	static String data;
	static String stationA;
	static String stationB;
	static String stationC;
	static String stationD;
	static String stationE;
	static String stationF;
	static String stationG;
	static String stationH;
	static String stationI;

	static Output out1;
	static Output out2;
	static List<Output> outputs;
	List<Long> zonesFrom = new ArrayList<Long>(Arrays.asList(3l, 2l));
	List<Long> zonesTo = new ArrayList<Long>(Arrays.asList(1l));;

	@BeforeAll
	static void beforeAll() {
		System.out.println("**--- Executed once before all test methods in this class ---**");
		stationA = "A";
		outputs = new ArrayList<>();

		out1 = new Output();
		out1.setCustomerId(1l);
		out1.setTotalCostInCents(480);
		List<TripsOutput> trips1 = new ArrayList();

		TripsOutput tr1 = new TripsOutput();
		tr1.setCostInCents(240);
		tr1.setStartedJourneyAt(1572242400);
		tr1.setStationEnd(stationD);
		tr1.setStationStart(stationA);
		tr1.setZoneFrom(1);
		tr1.setZoneTo(2);
		trips1.add(tr1);
		TripsOutput tr2 = new TripsOutput();
		tr2.setCostInCents(240);
		tr2.setStartedJourneyAt(1572282000);
		tr2.setStationEnd(stationA);
		tr2.setStationStart(stationD);
		tr2.setZoneFrom(2);
		tr2.setZoneTo(1);
		trips1.add(tr2);
		out1.setTrips(trips1);

		out2 = new Output();
		out2.setCustomerId(2l);
		out2.setTotalCostInCents(480);
		List<TripsOutput> trips2 = new ArrayList();
		TripsOutput tr3 = new TripsOutput();
		tr3.setCostInCents(240);
		tr3.setStartedJourneyAt(1572242400);
		tr3.setStationEnd(stationD);
		tr3.setStationStart(stationA);
		tr3.setZoneFrom(1);
		tr3.setZoneTo(2);
		trips2.add(tr3);
		TripsOutput tr4 = new TripsOutput();

		tr4.setCostInCents(240);
		tr4.setStartedJourneyAt(1572282000);
		tr4.setStationEnd(stationA);
		tr4.setStationStart(stationD);
		tr4.setZoneFrom(2);
		tr4.setZoneTo(1);
		trips2.add(tr4);
		out2.setTrips(trips2);
		outputs.add(out1);
		outputs.add(out2);
		data = "{\r\n" + "  \"taps\": [\r\n" + "    {\r\n" + "	  \"unixTimestamp\": 1572242400,\r\n"
				+ "	  \"customerId\": 1,\r\n" + "	  \"station\": \"A\"\r\n" + "	},\r\n" + "	{\r\n"
				+ "	  \"unixTimestamp\": 1572244200,\r\n" + "	  \"customerId\": 1,\r\n"
				+ "	  \"station\": \"D\"\r\n" + "	},\r\n" + "	{\r\n" + "	  \"unixTimestamp\": 1572282000,\r\n"
				+ "	  \"customerId\": 1,\r\n" + "	  \"station\": \"D\"\r\n" + "	},\r\n" + "	{\r\n"
				+ "	  \"unixTimestamp\": 1572283800,\r\n" + "	  \"customerId\": 1,\r\n"
				+ "	  \"station\": \"A\"\r\n" + "	},\r\n" + "\r\n" + "\r\n" + "{\r\n"
				+ "	  \"unixTimestamp\": 1572242400,\r\n" + "	  \"customerId\": 2,\r\n"
				+ "	  \"station\": \"A\"\r\n" + "	},\r\n" + "	{\r\n" + "	  \"unixTimestamp\": 1572244200,\r\n"
				+ "	  \"customerId\": 2,\r\n" + "	  \"station\": \"D\"\r\n" + "	},\r\n" + "	{\r\n"
				+ "	  \"unixTimestamp\": 1572282000,\r\n" + "	  \"customerId\": 2,\r\n"
				+ "	  \"station\": \"D\"\r\n" + "	},\r\n" + "	{\r\n" + "	  \"unixTimestamp\": 1572283800,\r\n"
				+ "	  \"customerId\": 2,\r\n" + "	  \"station\": \"A\"\r\n" + "	}\r\n" + "  ]\r\n" + "}";
	}

	@AfterAll
	static void afterAll() {
		System.out.println("**--- Executed once after all test methods in this class ---**");
	}

	@Test
	void readFile() {
		System.out.println("**--- Test method1 executed ---**");
	}

	@Test
	void manipulateData() {
		System.out.println("**--- Test method1 executed ---**");

		List<Output> outs = trainService.manipulateData(data);
		assertEquals(outs.get(0), outputs.get(0));
		assertEquals(outs.get(1), outputs.get(1));
	}

	@Test
	void prepareOutput() {
		System.out.println("**--- Test method1 executed ---**");
	}

	@Test
	void calculateCosts() {
		System.out.println("**--- Test method1 executed ---**");
		long cost = trainService.calculateCosts(1l, 3l);
		assertEquals(cost, 280l);

	}

	@Test
	void getZone() {
		List<Long> zones = trainService.getZone(stationA);
		assertEquals(1l, zones.get(0));
	}

	@Test
	void getZoneByCosts() {
		long zones = trainService.getZoneByCosts(zonesFrom, zonesTo);
		assertEquals(2l, zones);

	}

	@Test
	void WriteExportFile() {
		String json = trainService.WriteExportFile(outputs);
	}

}
