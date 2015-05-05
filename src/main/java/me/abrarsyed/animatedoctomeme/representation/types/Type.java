package me.abrarsyed.animatedoctomeme.representation.types;

public abstract class Type
{
    public final String  name;
    public final boolean isPrimitive;

    public Type(String name, boolean isPrimitive)
    {
        super();
        this.name = name;
        this.isPrimitive = isPrimitive;
    }

    abstract ArrayType asArray(int dimensions);

    abstract boolean isArray();
}
