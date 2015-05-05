package me.abrarsyed.animatedoctomeme.representation.types;

public class ClassType extends Type
{
    public ClassType(String name)
    {
        super(name, false);
    }

    public ArrayType asArray(int dimensions)
    {
        return new ClassArrayType(name, dimensions);
    }

    public boolean isArray()
    {
        return false;
    }
}
