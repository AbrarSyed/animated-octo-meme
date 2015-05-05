package me.abrarsyed.animatedoctomeme.representation.types;

public class PrimitiveType extends Type
{
    public PrimitiveType(String name)
    {
        super(name, true);
    }

    public boolean isVoid()
    {
        return name.equals("V");
    }

    public ArrayType asArray(int dimensions)
    {
        return new PrimitiveArrayType(name, dimensions);
    }

    public boolean isArray()
    {
        return false;
    }
}
