package Assignment2;

import org.objectweb.asm.Label;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;

public class GenIfElse {
	public static void main(String args[]) throws IOException{
        
        ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "IfElse", null, "java/lang/Object", null);

        //Create the class
		{
			MethodVisitor mv=cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(RETURN);
			mv.visitMaxs(0,0);
			mv.visitEnd();
		}
		
		MethodVisitor methodVisitor=cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        methodVisitor.visitCode();
        
        
        //Declare labels to Jump to
        Label alt = new Label();
        Label end = new Label();
        
        //Store int
        methodVisitor.visitIntInsn(Opcodes.BIPUSH, 0);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);
        
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        
        //If int == 0, jump to alt. Else, GOTO end
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, alt);
        methodVisitor.visitLdcInsn((String) "Number is NOT equal to Zero.");
        methodVisitor.visitVarInsn(Opcodes.ASTORE, 2);
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 2);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, end);
        
        methodVisitor.visitLabel(alt);
        //Load "Equal to Zero" string
        methodVisitor.visitLdcInsn((String) "Number is equal to Zero.");
        methodVisitor.visitVarInsn(Opcodes.ASTORE, 3);
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 3);
        
        methodVisitor.visitLabel(end);
        //Print greater var
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
        
        methodVisitor.visitInsn(Opcodes.RETURN);
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
        
        cw.visitEnd();
        
        //save bytecode into disk
		FileOutputStream out=new FileOutputStream("./IfElse.class");
		out.write(cw.toByteArray());
		out.close();
	}

}
