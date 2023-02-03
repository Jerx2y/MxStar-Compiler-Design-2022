package BackEnd;

import java.io.PrintStream;

public class BuiltinPrinter {
    PrintStream os;

    public BuiltinPrinter(PrintStream os) {
        this.os = os;
    }

    public void visit() {
        os.println("""
	.text
	.file	"builtin.c"
	.globl	print                   # -- Begin function print
	.p2align	2
	.type	print,@function
print:                                  # @print
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end0:
	.size	print, .Lfunc_end0-print
	.cfi_endproc
                                        # -- End function
	.globl	println                 # -- Begin function println
	.p2align	2
	.type	println,@function
println:                                # @println
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end1:
	.size	println, .Lfunc_end1-println
	.cfi_endproc
                                        # -- End function
	.globl	printInt                # -- Begin function printInt
	.p2align	2
	.type	printInt,@function
printInt:                               # @printInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end2:
	.size	printInt, .Lfunc_end2-printInt
	.cfi_endproc
                                        # -- End function
	.globl	printlnInt              # -- Begin function printlnInt
	.p2align	2
	.type	printlnInt,@function
printlnInt:                             # @printlnInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end3:
	.size	printlnInt, .Lfunc_end3-printlnInt
	.cfi_endproc
                                        # -- End function
	.globl	getString               # -- Begin function getString
	.p2align	2
	.type	getString,@function
getString:                              # @getString
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	addi	a0, zero, 256
	mv	a1, zero
	call	malloc
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	__isoc99_scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	getString, .Lfunc_end4-getString
	.cfi_endproc
                                        # -- End function
	.globl	getInt                  # -- Begin function getInt
	.p2align	2
	.type	getInt,@function
getInt:                                 # @getInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	addi	a1, sp, 8
	call	__isoc99_scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	getInt, .Lfunc_end5-getInt
	.cfi_endproc
                                        # -- End function
	.globl	toString                # -- Begin function toString
	.p2align	2
	.type	toString,@function
toString:                               # @toString
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	.cfi_offset ra, -4
	sw	a0, 24(sp)
	addi	a0, zero, 15
	mv	a1, zero
	call	malloc
	lw	a2, 12(sp)
	sw	a0, 16(sp)
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	call	sprintf
	lw	a0, 16(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end6:
	.size	toString, .Lfunc_end6-toString
	.cfi_endproc
                                        # -- End function
	.globl	__array_size            # -- Begin function __array_size
	.p2align	2
	.type	__array_size,@function
__array_size:                           # @__array_size
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	a0, 8(sp)
	lw	a0, -4(a0)
	addi	sp, sp, 16
	ret
.Lfunc_end7:
	.size	__array_size, .Lfunc_end7-__array_size
	.cfi_endproc
                                        # -- End function
	.globl	__string_length         # -- Begin function __string_length
	.p2align	2
	.type	__string_length,@function
__string_length:                        # @__string_length
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	call	strlen
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end8:
	.size	__string_length, .Lfunc_end8-__string_length
	.cfi_endproc
                                        # -- End function
	.globl	__string_substring      # -- Begin function __string_substring
	.p2align	2
	.type	__string_substring,@function
__string_substring:                     # @__string_substring
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	.cfi_offset ra, -4
	sw	a0, 24(sp)
	sw	a1, 20(sp)
	sw	a2, 16(sp)
	sub	a0, a2, a1
	addi	a0, a0, 1
	srai	a1, a0, 31
	call	malloc
	lw	a1, 24(sp)
	lw	a2, 20(sp)
	lw	a3, 16(sp)
	sw	a0, 8(sp)
	add	a1, a1, a2
	sub	a2, a3, a2
	call	memcpy
	lw	a0, 16(sp)
	lw	a1, 20(sp)
	lw	a2, 8(sp)
	sub	a0, a0, a1
	add	a0, a2, a0
	sb	zero, 0(a0)
	lw	a0, 8(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end9:
	.size	__string_substring, .Lfunc_end9-__string_substring
	.cfi_endproc
                                        # -- End function
	.globl	__string_parseInt       # -- Begin function __string_parseInt
	.p2align	2
	.type	__string_parseInt,@function
__string_parseInt:                      # @__string_parseInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	a0, 8(sp)
	sw	zero, 4(sp)
	sb	zero, 3(sp)
	lb	a0, 0(a0)
	addi	a1, zero, 45
	bne	a0, a1, .LBB10_2
# %bb.1:
	lw	a0, 8(sp)
	addi	a1, zero, 1
	sb	a1, 3(sp)
	addi	a0, a0, 1
	sw	a0, 8(sp)
.LBB10_2:
	addi	a0, zero, 48
	addi	a1, zero, 57
	addi	a2, zero, 10
.LBB10_3:                               # =>This Inner Loop Header: Depth=1
	lw	a3, 8(sp)
	lb	a3, 0(a3)
	beqz	a3, .LBB10_7
# %bb.4:                                #   in Loop: Header=BB10_3 Depth=1
	lw	a3, 8(sp)
	lb	a3, 0(a3)
	blt	a3, a0, .LBB10_7
# %bb.5:                                #   in Loop: Header=BB10_3 Depth=1
	lw	a3, 8(sp)
	lb	a3, 0(a3)
	blt	a1, a3, .LBB10_7
# %bb.6:                                #   in Loop: Header=BB10_3 Depth=1
	lw	a3, 8(sp)
	lw	a4, 4(sp)
	lb	a5, 0(a3)
	mul	a4, a4, a2
	add	a4, a5, a4
	addi	a4, a4, -48
	sw	a4, 4(sp)
	addi	a3, a3, 1
	sw	a3, 8(sp)
	j	.LBB10_3
.LBB10_7:
	lbu	a0, 3(sp)
	andi	a0, a0, 1
	beqz	a0, .LBB10_9
# %bb.8:
	addi	a0, zero, -1
	sw	a0, 4(sp)
.LBB10_9:
	lw	a0, 4(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end10:
	.size	__string_parseInt, .Lfunc_end10-__string_parseInt
	.cfi_endproc
                                        # -- End function
	.globl	__string_ord            # -- Begin function __string_ord
	.p2align	2
	.type	__string_ord,@function
__string_ord:                           # @__string_ord
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	a0, 8(sp)
	sw	a1, 4(sp)
	add	a0, a0, a1
	lb	a0, 0(a0)
	addi	sp, sp, 16
	ret
.Lfunc_end11:
	.size	__string_ord, .Lfunc_end11-__string_ord
	.cfi_endproc
                                        # -- End function
	.globl	__string_add            # -- Begin function __string_add
	.p2align	2
	.type	__string_add,@function
__string_add:                           # @__string_add
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	sw	s1, 20(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	.cfi_offset s1, -12
	sw	a0, 16(sp)
	sw	a1, 8(sp)
	call	strlen
	lw	a2, 8(sp)
	mv	s0, a0
	mv	s1, a1
	mv	a0, a2
	call	strlen
	add	a1, s1, a1
	add	a2, s0, a0
	sltu	a0, a2, s0
	add	a1, a1, a0
	addi	a0, a2, 1
	sltu	a2, a0, a2
	add	a1, a1, a2
	call	malloc
	sw	a0, 0(sp)
	sb	zero, 0(a0)
	lw	a0, 0(sp)
	lw	a1, 16(sp)
	call	strcat
	lw	a0, 0(sp)
	lw	a1, 8(sp)
	call	strcat
	lw	a0, 0(sp)
	lw	s1, 20(sp)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end12:
	.size	__string_add, .Lfunc_end12-__string_add
	.cfi_endproc
                                        # -- End function
	.globl	__string_eq             # -- Begin function __string_eq
	.p2align	2
	.type	__string_eq,@function
__string_eq:                            # @__string_eq
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	seqz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end13:
	.size	__string_eq, .Lfunc_end13-__string_eq
	.cfi_endproc
                                        # -- End function
	.globl	__string_ne             # -- Begin function __string_ne
	.p2align	2
	.type	__string_ne,@function
__string_ne:                            # @__string_ne
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	snez	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end14:
	.size	__string_ne, .Lfunc_end14-__string_ne
	.cfi_endproc
                                        # -- End function
	.globl	__string_lt             # -- Begin function __string_lt
	.p2align	2
	.type	__string_lt,@function
__string_lt:                            # @__string_lt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end15:
	.size	__string_lt, .Lfunc_end15-__string_lt
	.cfi_endproc
                                        # -- End function
	.globl	__string_le             # -- Begin function __string_le
	.p2align	2
	.type	__string_le,@function
__string_le:                            # @__string_le
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	slti	a0, a0, 1
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end16:
	.size	__string_le, .Lfunc_end16-__string_le
	.cfi_endproc
                                        # -- End function
	.globl	__string_gt             # -- Begin function __string_gt
	.p2align	2
	.type	__string_gt,@function
__string_gt:                            # @__string_gt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	sgtz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end17:
	.size	__string_gt, .Lfunc_end17-__string_gt
	.cfi_endproc
                                        # -- End function
	.globl	__string_ge             # -- Begin function __string_ge
	.p2align	2
	.type	__string_ge,@function
__string_ge:                            # @__string_ge
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	not	a0, a0
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end18:
	.size	__string_ge, .Lfunc_end18-__string_ge
	.cfi_endproc
                                        # -- End function
	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.1,@object        # @.str.1
.L.str.1:
	.asciz	"%s\\n"
	.size	.L.str.1, 4

	.type	.L.str.2,@object        # @.str.2
.L.str.2:
	.asciz	"%d"
	.size	.L.str.2, 3

	.type	.L.str.3,@object        # @.str.3
.L.str.3:
	.asciz	"%d\\n"
	.size	.L.str.3, 4

	.section	".note.GNU-stack","",@progbits
                """);
    }
}
