package me.abrarsyed.animatedoctomeme.representation.types.generics;

import me.abrarsyed.animatedoctomeme.representation.types.ClassType;

public interface GenericArgument
{
    public static final GenericArgument NONE = new GenericArgument() {
            public ClassType getType()
            {
                return new GenericVariableArgumentType("?", GenericArgumentVariant.EXACT);
            }
            
            public GenericArgumentVariant getVariant()
            {
                return GenericArgumentVariant.EXACT;
            }
        };
    
    public ClassType getType();

    public GenericArgumentVariant getVariant();
}
