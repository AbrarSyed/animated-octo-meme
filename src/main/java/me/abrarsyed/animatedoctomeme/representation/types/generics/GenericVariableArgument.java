package me.abrarsyed.animatedoctomeme.representation.types.generics;

public class GenericVariableArgument extends GenericClassArgumentType
{
    public final boolean isGenericVariable = true;

    public GenericVariableArgument(String name, GenericArgumentVariant variant)
    {
        super(name, variant);
    }

}
