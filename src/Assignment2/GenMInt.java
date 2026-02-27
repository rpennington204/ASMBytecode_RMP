package Assignment2;

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;

@SuppressWarnings("unused")
public class GenMInt {
	
	public static void main(String args[]) throws IOException{
	        
	        ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
			cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "MInt", null, "java/lang/Object", null);
	
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
	        
	        //push 10 onto stack, store in 1
	        methodVisitor.visitIntInsn(Opcodes.BIPUSH, 10);
	        methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);
	        
	        //push 5 onto stack, store in 2
	        methodVisitor.visitIntInsn(Opcodes.BIPUSH, 5);
	        methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);
	        
	        //load variables 1 and 2
	        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
	        methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
	        
	        //multiply the variables, store in 3
	        methodVisitor.visitInsn(Opcodes.IMUL);
	        methodVisitor.visitVarInsn(Opcodes.ISTORE, 3);
	        
	        //print variable 3
	        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
	        methodVisitor.visitVarInsn(Opcodes.ILOAD, 3);
	        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(I)V", false);
	        
	        //push double 12.5, store it in 4 and 5
	        methodVisitor.visitLdcInsn((Double) 12.5);
	        methodVisitor.visitVarInsn(Opcodes.DSTORE, 4);
	        
	        //push double 2.0, store it in 6 and 7
	        methodVisitor.visitLdcInsn((Double) 2.0);
	        methodVisitor.visitVarInsn(Opcodes.DSTORE, 6);
	        
	        //multiply doubles, store in 8 and 9
	        methodVisitor.visitVarInsn(Opcodes.DLOAD,4);
	        methodVisitor.visitVarInsn(Opcodes.DLOAD,6);
	        methodVisitor.visitInsn(Opcodes.DMUL);
	        methodVisitor.visitVarInsn(Opcodes.DSTORE, 8);
	        
	        //Print var 8
	        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
	        methodVisitor.visitVarInsn(Opcodes.DLOAD, 8);
	        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(D)V", false);
	        
			methodVisitor.visitInsn(Opcodes.RETURN);
			methodVisitor.visitMaxs(0, 0);
			methodVisitor.visitEnd();
	        
	        cw.visitEnd();
	        
	        //save bytecode into disk
			FileOutputStream out=new FileOutputStream("./MInt.class");
			out.write(cw.toByteArray());
			out.close();
	        
	        
	}
	

}
