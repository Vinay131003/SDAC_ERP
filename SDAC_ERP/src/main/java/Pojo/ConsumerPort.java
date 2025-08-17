package Pojo;
public class ConsumerPort {
    private String portID;
    private String password;
    private String location;
    private String role;
public ConsumerPort() {
	
}
    public ConsumerPort(String portID, String password, String location, String role) {
        this.portID = portID;
        this.password = password;
        this.location = location;
        this.role = role;
    }

    // Getters and Setters
    public String getPortID() {
        return portID;
    }

    public void setPortID(String portID) {
        this.portID = portID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "ConsumerPort{" +
                "portId='" + portID + '\'' +
                ", location='" + location + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
