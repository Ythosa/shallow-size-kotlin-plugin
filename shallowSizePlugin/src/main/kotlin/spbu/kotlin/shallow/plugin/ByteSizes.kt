package spbu.kotlin.shallow.plugin

import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.isBoolean
import org.jetbrains.kotlin.ir.types.isByte
import org.jetbrains.kotlin.ir.types.isChar
import org.jetbrains.kotlin.ir.types.isDouble
import org.jetbrains.kotlin.ir.types.isFloat
import org.jetbrains.kotlin.ir.types.isInt
import org.jetbrains.kotlin.ir.types.isLong
import org.jetbrains.kotlin.ir.types.isShort
import org.jetbrains.kotlin.ir.types.isUByte
import org.jetbrains.kotlin.ir.types.isULong
import org.jetbrains.kotlin.ir.types.isUShort
import org.jetbrains.kotlin.ir.types.isUnit

object ByteSizes {
    const val DEFAULT = 8
    const val BOOLEAN = 1
    const val UNIT = 8
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
    this.isBoolean() -> ByteSizes.BOOLEAN
    this.isUnit() -> ByteSizes.UNIT
    else -> ByteSizes.DEFAULT
}
