package me.abrarsyed.animatedoctomeme.representation.types;

public class PrimitiveArrayType extends PrimitiveType implements ArrayType
{
    public final int dimensions;

    public PrimitiveArrayType(String name, int dimensions)
    {
        super(name);
        this.dimensions = dimensions;
    }

    @Override
    public int getDimensions()
    {
        return dimensions;
    }

    @Override
    public Type getType()
    {
        return this;
    }

    public ArrayType asArray(int dimensions)
    {
        return new PrimitiveArrayType(name, this.dimensions + dimensions);
    }

    public boolean isArray()
    {
        return true;
    }
}
