package Enumerat;

public enum  EnumAndEnum {
	/**
	 * 枚举的枚举
	 */
	APPETIZE(Food.Appetier.class),
	MAINCOURSE(Food.MainCourse.class),
	DESERT(Food.Desert.class);

	private Food[]values;
	EnumAndEnum(Class<? extends Food> kind) {
		values=kind.getEnumConstants();
	}
}
