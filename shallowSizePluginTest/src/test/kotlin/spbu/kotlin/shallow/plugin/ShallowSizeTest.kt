package spbu.kotlin.shallow.plugin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Named
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.reflect.full.memberFunctions

const val DEFAULT_SIZE = 8
const val BOOLEAN_SIZE = 1

class AddShallowSizeMethodTest {
    @ParameterizedTest(name = "[{index}] : {0}")
    @MethodSource("getShallowSizeTestData")
    fun shallowSize(testingClass: Any, expectedSize: Int) {
        require(testingClass::class.isData) { "the shallowSize method is present only in data classes" }

        val shallowSizeMethod = testingClass::class.memberFunctions.find { it.name == "shallowSize" }!!
        assertEquals(shallowSizeMethod.call(testingClass), expectedSize)
    }

    companion object {
        @JvmStatic
        fun getShallowSizeTestData() = listOf(
            Arguments.of(Named.of("BaseClass", BaseClass("Hello")), DEFAULT_SIZE),
            Arguments.of(Named.of("InternalClass", InternalClass(true)), BOOLEAN_SIZE),
            Arguments.of(Named.of("InheritInterfaces", InheritInterfaces(3)), Int.SIZE_BYTES),
            Arguments.of(Named.of("NoBackField", NoBackField('c')), Char.SIZE_BYTES),
            Arguments.of(Named.of("PrivateFields", PrivateFields(3)), Long.SIZE_BYTES + Int.SIZE_BYTES),
            Arguments.of(
                Named.of("MultipleFieldsInConstructor", MultipleFieldsInConstructor(1, 2, 3, 4)),
                Byte.SIZE_BYTES + Short.SIZE_BYTES + Int.SIZE_BYTES + Long.SIZE_BYTES
            ),
            Arguments.of(
                Named.of("NullablePrimitives", NullablePrimitives(1f, 1.0, 'c', true)),
                4 * DEFAULT_SIZE
            ),
            Arguments.of(Named.of("JavaCharacter", JavaCharacter(Character('3'))), DEFAULT_SIZE),
            Arguments.of(Named.of("NoExplicitType", NoExplicitType(3)), Int.SIZE_BYTES + Long.SIZE_BYTES),
            Arguments.of(Named.of("OverrideFieldFromClass", OverrideFieldFromClass(4)), Int.SIZE_BYTES),
            Arguments.of(Named.of("OverrideFieldFromInterface", OverrideFieldFromInterface(4)), Int.SIZE_BYTES),
        )
    }
}
