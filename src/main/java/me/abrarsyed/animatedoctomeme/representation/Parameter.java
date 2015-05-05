package me.abrarsyed.animatedoctomeme.representation;

import java.util.List;

import me.abrarsyed.animatedoctomeme.representation.types.ClassType;

import com.google.common.collect.Lists;

public class Parameter
{
    public final String          name;
    public final ClassType       superType;
    public final List<ClassType> superInterfaces;

    public Parameter(String name, ClassType boundClass)
    {
        this.name = name;
        superType = boundClass;
        superInterfaces = Lists.newArrayList();
    }

    public Parameter(String name)
    {
        this.name = name;
        superType = null;
        superInterfaces = null;
    }
}
