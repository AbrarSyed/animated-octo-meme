package me.abrarsyed.animatedoctomeme.representation.types;

public class ClassArrayType extends ClassType implements ArrayType
{
    public final int dimensions;

    public ClassArrayType(String name, int dimensions)
    {
        super(name);
        this.dimensions = dimensions;
    }

    @Override
    public int getDimensions()
    {
        return dimensions;
    }
}
