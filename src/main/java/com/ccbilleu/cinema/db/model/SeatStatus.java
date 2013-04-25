package com.ccbilleu.cinema.db.model;

public enum SeatStatus {
	FREE("FREE"), OCCUPIED("OCCUPIED");
	
	private String value;
	
	SeatStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
        return value;
    }
	
	@Override
    public String toString() {
        return this.getValue();
    }
}
