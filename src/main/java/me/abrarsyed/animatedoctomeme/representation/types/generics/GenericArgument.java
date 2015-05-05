package me.abrarsyed.animatedoctomeme.representation.types.generics;

import me.abrarsyed.animatedoctomeme.representation.types.ClassType;

public interface GenericArgument
{
    public ClassType getType();

    public GenericArgumentVariant getVariant();
}
