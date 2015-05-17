package me.abrarsyed.animatedoctomeme.representation.types.generics;

import me.abrarsyed.animatedoctomeme.representation.types.ClassType;
import me.abrarsyed.animatedoctomeme.representation.types.VariableArrayType;

public class GenericArrayArgumentType extends VariableArrayType implements GenericArgument
{
    public final GenericArgumentVariant variant;

    public GenericArrayArgumentType(String name, int dimensions, GenericArgumentVariant variant)
    {
        super(name, dimensions);
        this.variant = variant;
    }

    @Override
    public ClassType getType()
    {
        return this;
    }

    @Override
    public GenericArgumentVariant getVariant()
    {
        return variant;
    }
}
