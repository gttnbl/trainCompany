package traincompany.main;

import traincompany.service.TrainService;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TrainService service = new TrainService();
		service.readFile();
	}

}
