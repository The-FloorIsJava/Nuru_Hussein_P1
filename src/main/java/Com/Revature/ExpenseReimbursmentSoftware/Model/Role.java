package Com.Revature.ExpenseReimbursmentSoftware.Model;

public enum Role {
    Manager(" ");
    private String role;

    Role (String role) {
        this.role = role;

    }
    /**
     * This Allows to get a string value of role to send to DB
     * @return string value of enum
     */
    public String getRole() {
        return this.role;
    }

    /**
     * To fetch Enum value  of role to retrieve from Database
     * @param string from the Database
     * @return enum value
     */
    public static Role fromString(String string) {
        for(Role position: Role.values()) {
            if(position.getRole().equalsIgnoreCase(string)) {
                return position;
            }
        }
        return null;
    }
}

