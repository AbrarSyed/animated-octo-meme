package me.abrarsyed.animatedoctomeme.representation.types;

import java.util.List;

import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgument;

import com.google.common.collect.Lists;

public class GenericClassType extends ClassType
{
    public final List<GenericArgument> generics = Lists.newArrayList();

    public GenericClassType(String name)
    {
        super(name);
    }

    public ArrayType asArray(int dimensions)
    {
        GenericClassArrayType out = new GenericClassArrayType(name, dimensions);
        out.generics.addAll(generics);
        return out;
    }
}
