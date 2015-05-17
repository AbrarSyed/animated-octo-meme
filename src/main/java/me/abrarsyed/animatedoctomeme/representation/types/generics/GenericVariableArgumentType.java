package me.abrarsyed.animatedoctomeme.representation.types.generics;

import me.abrarsyed.animatedoctomeme.representation.types.ClassType;
import me.abrarsyed.animatedoctomeme.representation.types.VariableType;

public class GenericVariableArgumentType extends VariableType implements GenericArgument
{
    public final GenericArgumentVariant variant;

    public GenericVariableArgumentType(String name, GenericArgumentVariant variant)
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
