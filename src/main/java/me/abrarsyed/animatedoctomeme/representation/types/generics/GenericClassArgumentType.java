package me.abrarsyed.animatedoctomeme.representation.types.generics;

import me.abrarsyed.animatedoctomeme.representation.types.ClassType;

public class GenericClassArgumentType extends ClassType implements GenericArgument
{
    public final GenericArgumentVariant variant;

    public GenericClassArgumentType(String name, GenericArgumentVariant variant)
    {
        super(name);
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
