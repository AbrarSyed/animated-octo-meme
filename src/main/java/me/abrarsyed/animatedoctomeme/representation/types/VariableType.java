package me.abrarsyed.animatedoctomeme.representation.types;

import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgument;
import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgumentVariant;
import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericVariableArgumentType;


public class VariableType extends ClassType
{
    public final boolean isVariable = true;
    
    public VariableType(String name)
    {
        super(name);
    }

    public ArrayType asArray(int dimensions)
    {
        return new ClassArrayType(name, dimensions);
    }

    public boolean isArray()
    {
        return false;
    }
    
    public GenericArgument asGenericArgument(GenericArgumentVariant variant)
    {
        return new GenericVariableArgumentType(name, variant);
    }
}
