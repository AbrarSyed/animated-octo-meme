package me.abrarsyed.animatedoctomeme.representation;

import java.util.List;
import java.util.Map;

import me.abrarsyed.animatedoctomeme.representation.types.ClassType;
import me.abrarsyed.animatedoctomeme.util.TypeParser;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class AnnotationNode extends AnnotationVisitor
{
    public final boolean             isRuntime;
    public final ClassType           type;
    public final Map<String, Object> values;
    private transient List<String>   arrayKeys = Lists.newArrayList();

    public AnnotationNode(ClassType type, boolean isRuntime)
    {
        super(Opcodes.ASM5);
        this.type = type;
        this.isRuntime = isRuntime;
        this.values = Maps.newHashMap();
    }

    @Override
    public void visit(String name, Object value)
    {
        values.put(name, value);
    }

    @Override
    public void visitEnum(String name, String desc, String value)
    {
        EnumValue val = new EnumValue(TypeParser.parseClassType(desc), value);
        values.put(name, val);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String name, String desc)
    {
        AnnotationNode node = new AnnotationNode(TypeParser.parseClassType(desc), isRuntime);
        values.put(name, node);
        return node;
    }

    @Override
    public AnnotationVisitor visitArray(String name)
    {
        ArrayVisitor value = new ArrayVisitor(isRuntime);
        values.put(name, value);
        arrayKeys.add(name); // so we can repalce the arrayVisitors with their lists later
        return value;
    }

    @Override
    public void visitEnd()
    {
        for (String key : arrayKeys)
        {
            values.put(key, ((ArrayVisitor) values.get(key)).values);
        }
    }

    public static class EnumValue
    {
        public final ClassType type;
        public final String    value;

        public EnumValue(ClassType type, String value)
        {
            this.type = type;
            this.value = value;
        }
    }

    private class ArrayVisitor extends AnnotationVisitor
    {
        private final List<Object> values = Lists.newArrayList();

        public ArrayVisitor(boolean isRuntime)
        {
            super(Opcodes.ASM5);
        }

        @Override
        public void visit(String name, Object value)
        {
            values.add(value);
        }

        @Override
        public void visitEnum(String name, String desc, String value)
        {
            values.add(value);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String name, String desc)
        {
            AnnotationNode node = new AnnotationNode(TypeParser.parseClassType(desc), isRuntime);
            values.add(node);
            return node;
        }

        @Override
        public AnnotationVisitor visitArray(String name)
        {
            ArrayVisitor value = new ArrayVisitor(isRuntime);
            values.add(value);
            return value;
        }

        @Override
        public void visitEnd()
        {
            for (int i = 0; i < values.size(); i++)
            {
                Object obj = values.get(i);
                if (obj.getClass() == ArrayVisitor.class) // this should work??
                {
                    values.set(i, ((ArrayVisitor) obj).values);
                }
            }
        }
    }
}
