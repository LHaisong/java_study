package Enumerat;

/**
 * 由于enum已经继承了Enum类，所以不能再继承了
 * 但是可以用实现接口的方式进行扩展
 */
public interface Food {
	enum Appetier implements Food{
		SALAD,SOUP,SPRING_ROOLS;
	}
	enum MainCourse implements Food{
		LASAGEN,BURRITO,HUMMOUS;
	}
	enum Desert implements Food{
		FRUIT,SALA,GELATO;
	}
}
