package net.devwool.cyanwool.api.management;

import java.util.UUID;

public interface PlayerData {

    public String getName();

    public UUID getId();

    public String getIdAsString();

    public String getValue();

    public void setValue(String value);

    public void setName(String name);// Possible ?

}