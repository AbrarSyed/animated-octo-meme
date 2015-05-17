package me.abrarsyed.animatedoctomeme.representation.types.generics;

import me.abrarsyed.animatedoctomeme.representation.types.ClassArrayType;
import me.abrarsyed.animatedoctomeme.representation.types.ClassType;

public class GenericVariableArrayArgumentType extends ClassArrayType implements GenericArgument
{
    public final GenericArgumentVariant variant;

    public GenericVariableArrayArgumentType(String name, int dimensions, GenericArgumentVariant variant)
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
