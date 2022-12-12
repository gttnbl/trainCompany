package traincompany.dtos;

import java.util.List;
import java.util.Objects;

public class Output {
	private long customerId;
	private long totalCostInCents;
	private List<TripsOutput> trips;
	
	
	

	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getTotalCostInCents() {
		return totalCostInCents;
	}
	public void setTotalCostInCents(long totalCostInCents) {
		this.totalCostInCents = totalCostInCents;
	}
	public List<TripsOutput> getTrips() {
		return trips;
	}
	public void setTrips(List<TripsOutput> trips) {
		this.trips = trips;
	}
	@Override
	public int hashCode() {
		return Objects.hash(customerId, totalCostInCents, trips);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Output other = (Output) obj;
		return customerId == other.customerId && totalCostInCents == other.totalCostInCents
				;
	}

	
	
	
}
