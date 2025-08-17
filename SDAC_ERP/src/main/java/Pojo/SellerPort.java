package Pojo;

public class SellerPort {
    private String portID;
    private String password;
    private String role;

    public SellerPort(String portID, String password, String role) {
        this.portID = portID;
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "SellerPort{" +
                "portId='" + portID + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
