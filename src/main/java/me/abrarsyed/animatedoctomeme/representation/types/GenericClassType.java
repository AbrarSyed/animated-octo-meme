package me.abrarsyed.animatedoctomeme.representation.types;

import java.util.List;

import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgument;

import com.google.common.collect.Lists;

public class GenericClassType extends ClassType
{
    public static final List<GenericArgument> generics = Lists.newArrayList();
    
    public GenericClassType(String name)
    {
        super(name);
    }
}
