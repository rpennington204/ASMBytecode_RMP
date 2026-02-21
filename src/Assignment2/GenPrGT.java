package Assignment2;

import org.objectweb.asm.Label;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;

public class GenPrGT {
	public static void main(String args[]) throws IOException{
        
        ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "PrGT", null, "java/lang/Object", null);

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
        
        //Store 10 in 1
        methodVisitor.visitIntInsn(Opcodes.BIPUSH, 10);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);
        
        //Store 5 in 2
        methodVisitor.visitIntInsn(Opcodes.BIPUSH, 15);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);
        
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
        
        //If Var 2 < 1, jump to alt. Else, load var 1, and jump to end
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPLT, alt);
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, end);
        
        methodVisitor.visitLabel(alt);
        //Load var 2
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
        
        methodVisitor.visitLabel(end);
        //Print greater var
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(I)V", false);
        
        methodVisitor.visitInsn(Opcodes.RETURN);
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
        
        cw.visitEnd();
        
        //save bytecode into disk
		FileOutputStream out=new FileOutputStream("./PrGT.class");
		out.write(cw.toByteArray());
		out.close();
	}

}
