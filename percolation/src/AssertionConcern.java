<<<<<<< HEAD




public interface AssertionConcern {

    default void assertArgument(boolean aBoolean, String aMessage) {
        if (!aBoolean) {
            throw new IllegalArgumentException(aMessage);
        }
    }
=======




public interface AssertionConcern {

    default void assertArgument(boolean aBoolean, String aMessage) {
        if (!aBoolean) {
            throw new IllegalArgumentException(aMessage);
        }
    }
>>>>>>> 093657dea6420a6fc3ffe42e51947c8389bc64e1
}