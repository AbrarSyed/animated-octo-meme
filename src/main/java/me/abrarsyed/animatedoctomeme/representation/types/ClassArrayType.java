package me.abrarsyed.animatedoctomeme.representation.types;

import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgument;
import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgumentVariant;
import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArrayArgumentType;

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

    @Override
    public Type getType()
    {
        return this;
    }

    public ArrayType asArray(int dimensions)
    {
        return new ClassArrayType(name, this.dimensions + dimensions);
    }

    public boolean isArray()
    {
        return true;
    }
    
    public GenericArgument asGenericArgument(GenericArgumentVariant variant)
    {
        return new GenericArrayArgumentType(name, dimensions, variant);
    }
}
