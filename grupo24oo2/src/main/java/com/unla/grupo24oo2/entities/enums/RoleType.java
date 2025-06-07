package com.unla.grupo24oo2.entities.enums;

public enum RoleType {
    ADMIN,
    EMPLEADO,
    CLIENTE;

    public String getPrefixedName() {
        return "ROLE_" + this.name();
    }
}
