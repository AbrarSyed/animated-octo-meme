package me.abrarsyed.animatedoctomeme.util;

import me.abrarsyed.animatedoctomeme.representation.types.*;
import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgument;
import me.abrarsyed.animatedoctomeme.representation.types.generics.GenericArgumentVariant;

public final class TypeParser
{
    //@formatter:off
    private TypeParser() { }
    //@formatter:on
    
    public static Type parseType(String str)
    {
        StringCursor cursor = new StringCursor(str);
        return parseType(cursor);
    }
    
    public static Type parseType(StringCursor cursor)
    {
        if (cursor.charAt() == 'L' || cursor.charAt() == 'T')
        {
            return parseClassType(cursor);
        }
        else if (cursor.charAt() == '[')
        {
            return (Type) parseArrayType(cursor);
        }
        else
        {
            String primitive = ""+cursor.charAt();
            cursor.increment();
            return parsePrimitiveType(primitive);
        }
    }
    
    public static ClassType parseClassType(String str)
    {
        return parseClassType(new StringCursor(str));
    }
    
    public static PrimitiveType parsePrimitiveType(String str)
    {
        return new PrimitiveType(str);
    }
    
    private static ArrayType parseArrayType(StringCursor cursor)
    {
        int dimensions = 0;
        while (cursor.charAt() == '[')
        {
            dimensions++;
            cursor.increment();
        }
        
        if (cursor.charAt() == 'L')
        {
            return parseClassType(cursor).asArray(dimensions);
        }
        else
        {
            return new PrimitiveArrayType(""+cursor.charAt(), dimensions);
        }
    }
    
    /**
     * WIl increment the cursor to just beyond this type.
     * @param cur StringCursor pointing at start of type
     * @return parsed out type
     */
    public static ClassType parseClassType(StringCursor cur)
    {
        if (cur.charAt() == 'T')
        {
            return new VariableType(cur.substringTill(';'));
        }
        else if (cur.charAt() != 'L')
            throw new IllegalStateException("Cursor is not pointing to the beginning of a class!");
        
        cur.increment(); // skip the L
        
        String className = cur.substringTill('<', ';');
        
        if (cur.charAt() == ';') // end of the class... nice.
        {
            cur.increment();
            return new ClassType(className);
        }
            
        // parse the generics
        GenericClassType out = new GenericClassType(className);
        
        char type;
        while ((type = cur.charAt()) != '>') // loop until its done.
        {
            if (type == '*')
            {
                out.generics.add(GenericArgument.NONE);
                cur.increment();
                continue;
            }
            
            GenericArgumentVariant variant;
            switch (type)
            {
                case '+':
                    variant = GenericArgumentVariant.EXTENDS;
                    cur.increment(); // we dont want the current char in the class ref
                    break;
                case '-':
                    variant = GenericArgumentVariant.SUPER;
                    cur.increment(); // we dont want the current char in the class ref
                    break;
                default:
                    variant = GenericArgumentVariant.EXACT;
                    // we want to include the current char in the class ref
                    cur.decrement();
                    break;
            }
            
            out.generics.add(parseClassType(cur).asGenericArgument(variant));
        }
        cur.increment(); // after the '>'
        
        return out;
    }

//       THIS HERE IS A BUNCH OF CODE i WROTE AND ADAPTED FROM THE JAVAASSIST LIBRARY.
//        I will get arroudn to doing stuff with this later.
//
//
//
//
//
//    public static TypeClass parseType(StringCursor cur)
//    {
//        TypeClass out = new TypeClass(cur.substringTill('<', ';'));
//        
//        if (cur.charAt() == '<')
//        {
//            char type;
//            while ((type = cur.charAt()) != '>') {
//                TypeGeneric ta;
//                switch(type)
//                {
//                    case '*':
//                        ta = new TypeGeneric("?", GenericScope.EXACT);
//                        break;
//                    case '+':
//                        ta = new TypeGeneric("?", GenericScope.EXTENDS);
//                        break;
//                    case '-':
//                        ta = new TypeGeneric("?", GenericScope.SUPER);
//                    default:
//                        ta = new TypeGeneric("?", GenericScope.EXACT);
//                }
//                if (type == '*' )
//                    ta = new TypeGeneric("?", GenericScope.EXACT);
//                else {
//                    if (type != '+' && type != '-') {
//                        type = ' ';
//                        c.position--;
//                    }
//
//                    ta = new TypeArgument(parseObjectType(sig, c, false), type);
//                }
//
//                generics.add(ta);
//            }
//        }
//        
//        return out;
//    }
//    
//
//    public static TypeGeneric parseGeneric(String sig)
//    {
//        return parseGeneric(new StringCursor(sig, 0));
//    }
//
//    public static TypeGeneric parseGeneric(StringCursor cursor)
//    {
//        // this had BETTER start with <
//        if (cursor.skipWhitespace().charAt() != '<')
//            throw new IllegalArgumentException("Gotta start with <");
//
//        cursor.increment();
//
//        while (cursor.charAt() != '>')
//        {
//            String name = cursor.substringTill(':');
//            
//            // generics cant be primitives
//            String boundClass = cursor.substringTill(';');
//            
//            Gernric gen = new TypeGeneric(name, new TypeClass(boundClass));
//            
//            ObjectType classBound = parseObjectType(sig, cursor, true);
//            ArrayList ifBound = new ArrayList();
//            while (sig.charAt(cursor.position) == ':')
//            {
//                cursor.position++;
//                ObjectType t = parseObjectType(sig, cursor, false);
//                ifBound.add(t);
//            }
//
//            TypeGeneric p = new TypeGeneric(sig, nameBegin, nameEnd,
//                    classBound, (ObjectType[]) ifBound.toArray(new ObjectType[ifBound.size()]));
//            typeParam.add(p);
//        }
//
//        return null;
//    }
//
//    private static ClassSignature parseSig2(String sig)
//    {
//        Cursor cur = new Cursor();
//        TypeGeneric[] tp = parseTypeParams(sig, cur);
//        ClassVariant superClass = parseClassType(sig, cur);
//        int sigLen = sig.length();
//        ArrayList ifArray = new ArrayList();
//        while (cur.position < sigLen && sig.charAt(cur.position) == 'L')
//            ifArray.add(parseClassType(sig, cur));
//
//        ClassVariant[] ifs = (ClassVariant[]) ifArray.toArray(new ClassVariant[ifArray.size()]);
//        return new ClassSignature(tp, superClass, ifs);
//    }
//
//    private static TypeGeneric[] parseTypeParams(String sig, Cursor cur)
//    {
//        ArrayList typeParam = new ArrayList();
//        if (sig.charAt(cur.position) == '<')
//        {
//            cur.position++;
//            while (sig.charAt(cur.position) != '>')
//            {
//                int nameBegin = cur.position;
//                int nameEnd = cur.indexOf(sig, ':');
//                ObjectType classBound = parseObjectType(sig, cur, true);
//                ArrayList superInterfaces = new ArrayList();
//                while (sig.charAt(cur.position) == ':')
//                {
//                    cur.position++;
//                    ObjectType t = parseObjectType(sig, cur, false);
//                    superInterfaces.add(t);
//                }
//
//                TypeGeneric p = new TypeGeneric(sig, nameBegin, nameEnd,
//                        classBound, (ObjectType[]) superInterfaces.toArray(new ObjectType[superInterfaces.size()]));
//                typeParam.add(p);
//            }
//
//            cur.position++;
//        }
//
//        return (TypeGeneric[]) typeParam.toArray(new TypeGeneric[typeParam.size()]);
//    }
//
//    private static ObjectType parseObjectType(String sig, Cursor c, boolean dontThrow)
//            throws BadBytecode
//    {
//        int i;
//        int begin = c.position;
//        switch (sig.charAt(begin))
//            {
//                case 'L':
//                    return parseClassType2(sig, c, null);
//                case 'T':
//                    i = c.indexOf(sig, ';');
//                    return new TypeVariable(sig, begin + 1, i);
//                case '[':
//                    return parseArray(sig, c);
//                default:
//                    if (dontThrow)
//                        return null;
//                    else
//                        throw error(sig);
//            }
//    }
//
//    /**
//     * Formal type parameters.
//     */
//    public static class TypeParameter
//    {
//        String       name;
//        ObjectType   superClass;
//        ObjectType[] superInterfaces;
//
//        TypeParameter(String sig, int nb, int ne, ObjectType sc, ObjectType[] si)
//        {
//            name = sig.substring(nb, ne);
//            superClass = sc;
//            superInterfaces = si;
//        }
//
//        /**
//         * Returns the name of the type parameter.
//         */
//        public String getName()
//        {
//            return name;
//        }
//
//        /**
//         * Returns the class bound of this parameter.
//         * @return null if the class bound is not specified.
//         */
//        public ObjectType getClassBound()
//        {
//            return superClass;
//        }
//
//        /**
//         * Returns the interface bound of this parameter.
//         * @return a zero-length array if the interface bound is not specified.
//         */
//        public ObjectType[] getInterfaceBound()
//        {
//            return superInterfaces;
//        }
//
//        /**
//         * Returns the string representation.
//         */
//        public String toString()
//        {
//            StringBuffer sbuf = new StringBuffer(getName());
//            if (superClass != null)
//                sbuf.append(" extends ").append(superClass.toString());
//
//            int len = superInterfaces.length;
//            if (len > 0)
//            {
//                for (int i = 0; i < len; i++)
//                {
//                    if (i > 0 || superClass != null)
//                        sbuf.append(" & ");
//                    else
//                        sbuf.append(" extends ");
//
//                    sbuf.append(superInterfaces[i].toString());
//                }
//            }
//
//            return sbuf.toString();
//        }
//
//        static void toString(StringBuffer sbuf, TypeGeneric[] tp)
//        {
//            sbuf.append('<');
//            for (int i = 0; i < tp.length; i++)
//            {
//                if (i > 0)
//                    sbuf.append(", ");
//
//                sbuf.append(tp[i]);
//            }
//
//            sbuf.append('>');
//        }
//    }

}
