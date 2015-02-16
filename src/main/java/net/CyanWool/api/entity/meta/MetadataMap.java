package net.CyanWool.api.entity.meta;

public interface MetadataMap {

    public Object getMetadata(int id);

    public void setMetadata(int id, Object value);

    public Number getNumber(int id);

    public boolean getBit(int id, int bit);

    public void setBit(int id, int bit, boolean value);

    public void setBitOfInt(int id, int bit, boolean value);

    //public EntityMetadata[] getMetadataArray();

    //public EntityMetadata[] getChanges();
}