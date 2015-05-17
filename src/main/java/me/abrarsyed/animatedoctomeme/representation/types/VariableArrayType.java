package me.abrarsyed.animatedoctomeme.representation.types;

import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgument;
import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgumentVariant;
import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericVariableArrayArgumentType;

public class VariableArrayType extends VariableType implements ArrayType
{
    public final int dimensions;

    public VariableArrayType(String name, int dimensions)
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
        return new VariableArrayType(name, this.dimensions + dimensions);
    }

    public boolean isArray()
    {
        return true;
    }
    
    public GenericArgument asGenericArgument(GenericArgumentVariant variant)
    {
        return new GenericVariableArrayArgumentType(name, dimensions, variant);
    }
}
