package traincompany.dtos;

import java.util.Objects;

public class TripsOutput {
	private String stationStart;
	private String stationEnd;
	private long startedJourneyAt;
	
	private long costInCents;
	private long zoneFrom;
	private long zoneTo;
	public String getStationStart() {
		return stationStart;
	}
	public void setStationStart(String stationStart) {
		this.stationStart = stationStart;
	}
	public String getStationEnd() {
		return stationEnd;
	}
	public void setStationEnd(String stationEnd) {
		this.stationEnd = stationEnd;
	}

	public long getStartedJourneyAt() {
		return startedJourneyAt;
	}
	public void setStartedJourneyAt(long startedJourneyAt) {
		this.startedJourneyAt = startedJourneyAt;
	}

	public long getCostInCents() {
		return costInCents;
	}
	public void setCostInCents(long costInCents) {
		this.costInCents = costInCents;
	}
	public long getZoneFrom() {
		return zoneFrom;
	}
	public void setZoneFrom(long zoneFrom) {
		this.zoneFrom = zoneFrom;
	}
	public long getZoneTo() {
		return zoneTo;
	}
	public void setZoneTo(long zoneTo) {
		this.zoneTo = zoneTo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(costInCents, startedJourneyAt, stationEnd, stationStart, zoneFrom, zoneTo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripsOutput other = (TripsOutput) obj;
		return costInCents == other.costInCents && startedJourneyAt == other.startedJourneyAt
				&& Objects.equals(stationEnd, other.stationEnd) && Objects.equals(stationStart, other.stationStart)
				&& zoneFrom == other.zoneFrom && zoneTo == other.zoneTo;
	}

	
	
	
	
}
