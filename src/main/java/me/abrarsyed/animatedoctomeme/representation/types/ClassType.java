package me.abrarsyed.animatedoctomeme.representation.types;

import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgument;
import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgumentVariant;
import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericClassArgumentType;

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
    
    public GenericArgument asGenericArgument(GenericArgumentVariant variant)
    {
        return new GenericClassArgumentType(name, variant);
    }
}
