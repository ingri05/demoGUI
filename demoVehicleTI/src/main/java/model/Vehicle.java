package model;

public class Vehicle {

    private String id;
    private String vehicleType;
    private String status;
    private String assignedIncident;

    public Vehicle(String id, String vehicleType,
                   String status, String assignedIncident) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.status = status;
        this.assignedIncident = assignedIncident;
    }

    public String getId() {
        return id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getStatus() {
        return status;
    }

    public String getAssignedIncident() {
        return assignedIncident;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAssignedIncident(String assignedIncident) {
        this.assignedIncident = assignedIncident;
    }
}
