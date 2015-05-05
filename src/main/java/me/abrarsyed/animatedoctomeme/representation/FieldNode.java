package me.abrarsyed.animatedoctomeme.representation;

import java.util.List;

import me.abrarsyed.animatedoctomeme.representation.types.Type;
import me.abrarsyed.animatedoctomeme.util.TypeParser;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

import com.google.common.collect.Lists;

public class FieldNode extends FieldVisitor
{
    public final Type           type;
    public final String         name;
    public final int            access;
    public List<AnnotationNode> annotations = Lists.newArrayList();

    public FieldNode(Type type, String name, int access)
    {
        super(Opcodes.ASM5);
        this.type = type;
        this.name = name;
        this.access = access;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean runtimeVisible)
    {
        AnnotationNode annot = new AnnotationNode(TypeParser.parseClassType(desc), runtimeVisible);
        annotations.add(annot);
        return annot;
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible)
    {
        // TODO: these are more complicated than I expected
        return super.visitTypeAnnotation(typeRef, typePath, desc, visible);
    }
}
