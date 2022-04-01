package spbu.kotlin.shallow.plugin

import org.jetbrains.kotlin.ir.types.*

object Sizes {
    const val DEFAULT_SIZE = 8
    const val BOOLEAN_SIZE = 1
    const val UNIT_SIZE = 8
}

fun IrType.byteSize(): Int = when {
    this.isChar() -> Char.SIZE_BYTES
    this.isByte() -> Byte.SIZE_BYTES
    this.isShort() -> Short.SIZE_BYTES
    this.isInt() -> Int.SIZE_BYTES
    this.isLong() -> Long.SIZE_BYTES
    this.isUByte() -> UByte.SIZE_BYTES
    this.isUShort() -> UShort.SIZE_BYTES
    this.isULong() -> ULong.SIZE_BYTES
    this.isFloat() -> Float.SIZE_BYTES
    this.isDouble() -> Double.SIZE_BYTES
    this.isBoolean() -> Sizes.BOOLEAN_SIZE
    this.isUnit() -> Sizes.UNIT_SIZE
    else -> Sizes.DEFAULT_SIZE
}
